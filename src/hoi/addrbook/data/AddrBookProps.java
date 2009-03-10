package hoi.addrbook.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class AddrBookProps extends java.util.LinkedHashMap<String, ContactProps> {

	private static final long serialVersionUID = 2738957830618139780L;
	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final String ADDRBOOKER_DIR_NAME = USER_HOME_DIR + File.separator + "AddrBooker";
	private static final String ADDRBOOKER_FILE_PATH = ADDRBOOKER_DIR_NAME + File.separator + "AddrBooker.abk";
	static {
		new File(ADDRBOOKER_DIR_NAME).mkdirs();
		File file = new File(ADDRBOOKER_FILE_PATH);
		if (file.exists()) {
			if (file.isDirectory()) {
				file.delete();
			} else {
				try { // 备份
					FileUtils.copyFile(file, new File(ADDRBOOKER_FILE_PATH + getDateString()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			save(new AddrBookProps(), ADDRBOOKER_FILE_PATH);
		}
	}
	private static final String MARK = ": ";

	private static String getDateString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	private static String quote(String str) {
		if (str == null)
			return "";
		return str.replace("\n", "\\n").replace("\t", "\\t");
	}

	private static String unquote(String str) {
		if (str == null)
			return str;
		return str.replace("\\n", "\n").replace("\\t", "\t");
	}

	public static AddrBookProps load(String path) {
		try {
			String version = null;
			AddrBookProps addrbook = new AddrBookProps();
			ContactProps contact = new ContactProps();

			Scanner cin = new Scanner(new File(path));
			while (cin.hasNextLine()) {
				String line = cin.nextLine();
				if (line.startsWith("#")) {
					version = line;
				} else if (line.startsWith("+")) {
					if (contact.containsKey(ContactPropsEnum.NAME.getKeyName())) {
						String name = contact.getProperty(ContactPropsEnum.NAME);
						addrbook.put(name, contact);
						contact = new ContactProps();
					} else {
						//
					}
				} else if (line.indexOf(MARK) != -1) {
					String[] o = line.split(MARK, 2);
					if (o.length == 2) {
						String key = o[0].trim();
						String value = o[1];

						contact.setProperty(key, unquote(value));
					}
				} else {

				}
			}
			return addrbook;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new AddrBookProps();
		}
	}

	public static AddrBookProps load() {
		return load(ADDRBOOKER_FILE_PATH);
	}

	public static void save(AddrBookProps props, String path) {
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write("#0.1\r\n".getBytes());
			for (String s : props.keySet()) {
				ContactProps contact = props.get(s);
				for (Object ss : contact.keySet().toArray())
					fos.write(String.format("%s%s%s\r\n", ss.toString(), MARK, quote(contact.getProperty( //
							ss.toString()))).getBytes());
				fos.write("+\r\n".getBytes());
			}
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void save(AddrBookProps props) {
		save(props, ADDRBOOKER_FILE_PATH);
	}

	public static void main(String[] args) {
		AddrBookProps.save(new AddrBookProps());
		AddrBookProps.save(new AddrBookProps());
	}
}

package hoi.addrbook.data;

import hoi.addrbook.Version;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AddrBookProps extends LinkedHashMap<String, ContactProps> {

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
			} else { // 备份
				copyFile(file, new File(ADDRBOOKER_FILE_PATH + getDateString()));
			}
		} else {
			save(new AddrBookProps(), ADDRBOOKER_FILE_PATH);
		}
	}
	private static final String MARK = ":";

	private static String getDateString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	private static boolean copyFile(File src, File dest) {
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dest);
			for (int c = fis.read(); c != -1; c = fis.read())
				fos.write(c);
			fis.close();
			fos.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	private static String quote(String str) {
		if (str == null)
			return "";
		return str.replace("\n", "\\n");
	}

	private static String unquote(String str) {
		if (str == null)
			return "";
		return str.replace("\\n", "\n");
	}

	public static AddrBookProps load(String path) {
		try {
			AddrBookProps addrbook = new AddrBookProps();
			ContactProps contact = new ContactProps();

			Scanner cin = new Scanner(new File(path));
			while (cin.hasNextLine()) {
				String line = cin.nextLine();
				if (line.startsWith("#")) {

				} else if (line.startsWith("+")) {
					if (contact.containsKey(ContactProps.NAME)) {
						String name = contact.getProperty(ContactProps.NAME);
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
			String k = String.format("#Version=%s\r\n", Version.FULL_VERSION);
			fos.write(k.getBytes());
			fos.write(("#" + new Date().toString() + "\r\n").getBytes());
			int cnt = 0;
			for (String s : props.keySet()) {
				ContactProps contact = props.get(s);
				fos.write(String.format("\r\n#%s\r\n", contact.getProperty(ContactProps.NAME)).getBytes());
				for (Object ss : contact.keySet().toArray())
					fos.write(String.format("%s%s%s\r\n", ss.toString(), MARK, quote(contact.getProperty( //
							ss.toString()))).getBytes());
				cnt += 1;
				fos.write(String.format("+%d/%d\r\n", cnt, props.keySet().size()).getBytes());
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

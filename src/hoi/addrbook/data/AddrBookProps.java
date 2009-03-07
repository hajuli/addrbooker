package hoi.addrbook.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.ho.yaml.Yaml;

public class AddrBookProps extends java.util.LinkedHashMap<String, ContactProps> {

	private static final long serialVersionUID = 2738957830618139780L;
	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final String ADDRBOOKER_DIR_NAME = USER_HOME_DIR + File.separator + "AddrBooker";
	private static final String ADDRBOOKER_FILE_PATH = ADDRBOOKER_DIR_NAME + File.separator + "AddrBooker.yml";
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
			try {
				Yaml.dump(new AddrBookProps(), new File(ADDRBOOKER_FILE_PATH));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getDateString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static AddrBookProps load(String path) {
		try {
			return (AddrBookProps) Yaml.load(new File(path));
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
			Yaml.dump(props, new File(path));
		} catch (FileNotFoundException e) {
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

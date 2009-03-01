package hoi.addrbook.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.ho.yaml.Yaml;

public class AddrBookProps extends Properties {

	private static final long serialVersionUID = 2738957830618139780L;
	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final String ADDRBOOKER_DIR_NAME = USER_HOME_DIR + File.separator + "addrbooker";
	static {
		new File(ADDRBOOKER_DIR_NAME).mkdirs();
	}
	private static final String ADDRBOOKER_FILE_PATH = ADDRBOOKER_DIR_NAME + File.separator + "addrbooker.yml";
	private static final AddrBookProps addrbook = new AddrBookProps();

	public static AddrBookProps getInstance() {
		return addrbook;
	}

	public static boolean load(String path) {
		try {
			return addrbook.putAll((AddrBookProps) Yaml.load(new File(path));
		} catch (FileNotFoundException e) {
			return ""; // first time
		}
	}

	public static boolean save(String path) {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) // If there is the same name folder
			file.delete(); // delete it
		try {
			Yaml.dump(addrbook, new File(path));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Not enough permissions etc.
			return false;
		}
	}
}

package hoi.addrbook.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.ho.yaml.Yaml;

public class AddrBookProps extends Properties {

	private static final long serialVersionUID = 2738957830618139780L;
	private static final String USER_HOME_DIR = System.getProperty("user.home");
	private static final String ADDRBOOKER_DIR_NAME = USER_HOME_DIR + File.separator + "AddrBooker";
	static {
		new File(ADDRBOOKER_DIR_NAME).mkdirs();
	}
	private static final String ADDRBOOKER_FILE_PATH = ADDRBOOKER_DIR_NAME + File.separator + "AddrBooker.yml";

	private static String today() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static AddrBookProps load(String path) throws FileNotFoundException {
		return (AddrBookProps) Yaml.load(new File(path));
	}

	public static AddrBookProps load() throws FileNotFoundException {
		return load(ADDRBOOKER_FILE_PATH);
	}

	public static void save(String path, AddrBookProps props) throws FileNotFoundException {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) { // 如果有同名的文件夹
				file.delete(); // 不管三七二十一，删除它
			} else {
				try { // 重命名备份
					FileUtils.copyFile(file, new File(ADDRBOOKER_FILE_PATH + today()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Yaml.dump(props, new File(path));
	}

	public static void save(AddrBookProps props) throws FileNotFoundException {
		save(ADDRBOOKER_FILE_PATH, props);
	}

	public static void main(String[] args) throws FileNotFoundException {
		AddrBookProps.save(new AddrBookProps());
		AddrBookProps.save(new AddrBookProps());
	}
}

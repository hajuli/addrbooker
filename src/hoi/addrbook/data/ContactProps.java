package hoi.addrbook.data;

import java.util.Properties;

public class ContactProps extends Properties {

	public static final String NAME = ("姓名"); //
	public static final String CLASSIFY = ("分组"); //
	public static final String BIRTHDAY_LUNAR = ("生日_农历"); //
	public static final String BIRTHDAY_SOLAR = ("生日_阳历"); //
	public static final String TIMER = ("计时"); //
	public static final String QQ = ("QQ"); //
	public static final String MSN = ("MSN"); //
	public static final String MOBILE = ("手机"); //
	public static final String FETION = "飞信"; //
	public static final String EMAIL = ("电子邮箱"); //
	public static final String WEBSITE = ("个人主页"); //
	public static final String HOMEADDR = ("家庭住址"); //
	public static final String COMPANY = ("公司信息"); //
	public static final String NOTES = ("附注"); //
	public static final String AGE = ("年龄");

	private static final long serialVersionUID = 2452503714339288830L;

	public ContactProps() {
		for (ContactPropsEnum key : ContactPropsEnum.values())
			setProperty(key, "");
	}

	public Object setProperty(ContactPropsEnum contactKey, String value) {
		return super.setProperty(contactKey.getKeyName(), value);
	}

	//	public Object setProperty(String key, String value) {
	//		throw new IllegalArgumentException("This func not permitted");
	//	}

	public String getProperty(ContactPropsEnum contactKey) {
		return super.getProperty(contactKey.getKeyName());
	}

	public String getProperty(ContactPropsEnum contactKey, String defaultValue) {
		return super.getProperty(contactKey.getKeyName(), defaultValue);
	}
	//
	//	public String getProperty(String key) {
	//		throw new IllegalArgumentException("This func not permitted");
	//	}
	//
	//	public String getProperty(String key, String defaultValue) {
	//		throw new IllegalArgumentException("This func not permitted");
	//	}
}

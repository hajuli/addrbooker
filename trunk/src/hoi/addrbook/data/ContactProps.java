package hoi.addrbook.data;

import java.util.Properties;

public class ContactProps extends Properties {

	private static final long serialVersionUID = 2452503714339288830L;

	public static final String NAME = "姓名";
	public static final String CLASSIFY = "分组";
	public static final String BIRTHDAY_LUNAR = "生日_农历";
	public static final String BIRTHDAY_SOLAR = "生日_阳历";
	public static final String TIMER = "计时";
	public static final String QQ = "QQ";
	public static final String MSN = "MSN";
	public static final String MOBILE = "手机";
	public static final String FETION = "飞信";
	public static final String EMAIL = "电子邮箱";
	public static final String WEBSITE = "个人主页";
	public static final String HOMEADDR = "家庭住址";
	public static final String COMPANY = "公司信息";
	public static final String NOTES = "附注";

	public static final String[] KEYS = new String[] {
			NAME, CLASSIFY, BIRTHDAY_LUNAR, BIRTHDAY_SOLAR, TIMER, QQ, MSN, //
			MOBILE, FETION, EMAIL, WEBSITE, HOMEADDR, COMPANY, NOTES, };

	public Object setProperty(String key, String value) {
		for (String _key : KEYS)
			if (_key.equals(key))
				return super.setProperty(key, value);
		throw new IllegalArgumentException(String.format("Key(%s) not permitted", key));
	}
}

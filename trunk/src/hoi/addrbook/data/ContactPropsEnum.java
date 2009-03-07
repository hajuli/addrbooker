package hoi.addrbook.data;

public enum ContactPropsEnum {
	NAME("姓名"), //
	CLASSIFY("分组"), //
	BIRTHDAY_LUNAR("生日_农历"), //
	BIRTHDAY_SOLAR("生日_阳历"), //
	TIMER("计时"), //
	QQ("QQ"), //
	MSN("MSN"), //
	MOBILE("手机"), //
	FETION("飞信"), //
	EMAIL("电子邮箱"), //
	WEBSITE("个人主页"), //
	HOMEADDR("家庭住址"), //
	COMPANY("公司信息"), //
	NOTES("附注"), //

	AGE("年龄");

	private final String keyName;

	ContactPropsEnum(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyName() {
		return keyName;
	}
}

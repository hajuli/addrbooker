package hoi.addrbook.data;

import java.util.Properties;

public class ContactProps extends Properties {

	private static final long serialVersionUID = 2452503714339288830L;

	public Object setProperty(ContactPropsEnum contactKey, String value) {
		return super.setProperty(contactKey.getKeyName(), value);
	}

	public Object setProperty(String key, String value) {
		throw new IllegalArgumentException("This func not permitted");
	}

	public String getProperty(ContactPropsEnum contactKey) {
		return super.getProperty(contactKey.getKeyName());
	}

	public String getProperty(ContactPropsEnum contactKey, String defaultValue) {
		return super.getProperty(contactKey.getKeyName(), defaultValue);
	}

	public String getProperty(String key) {
		throw new IllegalArgumentException("This func not permitted");
	}

	public String getProperty(String key, String defaultValue) {
		throw new IllegalArgumentException("This func not permitted");
	}
}

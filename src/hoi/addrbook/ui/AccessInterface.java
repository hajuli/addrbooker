package hoi.addrbook.ui;

import hoi.addrbook.data.ContactPropsEnum;

public interface AccessInterface {

	public ContactPropsEnum getContactKey();

	public void setContactKey(ContactPropsEnum contactKey);

	public String getContent();

	public void setContent(String content);
}

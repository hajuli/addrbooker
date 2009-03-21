package hoi.addrbook.data;

import java.util.Properties;

public class ContactProps extends Properties {

    private static final long serialVersionUID = 2452503714339288830L;

    public static final String BIRTHDAY = "Birthday";
    public static final String LUNAR = "Lunar";
    public static final String SOLAR = "Solar";

    public static final String NAME = "Name";
    public static final String CLASSIFY = "Classify";
    public static final String BIRTHDAY_LUNAR = LUNAR + BIRTHDAY;
    public static final String BIRTHDAY_SOLAR = SOLAR + BIRTHDAY;
    public static final String TIMER = "Timer";
    public static final String QQ = "QQ";
    public static final String MSN = "MSN";
    public static final String MOBILE = "Mobile";
    public static final String FETION = "Fetion";
    public static final String EMAIL = "Email";
    public static final String WEBSITE = "Website";
    public static final String ADDRESS = "Address";
    public static final String COMPANY = "Company";
    public static final String NOTES = "Notes";
    public static final String AGE = "Age";

    public static final String[] KEYS = new String[] {
            NAME, CLASSIFY, AGE, BIRTHDAY_LUNAR, BIRTHDAY_SOLAR, TIMER, QQ, MSN, //
            MOBILE, FETION, EMAIL, WEBSITE, ADDRESS, COMPANY, NOTES };
    public static final String KEY_REX = "[A-Za-z0-9_]+"; // KEY 都是以字母、数字、下划线组成

    public ContactProps() {
        for (String key : KEYS)
            setProperty(key, "");
    }

    public static void main(String[] args) {
        for (String key : KEYS)
            System.out.println(key.matches(KEY_REX));
    }
}

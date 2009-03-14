package hoi.addrbook.data;

import java.util.Properties;

public class ContactProps extends Properties {

    private static final long serialVersionUID = 2452503714339288830L;

    public static final String NAME = "NAME";
    public static final String CLASSIFY = "CLASSIFY";
    public static final String BIRTHDAY_LUNAR = "BIRTHDAY_LUNAR";
    public static final String BIRTHDAY_SOLAR = "BIRTHDAY_SOLAR";
    public static final String TIMER = "TIMER";
    public static final String QQ = "QQ";
    public static final String MSN = "MSN";
    public static final String MOBILE = "MOBILE";
    public static final String FETION = "FETION";
    public static final String EMAIL = "EMAIL";
    public static final String WEBSITE = "WEBSITE";
    public static final String HOMEADDR = "HOMEADDR";
    public static final String COMPANY = "COMPANY";
    public static final String NOTES = "NOTES";
    public static final String AGE = "AGE";

    public static final String[] KEYS = new String[] {
            NAME, CLASSIFY, BIRTHDAY_LUNAR, BIRTHDAY_SOLAR, TIMER, QQ, MSN, //
            MOBILE, FETION, EMAIL, WEBSITE, HOMEADDR, COMPANY, NOTES, AGE };
    public static final String KEY_REX = "[A-Z0-9_]+"; // KEY 都是以大写字母、数字、下划线组成

    public ContactProps() {
        for (String key : KEYS)
            setProperty(key, "");
    }

    public static void main(String[] args) {
        for (String key : KEYS)
            System.out.println(key.matches(KEY_REX));
    }
}

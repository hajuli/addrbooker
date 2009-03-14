package hoi.addrbook.util;

import hoi.addrbook.Version;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class XMLHelper {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties xml = new Properties();
        xml.put("NAME", "姓名");
        xml.put("CLASSIFY", "分组");
        xml.put("BIRTHDAY_LUNAR", "生日_农历");
        xml.put("BIRTHDAY_SOLAR", "生日_阳历");
        xml.put("TIMER", "计时");
        xml.put("QQ", "QQ");
        xml.put("MSN", "MSN");
        xml.put("MOBILE", "手机");
        xml.put("FETION", "飞信");
        xml.put("EMAIL", "电子邮箱");
        xml.put("WEBSITE", "个人主页");
        xml.put("HOMEADDR", "家庭住址");
        xml.put("COMPANY", "公司信息");
        xml.put("NOTES", "附注");
        xml.put("AGE", "年龄");
        xml.storeToXML(new FileOutputStream(new File("localize.xml")), Version.HOME_WEBSITE);
    }

}

package hoi.addrbook.ui;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class UINamesCtrl {
    private static Properties names = new Properties();
    static {
        try {
            names.loadFromXML(UINamesCtrl.class.getResourceAsStream("localize.xml"));

            names.put("AddrBooker", "草根通讯录");

            names.put("Backup data", "数据备份");
            names.put("Restore data", "数据恢复");
            names.put("Help", "帮助");
            names.put("Close", "关闭");
            names.put("Backup and Restore you data", "备份/恢复 数据");

            names.put("Settings", "选项 设置");
            names.put("Add Password", "创建密码");
            names.put("Change Password", "更改密码");
            names.put("Donate", "捐赠");

            names.put("Version", "版本");
            names.put("Found New Version", "发现新版本");
            names.put("Reset", "重置");
            names.put("Years", "年");
            names.put("Year", "年");
            names.put("Months", "月");
            names.put("Month", "月");
            names.put("Days", "天");
            names.put("Day", "天");

            names.put("Parse Error", "解析错误");
            names.put("Postcode", "邮政编码");
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLocalName(String name) {
        return names.getProperty(name, name);
    }
}

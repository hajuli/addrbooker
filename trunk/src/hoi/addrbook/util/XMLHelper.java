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
        xml.put("AddrBooker", "草根通讯录");
        xml.put("Backup data", "数据备份");
        xml.put("Restore data", "数据恢复");
        xml.put("Help", "帮助");
        xml.put("Close", "关闭");
        xml.put("Backup and Restore you data", "备份/恢复 数据");
        xml.put("Settings", "选项 设置");
        xml.put("Add Password", "设置密码");
        xml.put("Change Password", "更改密码");
        xml.put("Donate", "捐赠");
        xml.storeToXML(new FileOutputStream(new File("localize.xml")), Version.HOME_WEBSITE);
    }

}

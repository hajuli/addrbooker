package hoi.addrbook.util;

import hoi.addrbook.AddrBookInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class XMLHelper {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Properties names = new Properties();

        names.put("AddrBooker", "草根通讯录");

        names.put("Backup data", "数据备份");
        names.put("Restore data", "数据恢复");
        names.put("Help", "帮助");
        names.put("Close", "关闭");
        names.put("Backup and Restore you data", "备份/恢复 数据");

        names.put("Option Settings", "选项 设置");
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
        names.put("Typing to Search", "键入内容 搜索");

        names.put("Add", "新增");
        names.put("Save", "保存");
        names.put("Delete", "删除");
        names.put("Settings", "设置");
        names.put("Backup", "备份");
        names.put("Exit", "退出");

        names.put("Name", "姓名");
        names.put("Classify", "分组");
        names.put("Birthday", "生日");
        names.put("Lunar", "农历");
        names.put("Solar", "阳历");
        names.put("Timer", "时间");
        names.put("Age", "年龄");
        names.put("QQ", "QQ");
        names.put("MSN", "MSN");
        names.put("Mobile", "手机");
        names.put("Fetion", "飞信");
        names.put("Email", "电子邮箱");
        names.put("Website", "个人主页");
        names.put("Address", "家庭住址");
        names.put("Company", "公司信息");

        names.put("Birthday Reminder", "生日提醒");

        names.storeToXML(new FileOutputStream(new File("localize.xml")), AddrBookInfo.HOME_WEBSITE);
    }

}

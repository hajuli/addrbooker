package hoi.bm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BMRecord {

    protected String name = "";
    protected String type = "";
    protected String birthday = "";
    protected String age = "";
    protected String time = "";
    protected String timer = "";
    protected String website = "";
    protected String notes = "";

    public BMRecord() {
    }

    public BMRecord(String line) {
        String[] items = line.trim().split("\\|", 6);
        setName(items[0]);
        setType(items[1]);
        setBirthday(items[2]);
        setTime(items[3]);
        setWebsite(items[4]);
        setNotes(items[5]);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append(getName() + "|");
        buffer.append(getType() + "|");
        buffer.append(getBirthday() + "|");
        buffer.append(getTime() + "|");
        buffer.append(getWebsite() + "|");
        buffer.append(getNotes());
        return buffer.toString().trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
        Pattern pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
        Matcher matcher = pattern.matcher(birthday);
        if (matcher.find()) {
            int byear = Integer.parseInt(matcher.group(1));
            int bmonth = Integer.parseInt(matcher.group(2));
            String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
            matcher = pattern.matcher(current);
            matcher.find();
            int cyear = Integer.parseInt(matcher.group(1));
            int cmonth = Integer.parseInt(matcher.group(2));
            int kmonth = cyear * 12 + cmonth - byear * 12 - bmonth;
            setAge(String.format("%d年%d个月", kmonth / 12, kmonth % 12));
        }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        Pattern pattern = Pattern.compile("^([0-9]+)-([0-9]+)-([0-9]+)$");
        Matcher matcher = pattern.matcher(time);
        if (matcher.find()) {
            int tyear = Integer.parseInt(matcher.group(1));
            int tmonth = Integer.parseInt(matcher.group(2));
            int tday = Integer.parseInt(matcher.group(3));
            String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
            matcher = pattern.matcher(current);
            matcher.find();
            int cyear = Integer.parseInt(matcher.group(1));
            int cmonth = Integer.parseInt(matcher.group(2));
            int cday = Integer.parseInt(matcher.group(3));
            int kday = cyear * 365 + cmonth * 30 + cday - tyear * 365 - tmonth * 30 - tday; // 大概的算一下，不用那么精确
            setTimer(String.format("%d个月%d天", kday / 30, kday % 30));
        }
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isEmptyRow() {
        return name.trim().equals("") && //
                type.trim().equals("") && //
                birthday.trim().equals("") && //
                age.trim().equals("") && //
                time.trim().equals("") && //
                timer.trim().equals("") && //
                website.trim().equals("") && //
                notes.trim().equals("");
    }
}

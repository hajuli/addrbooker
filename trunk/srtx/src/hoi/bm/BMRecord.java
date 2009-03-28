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
        StringBuffer buffer = new StringBuffer();
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
        setAge(getDeltaDate(birthday));
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

    private int getMonths(String str) {
        Pattern pattern = Pattern.compile("([0-9]+)-([0-9]+)-([0-9]+)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            int year = Integer.parseInt(matcher.group(1));
            int month = Integer.parseInt(matcher.group(2));
            return year * 12 + month;
        } else {
            return 0;
        }
    }

    private String getDeltaDate(String str) {
        int a = getMonths(str);
        int b = getMonths(new SimpleDateFormat("yyyy-M-d").format(new Date()));
        if (a != 0 && b != 0) {
            int c = b - a;
            return String.format("%d个月%d天", c / 12, c % 12);
        } else {
            return "";
        }
    }

    public void setTime(String time) {
        this.time = time;
        setTimer(getDeltaDate(time));
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

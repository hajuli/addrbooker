package hoi.birthdaymgr;

public class BMgrRecord {

    protected Boolean selected = false;
    protected String name = "";
    protected String birthday = "";
    protected String website = "";
    protected String notes = "";
    protected String time = "";

    public BMgrRecord() {
    }

    public BMgrRecord(String line) {
        String[] items = line.trim().split("\\|", 5);
        setName(items[0]);
        setBirthday(items[1]);
        setWebsite(items[2]);
        setNotes(items[3]);
        setTime(items[4]);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getName() + "|");
        buffer.append(getBirthday() + "|");
        buffer.append(getWebsite() + "|");
        buffer.append(getNotes() + "|");
        buffer.append(getTime());
        return buffer.toString().trim();
    }

    public boolean isEmptyRecord() {
        return name.trim().equals("") && //
                birthday.trim().equals("") && //
                website.trim().equals("") && //
                notes.trim().equals("") && //
                time.trim().equals("");
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

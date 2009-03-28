package hoi.bm;

public class BMRecord {

    protected String name = "";
    protected String type = "";
    protected String birthday = "";
    protected String time = "";
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
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
                time.trim().equals("") && //
                website.trim().equals("") && //
                notes.trim().equals("");
    }
}

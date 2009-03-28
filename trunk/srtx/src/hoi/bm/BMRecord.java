package hoi.bm;

public class BMRecord {
    protected String name = "";
    protected String type = "";
    protected String birthday = "";
    protected String age = "";
    protected String timer = "";
    protected String website = "";
    protected String notes = "";

    public BMRecord() {
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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
                timer.trim().equals("") && //
                website.trim().equals("") && //
                notes.trim().equals("");
    }
}

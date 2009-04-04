package hoi.birthdaymgr.utility;

public class LunarCalendar extends BaseCalendar {

    public LunarCalendar(int year, int month, int day) throws Exception {
        super(year, month, day);
        check();
    }

    public String toString() {
        return String.format("农历 %d-%02d-%02d", year, month, day);
    }

    public LunarCalendar next() throws Exception {
        return toSolarCalendar().next().toLunarCalendar();
    }

    public boolean check2() {
        try {
            return check();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check() throws Exception {
        int LunarYear = year, LunarMonth = month, LunarDate = day;

        if (LunarYear < FIRSTYEAR || LunarYear >= LASTYEAR)
            throw new Exception(String.format("请输入%d-%d有效年份", FIRSTYEAR, LASTYEAR));

        int y = LunarYear - FIRSTYEAR;
        int im = LunarCal[y].Intercalation;
        int lm = LunarMonth;
        if (lm < 0) {
            if (lm != -im)
                throw new Exception("请输入有效月份");
        } else if (lm < 1 || lm > 12)
            throw new Exception("请输入有效月份");

        if (im != 0) {
            if (lm > im)
                lm++;
            else if (lm == -im)
                lm = im + 1;
        }
        lm--;
        if (LunarDate > LunarCal[y].MonthDays[lm] + 29)
            throw new Exception("农历日期不正确");
        return true;
    }

    public SolarCalendar toSolarCalendar() throws Exception {
        int LunarYear = year, LunarMonth = month, LunarDate = day;
        int SolarYear, SolarMonth, SolarDate;

        int y = LunarYear - FIRSTYEAR;
        int im = LunarCal[y].Intercalation;
        int lm = LunarMonth;
        if (im != 0) {
            if (lm > im)
                lm++;
            else if (lm == -im)
                lm = im + 1;
        }
        lm--;

        int acc = 0, i;
        for (i = 0; i < lm; i++)
            acc += LunarCal[y].MonthDays[i] + 29;
        acc += LunarCal[y].BaseDays + LunarDate;
        int leap = SolarCalendar.GetLeap(LunarYear);
        for (i = 13; i >= 0; i--)
            if (acc > SolarDays[leap * 14 + i])
                break;
        SolarDate = acc - SolarDays[leap * 14 + i];

        if (i <= 11) {
            SolarYear = LunarYear;
            SolarMonth = i + 1;
        } else {
            SolarYear = LunarYear + 1;
            SolarMonth = i - 11;
        }

        leap = SolarCalendar.GetLeap(SolarYear);
        y = SolarYear - FIRSTYEAR;
        acc = SolarDays[leap * 14 + SolarMonth - 1] + SolarDate;

        return new SolarCalendar(SolarYear, SolarMonth, SolarDate);
    }
}

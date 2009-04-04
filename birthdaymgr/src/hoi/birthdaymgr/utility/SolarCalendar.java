package hoi.birthdaymgr.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SolarCalendar extends BaseCalendar {

    public SolarCalendar(int year, int month, int day) throws Exception {
        super(year, month, day);
        check();
    }

    public SolarCalendar(String str) throws Exception {
        super(str);
        check();
    }

    public String toString() {
        return String.format("公历 %d-%02d-%02d", year, month, day);
    }

    /* 闰年, 返回 0 平年, 1 闰年 */
    public static int GetLeap(int year) {
        if (year % 400 == 0)
            return 1;
        else if (year % 100 == 0)
            return 0;
        else if (year % 4 == 0)
            return 1;
        else
            return 0;
    }

    public SolarCalendar next() throws Exception {
        int maxDay = SolarCal[month - 1];
        if (month == 2)
            maxDay = 28 + GetLeap(year);

        if (day < maxDay) // 此月还没完
            return new SolarCalendar(year, month, day + 1);
        if (month == 12) // 月末，年末
            return new SolarCalendar(year + 1, 1, 1);
        return new SolarCalendar(year, month + 1, 1); // 月末
    }

    public SolarCalendar copy() {
        try {
            return new SolarCalendar(year, month, day);
        } catch (Exception ignore) {
            return null;
        }
    }

    public static SolarCalendar today() {
        String current = new SimpleDateFormat("yyyy-M-d").format(new Date());
        try {
            return new SolarCalendar(current);
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return null;
        }
    }

    public boolean check2() {
        try {
            return check();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean check() throws Exception {
        int SolarYear = year, SolarMonth = month, SolarDate = day;

        if (SolarYear <= FIRSTYEAR || SolarYear > LASTYEAR)
            throw new Exception(String.format("请输入%d-%d有效年份", FIRSTYEAR, LASTYEAR));

        int sm = SolarMonth - 1;
        if (sm < 0 || sm > 11)
            throw new Exception("请输入有效月份");

        int leap = GetLeap(SolarYear), d = 0;
        if (sm == 1)
            d = leap + 28;
        else
            d = SolarCal[sm];
        if (SolarDate < 1 || SolarDate > d)
            throw new Exception("请输入有效日期");
        return true;
    }

    public LunarCalendar toLunarCalendar() throws Exception {
        int SolarYear = year, SolarMonth = month, SolarDate = day;
        int LunarYear, LunarMonth, LunarDate;

        int sm = SolarMonth - 1;
        int leap = GetLeap(SolarYear);

        int y = SolarYear - FIRSTYEAR;
        int acc = SolarDays[leap * 14 + sm] + SolarDate;
        int kc = acc + LunarCal[y].BaseKanChih;
        int Age = kc % 60;
        if (Age < 22)
            Age = 22 - Age;
        else
            Age = 82 - Age;
        Age = Age + 3;
        if (Age < 10)
            Age = Age + 60;

        if (acc <= LunarCal[y].BaseDays) {
            y--;
            LunarYear = SolarYear - 1;
            leap = GetLeap(LunarYear);
            sm += 12;
            acc = SolarDays[leap * 14 + sm] + SolarDate;
        } else
            LunarYear = SolarYear;

        int l1 = LunarCal[y].BaseDays, i;
        for (i = 0; i < 13; i++) {
            int l2 = l1 + LunarCal[y].MonthDays[i] + 29;
            if (acc <= l2)
                break;
            l1 = l2;
        }

        LunarMonth = i + 1;
        LunarDate = acc - l1;
        int im = LunarCal[y].Intercalation;
        if (im != 0 && LunarMonth > im) {
            LunarMonth--;
            if (LunarMonth == im)
                LunarMonth = -im;
        }
        if (LunarMonth > 12)
            LunarMonth -= 12;

        return new LunarCalendar(LunarYear, LunarMonth, LunarDate);
    }
}

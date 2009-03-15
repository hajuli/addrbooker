package hoi.addrbook.util;

/**
 * http://act1.astro.women.sohu.com/search/calendar.htm <br>
 * http://www.gio.gov.tw/info/festival_c/calendar/calendar.htm <br>
 * http://www.wannianli.net/ <br>
 * http://www.time.ac.cn/nongli.htm <br>
 * http://www.21softs.com/calendar.htm
 * 
 * @author Hoi
 */
public class LunisolarCalendar { // 

    private int year;
    private int month;
    private int day;

    public LunisolarCalendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String toString() {
        return String.format("%d-%02d-%02d", this.year, this.month, this.day);
    }

    private static final int FIRSTYEAR = 1936;
    private static final int LASTYEAR = 2031;
    private static final TagLunarCal LunarCal[] = new TagLunarCal[] { //
            new TagLunarCal(23, 3, 2, 17, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0), /* 1936 */
            new TagLunarCal(41, 0, 4, 23, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1), //
            new TagLunarCal(30, 7, 5, 28, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1), //
            new TagLunarCal(49, 0, 6, 33, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1), //
            new TagLunarCal(38, 0, 0, 38, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1), /* 1940 */
            new TagLunarCal(26, 6, 2, 44, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0), //
            new TagLunarCal(45, 0, 3, 49, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(35, 0, 4, 54, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(24, 4, 5, 59, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1), /* 1944 */
            new TagLunarCal(43, 0, 0, 5, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1), //
            new TagLunarCal(32, 0, 1, 10, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1), //
            new TagLunarCal(21, 2, 2, 15, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1), //
            new TagLunarCal(40, 0, 3, 20, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1), /* 1948 */
            new TagLunarCal(28, 7, 5, 26, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(47, 0, 6, 31, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(36, 0, 0, 36, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(26, 5, 1, 41, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1), /* 1952 */
            new TagLunarCal(44, 0, 3, 47, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1), //
            new TagLunarCal(33, 0, 4, 52, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0), //
            new TagLunarCal(23, 3, 5, 57, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1), //
            new TagLunarCal(42, 0, 6, 2, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1), /* 1956 */
            new TagLunarCal(30, 8, 1, 8, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(48, 0, 2, 13, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(38, 0, 3, 18, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(27, 6, 4, 23, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0), /* 1960 */
            new TagLunarCal(45, 0, 6, 29, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(35, 0, 0, 34, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1), //
            new TagLunarCal(24, 4, 1, 39, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0), //
            new TagLunarCal(43, 0, 2, 44, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0), /* 1964 */
            new TagLunarCal(32, 0, 4, 50, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1), //
            new TagLunarCal(20, 3, 5, 55, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0), //
            new TagLunarCal(39, 0, 6, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(29, 7, 0, 5, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1), /* 1968 */
            new TagLunarCal(47, 0, 2, 11, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(36, 0, 3, 16, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0), //
            new TagLunarCal(26, 5, 4, 21, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1), //
            new TagLunarCal(45, 0, 5, 26, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1), /* 1972 */
            new TagLunarCal(33, 0, 0, 32, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1), //
            new TagLunarCal(22, 4, 1, 37, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1), //
            new TagLunarCal(41, 0, 2, 42, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1), //
            new TagLunarCal(30, 8, 3, 47, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1), /* 1976 */
            new TagLunarCal(48, 0, 5, 53, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1), //
            new TagLunarCal(37, 0, 6, 58, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(27, 6, 0, 3, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0), //
            new TagLunarCal(46, 0, 1, 8, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0), /* 1980 */
            new TagLunarCal(35, 0, 3, 14, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1), //
            new TagLunarCal(24, 4, 4, 19, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1), //
            new TagLunarCal(43, 0, 5, 24, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1), //
            new TagLunarCal(32, 10, 6, 29, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1), /* 1984 */
            new TagLunarCal(50, 0, 1, 35, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0), //
            new TagLunarCal(39, 0, 2, 40, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1), //
            new TagLunarCal(28, 6, 3, 45, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0), //
            new TagLunarCal(47, 0, 4, 50, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1), /* 1988 */
            new TagLunarCal(36, 0, 6, 56, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 0), //
            new TagLunarCal(26, 5, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1), //
            new TagLunarCal(45, 0, 1, 6, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0), //
            new TagLunarCal(34, 0, 2, 11, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0), /* 1992 */
            new TagLunarCal(22, 3, 4, 17, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0), //
            new TagLunarCal(40, 0, 5, 22, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0), //
            new TagLunarCal(30, 8, 6, 27, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1), //
            new TagLunarCal(49, 0, 0, 32, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1), /* 1996 */
            new TagLunarCal(37, 0, 2, 38, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1), //
            new TagLunarCal(27, 5, 3, 43, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 1), //
            new TagLunarCal(46, 0, 4, 48, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1), /* 1999 */
            new TagLunarCal(35, 0, 5, 53, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1), /* 2000 */
            new TagLunarCal(23, 4, 0, 59, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(42, 0, 1, 4, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(31, 0, 2, 9, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0), //
            new TagLunarCal(21, 2, 3, 14, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1), /* 2004 */
            new TagLunarCal(39, 0, 5, 20, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(28, 7, 6, 25, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1), //
            new TagLunarCal(48, 0, 0, 30, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1), //
            new TagLunarCal(37, 0, 1, 35, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1), /* 2008 */
            new TagLunarCal(25, 5, 3, 41, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1), //
            new TagLunarCal(44, 0, 4, 46, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1), //
            new TagLunarCal(33, 0, 5, 51, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(22, 4, 6, 56, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0), /* 2012 */
            new TagLunarCal(40, 0, 1, 2, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(30, 9, 2, 7, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1), //
            new TagLunarCal(49, 0, 3, 12, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1), //
            new TagLunarCal(38, 0, 4, 17, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0), /* 2016 */
            new TagLunarCal(27, 6, 6, 23, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1), //
            new TagLunarCal(46, 0, 0, 28, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0), //
            new TagLunarCal(35, 0, 1, 33, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0), //
            new TagLunarCal(24, 4, 2, 38, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1), /* 2020 */
            new TagLunarCal(42, 0, 4, 44, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1), //
            new TagLunarCal(31, 0, 5, 49, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0), //
            new TagLunarCal(21, 2, 6, 54, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1), //
            new TagLunarCal(40, 0, 0, 59, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1), /* 2024 */
            new TagLunarCal(28, 6, 2, 5, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0), //
            new TagLunarCal(47, 0, 3, 10, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1), //
            new TagLunarCal(36, 0, 4, 15, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 1), //
            new TagLunarCal(25, 5, 5, 20, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0), /* 2028 */
            new TagLunarCal(43, 0, 0, 26, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1), //
            new TagLunarCal(32, 0, 1, 31, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0), //
            new TagLunarCal(22, 3, 2, 36, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0), };
    private static final int SolarCal[] = new int[] {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 }; /* 西曆年每月之日數 */
    private static final int SolarDays[] = new int[] { /* 西曆年每月之累積日數，平年與閏年 */
            0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365, 396, //
            0, 31, 60, 91, 121, 152, 182, 213, 244, 274, 305, 335, 366, 397 };

    /**
     * 阳历转阴历
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static LunisolarCalendar solar2lunar(LunisolarCalendar date) throws Exception { // 
        int SolarYear = date.year;
        int SolarMonth = date.month;
        int SolarDate = date.day;
        int LunarYear, LunarMonth, LunarDate;

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

        return new LunisolarCalendar(LunarYear, LunarMonth, LunarDate);
    }

    /**
     * 阴历转阳历
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static LunisolarCalendar lunar2solar(LunisolarCalendar date) throws Exception { // 
        int LunarYear = date.year;
        int LunarMonth = date.month;
        int LunarDate = date.day;
        int SolarYear, SolarMonth, SolarDate;

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

        int acc = 0, i;
        for (i = 0; i < lm; i++)
            acc += LunarCal[y].MonthDays[i] + 29;
        acc += LunarCal[y].BaseDays + LunarDate;
        int leap = GetLeap(LunarYear);
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

        leap = GetLeap(SolarYear);
        y = SolarYear - FIRSTYEAR;
        acc = SolarDays[leap * 14 + SolarMonth - 1] + SolarDate;

        return new LunisolarCalendar(SolarYear, SolarMonth, SolarDate);
    }

    /* 闰年, 返回 0 平年, 1 闰年 */
    private static int GetLeap(int year) {
        if (year % 400 == 0)
            return 1;
        else if (year % 100 == 0)
            return 0;
        else if (year % 4 == 0)
            return 1;
        else
            return 0;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(LunisolarCalendar.lunar2solar(new LunisolarCalendar(1936, 10, 25)));
        System.out.println(LunisolarCalendar.solar2lunar(new LunisolarCalendar(1986, 11, 26)));
        System.out.println(LunisolarCalendar.lunar2solar(new LunisolarCalendar(1986, 3, 0)));
        System.out.println(LunisolarCalendar.solar2lunar(new LunisolarCalendar(1986, 3, 2)));
    }
}

class TagLunarCal {
    public int BaseDays; /* 1 月 1 日到正月初一的累计日 */
    public int Intercalation; /* 闰月月份. 0==此年沒有闰月 */
    public int BaseWeekday; /* 此年 1 月 1 日为星期减 1 */
    public int BaseKanChih; /* 此年 1 月 1 日之干支序号减 1 */
    public int MonthDays[]; /* 此农历年每月之大小, 0==小月(29日), 1==大月(30日) */

    public TagLunarCal(int d, int i, int w, int k, //
            int m1, int m2, int m3, int m4, int m5, int m6, int m7, //
            int m8, int m9, int m10, int m11, int m12, int m13) {
        this.BaseDays = d;
        this.Intercalation = i;
        this.BaseWeekday = w;
        this.BaseKanChih = k;
        this.MonthDays = new int[] {
                m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13 };
    }
}

package hoi.addrbook.util;

import java.util.Date;

public class DeltaDate {
    public int year, month, day;

    public DeltaDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * 按照 一年 365 天，一个月 30 天 计算 Math.abs(dateTo - dateFrom)
     * 
     * @param dateFrom
     * @param dateTo
     */
    public DeltaDate(Date dateFrom, Date dateTo) {
        int days = (int) Math.abs((dateTo.getTime() - dateFrom.getTime()) / 1000 / 60 / 60 / 24);
        this.year = days / 365;
        this.month = days % 365 / 30;
        this.day = days % 365 % 30;
    }

    public String toString() {
        return String.format("%d-%d-%d", year, month, day);
    }
}

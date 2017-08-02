package com.pramy.util;

import java.util.Calendar;
import java.util.Date;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/8/2.
 */
public class DayUtil {

    private static Calendar calendar = Calendar.getInstance();

    public static Date getBeforeDate(int day){
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
        return calendar.getTime();
    }
}

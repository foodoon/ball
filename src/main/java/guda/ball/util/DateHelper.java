package guda.ball.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by foodoon on 2014/8/5.
 */
public class DateHelper {

    public static final String defaultPattern = "yyyyMMddHHmmss";

    public static Date getStartTime(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultPattern);

        Date date = simpleDateFormat.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();

    }

    public static Date getStartDay()  {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();

    }
    public static Date get7Day()  {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR,7);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();

    }

    public static Date parseDate(String d) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = simpleDateFormat.parse(d);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime();
        }catch(Exception e){

        }
        return null;

    }

    public static Date parseDateTime(String d) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return simpleDateFormat.parse(d);

        }catch(Exception e){

        }
        return null;

    }

    public static Date getStartTime(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();

    }

    public static boolean compare(Date time) {
       if(time == null){
           return false;
       }
        return new Date().before(time);

    }

    public static boolean sameDay(Date time) {
        if(time == null){
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String t = simpleDateFormat.format(time);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat2.format(new Date());
        return t.equals(format);

    }

    public static Date get7Time() throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,7);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();

    }

    public static Date getEndTime(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultPattern);

        Date date = simpleDateFormat.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();

    }
    public static Date getEndTime(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        return calendar.getTime();

    }

    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isWorkday(Date date)  {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(n);
        return n>1 && n<7;

    }

    public static boolean isWeekend(Date date)  {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(n);
        return n == 1 || n ==7;

    }


    public static String formatYMDHMSCN(Date time)  {
        if(time == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd HH时mm分ss秒");
        return simpleDateFormat.format(time);
    }

    public static String formatYMDHMCN(Date time)  {
        if(time == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd HH时mm分");
        return simpleDateFormat.format(time);
    }

    public static String formatHMCN(Date time)  {
        if(time == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH时mm分");
        return simpleDateFormat.format(time);
    }


    public static String formatYMD(Date time) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(time);
    }
    public static String formatYMDCN(Date time) {
        if (time == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return simpleDateFormat.format(time);
    }

    public static String formatShowCN(Date time)  {
        if(time == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");

        return simpleDateFormat.format(time);
    }

}

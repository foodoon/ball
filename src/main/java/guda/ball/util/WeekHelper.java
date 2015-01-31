package guda.ball.util;

/**
 * Created by foodoon on 2015/1/31.
 */
public class WeekHelper {

    private static String[] weekCN = new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    public static String cn(int week){
        if(week < 7) {
            return weekCN[week];
        }
        return null;
    }
}

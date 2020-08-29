package com.kingstar.kafka.io;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xiayc
 * @date 2020/8/29 17:24
 */
public class DateConvertUtil {
    public static void dateConvertUtil(String dateValue) {
        int num;
        if (dateValue.contains("+")) {
            String addNum = dateValue.substring(dateValue.indexOf("+") + 1);
            num = Integer.parseInt(addNum);
        } else if (dateValue.contains("-")) {
            String addNum = dateValue.substring(dateValue.indexOf("-") + 1);
            num = -Integer.parseInt(addNum);
        } else {
            num = 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, num);

        Date newDate = calendar.getTime();
        String newDateValue = new SimpleDateFormat("yyyy-MM-dd").format(newDate);
        System.out.println(newDateValue);
    }

    public static void main(String[] args) {
        dateConvertUtil("today");
    }
}

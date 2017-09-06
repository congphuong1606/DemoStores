package com.example.mypc.stores.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by MyPC on 08/08/2017.
 */

public class TimeControler {
    public String getCurentTime(){
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return  format.format(Calendar.getInstance().getTime());
    }
    public long getLongCurentTime(){
        return System.currentTimeMillis()% 10015L;
    }
}

package com.karrel.bluetoothsample.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Rell on 2017. 8. 29..
 */

public class ReadDataItem {
    public List<String> list;
    public String date;

    public ReadDataItem(List<String> list) {
        this.list = list;
        SimpleDateFormat format = new SimpleDateFormat("hh:MM:ss");
        date = format.format(new Date());

    }

}

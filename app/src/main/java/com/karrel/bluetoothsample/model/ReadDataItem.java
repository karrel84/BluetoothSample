package com.karrel.bluetoothsample.model;

import java.util.Queue;

/**
 * Created by Rell on 2017. 8. 29..
 */

public class ReadDataItem {
    public Queue<String> queue;

    public ReadDataItem(Queue<String> queue) {
        this.queue = queue;
    }
}

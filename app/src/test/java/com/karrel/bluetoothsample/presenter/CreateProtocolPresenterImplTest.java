package com.karrel.bluetoothsample.presenter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rell on 2017. 8. 30..
 */
public class CreateProtocolPresenterImplTest {

    @Test
    public void test() {
        String hex = "ABABABABAB0101010101020202020203030303030404";

        List<String> strings = null;
        List<List<String>> strings2 = new ArrayList<>();
        strings = new ArrayList<>();
        strings2.add(strings);
        for (int i = 2; i < hex.length(); i += 2) {
            if (strings.size() == 10) {
                strings = new ArrayList<>();
                strings2.add(strings);
            }
            strings.add(hex.substring(i - 2, i));
        }

        System.out.println(strings2);
    }
}
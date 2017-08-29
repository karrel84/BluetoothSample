package com.karrel.bluetoothsample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void ltrimzero() {
        String s = "000000123000001231231230000000000000";

        System.out.println("s : " + s.replaceAll("0*$", ""));
    }
}
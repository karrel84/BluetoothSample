package com.karrel.bluetoothsample.presenter;

import com.karrel.bluetoothsample.util.ByteConverter;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Rell on 2017. 8. 29..
 */
public class MainPresenterImplTest {

    @Test
    public void createByteQueue() {
        // 뒤에 0은 모두 지운다.
        String readMessage = "551C01020000102440064000000100FFFF0100008200008F0000003F551C01020000102440064000000000FFFF0000008200008F0000003D";

        byte[] bytes = ByteConverter.hexToByteArray(readMessage);

        Queue<String> queue = new ArrayDeque<>();
        for (byte b : bytes) {
            final String hex = ByteConverter.byteToHex(b);
            queue.add(hex);
        }

        int count = 0;
        while (!queue.isEmpty()) {
            if (count % 5 == 0) {
                System.out.print(" ");
            }
            if (count % 20 == 0) {
                System.out.println();
            }
            System.out.print(queue.poll());

            count++;
        }
    }
}
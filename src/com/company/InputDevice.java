package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class InputDevice {

    private static BufferedReader bufferRead;

    public static void openFile() throws FileNotFoundException {
        bufferRead = new BufferedReader(new FileReader("input.txt"));
    }

    public static Word[] getInput() throws IOException {

        String s = bufferRead.readLine();

        if(s == null)
            return null;

        byte[] bytes = s.getBytes();

        Word[] words;

        // Creating Word array
        if (bytes.length % 4 != 0) {
            words = new Word[(bytes.length / 4) + 1];
        } else {
            words = new Word[bytes.length / 4];
        }

        // Initializing empty Word array
        for (int i = 0; i < words.length; i++) {
            words[i] = new Word();
        }

        // Fill array with bytes
        int i = 0, j = 0;
        for (byte b : bytes) {
            if (i <= 3) {
                words[j].setByte(i++, b);
            } else {
                i = 0;
                j++;
                words[j].setByte(i++, b);
            }
        }

        return words;
    }
}

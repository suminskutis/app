package com.company;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

    /*	InputValidator validuojam = new InputValidator();
    	System.out.println(validuojam.validateString("input.txt"));
*/

    InputDevice.openFile();
    Word[] aa = InputDevice.getInput();
        System.out.println(aa);
    }
}

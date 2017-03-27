package com.company;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, CloneNotSupportedException {

    /*	InputValidator validuojam = new InputValidator();
    	System.out.println(validuojam.validateString("input.txt"));
*/

   /* InputDevice.openFile();
    Word[] aa; //InputDevice.getInput();

    int i = 0;
String aaa = "a";
        while(!(aaa.equals("HALT"))) {
            aa = InputDevice.getInput();
            aaa = Word.wordsToString(aa);

            System.out.println(aaa);
        }*/

   RealMachine rm = new RealMachine();
   rm.execute();

    }
}

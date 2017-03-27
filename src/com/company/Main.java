package com.company;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, CloneNotSupportedException {

    /*	InputValidator validuojam = new InputValidator();
    	System.out.println(validuojam.validateString("input.txt"));
*/



   RealMachine rm = new RealMachine();
   rm.execute();

    }
}

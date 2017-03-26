package com.company;

import java.io.FileNotFoundException;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class RealMachine {

    private CPU cpu;
    private Memory memory;


    public RealMachine(){
        this.cpu = new CPU();
        this.memory = new Memory();
    }




    public void execute() throws FileNotFoundException {
        InputDevice.openFile();

    }

}

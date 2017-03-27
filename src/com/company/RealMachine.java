package com.company;

import java.io.FileNotFoundException;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class RealMachine {

    private CPU cpu;
    private Memory memory;
    public final static int VM_SIZE_IN_BLOCKS = 16;


    public RealMachine(){
        this.cpu = new CPU();
        this.memory = new Memory();
    }
	
	/*public void Identify(String line, VirtualMachine VM){
        if(line.substring(0, 3).equals("ADD")){
            VM.cmdADD();
        }
        else if(line.substring(0, 3).equals("SUB")){
            VM.cmdSUB();
        }
        else if(line.substring(0, 3).equals("MUL")){
            VM.cmdMUL();
        }
        else if(line.substring(0, 3).equals("DIV")){
            VM.cmdDIV();
        }
        else if(line.substring(0, 2).equals("PU")){
            String x, y;
            int z, w;
            x = line.substring(3, 4);
            y = line.substring(4);
            z = hexToDec(x);
            w = hexToDec(y);
            VM.cmdPU(x, y);
        }
        else if(line.substring(0, 2).equals("PO")){
            String x, y;
            int z, w;
            x = line.substring(3, 4);
            y = line.substring(4);
            z = hexToDec(x);
            w = hexToDec(y);
            VM.cmdPO(x, y);
        }
        else if(line.substring(0, 2).equals("JP")){
            String x, y;
            int z, w;
            x = line.substring(3, 4);
            y = line.substring(4);
            z = hexToDec(x);
            w = hexToDec(y);
            VM.cmdJP(x, y);
        }
        else if(line.substring(0, 2).equals("JE")){
            String x, y;
            int z, w;
            x = line.substring(3, 4);
            y = line.substring(4);
            z = hexToDec(x);
            w = hexToDec(y);
            VM.cmdJE(x, y);
        }
        else if(line.substring(0, 4).equals("FORK")){
            VM.cmdFORK();
        }
        else if(line.substring(0, 3).equals("ISP")){
            VM.cmdISP();
        }
        else if(line.substring(0, 4).equals("PRTS")){
            VM.cmdPRTS();
        }
        else if(line.substring(0, 4).equals("PRTN")) {
            VM.cmdPRTN();
        }
    }*/

    public static Integer hexToDec(String s) {
        switch(s.charAt(0)){
            default: return Integer.parseInt(s);
            case 'A': return 10;
            case 'B': return 11;
            case 'C': return 12;
            case 'D': return 13;
            case 'E': return 14;
            case 'F': return 15;
        }
    }

    public void execute() throws FileNotFoundException {
        InputDevice.openFile();

    }


}

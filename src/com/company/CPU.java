package com.company;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class CPU {

    public static final Map<String, Integer> cmdList;
    static {
        Map<String, Integer> tempMap = new HashMap<String, Integer>();
        tempMap.put("ADD", 0);
        tempMap.put("SUB", 0);
        tempMap.put("MUL", 0);
        tempMap.put("DIV", 0);
        tempMap.put("WR", 1);
        tempMap.put("RD", 1);
        tempMap.put("CMP", 0);
        tempMap.put("CPID", 0);
        tempMap.put("LD", 2);
        tempMap.put("PT", 2);
        tempMap.put("PUN", 1);
        tempMap.put("PUS", 1);
        tempMap.put("P", 3);
        tempMap.put("JP", 2);
        tempMap.put("JE", 2);
        tempMap.put("JL", 2);
        tempMap.put("JG", 2);
        tempMap.put("FO", 2);
        cmdList = Collections.unmodifiableMap(tempMap);
    }
    private static int x, y, z;
    public static final int SUPERVISOR = 0;
    public static final int USER = 1;
    // Registers
    private static int PTR;
    private static int PC;
    private static int SP;
    private static int SM;
    private static int PID;
    private static int MODE;
    private static int TI;
    private static int PI;

    // Commands
    private static int SI;
    private static int CH1;
    private static int CH2;
    private static int CH3;
    // Additional variables
    private static int supervisor = 0;
    private static int time = 20;


    // Default constructor
    public CPU() {
        setPTR(0);
        setPC(0);
        setSP(0);
        setSM(0);
        setPID(0);
        setTI(time);
        setPI(0);
        setSI(0);
        setCH1(0);
        setCH2(0);
        setCH3(0);
        setMODE(supervisor);
    }

    // Getters
    public static int getPTR() {
        return PTR;
    }

    // Setters
    public static void setPTR(int PTR) {
        CPU.PTR = PTR;
    }

    public static int getPC() {
        return PC;
    }

    public static void setPC(int PC) {
        CPU.PC = PC;
    }

    public static int getSP() {
        return SP;
    }

    public static void setSP(int SP) {
        CPU.SP = SP;
    }

    public void resetInterrupts() {
        resetTI();
        SI = 0;
        PI = 0;
    }

    public void resetTI() {
        setTI(time);
    }

    public int getInterrupt() {
        if (TI == 0) {
            return 1;
        }

        switch (PI) {
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
        }

        switch (SI) {
            case 1:
                return 6;
            case 2:
                return 7;
            case 3:
                return 8;
            case 4:
                return 9;
            case 5:
                return 10;
            case 6:
                return 11;
        }

        return 0;
    }

    public static void decreaseTI(){
        TI--;
    }

    public int getSM() {
        return SM;
    }

    public static void setSM(int SM) {
        CPU.SM = SM;
    }

    public int getPID() {
        return PID;
    }

    public static void setPID(int PID) {
        CPU.PID = PID;
    }

    public int getMODE() {
        return MODE;
    }

    public static void setMODE(int MODE) {
        CPU.MODE = MODE;
    }

    public int getTI() {
        return TI;
    }

    public static void setTI(int TI) {
        CPU.TI = TI;
    }

    public int getPI() {
        return PI;
    }

    public static void setPI(int PI) {
        CPU.PI = PI;
    }

    public int getSI() {
        return SI;
    }

    public static void setSI(int SI) {
        CPU.SI = SI;
    }

    public int getCH1() {
        return CH1;
    }

    public static void setCH1(int CH1) {
        CPU.CH1 = CH1;
    }

    public int getCH2() {
        return CH2;
    }

    public static void setCH2(int CH2) {
        CPU.CH2 = CH2;
    }

    public int getCH3() {
        return CH3;
    }

    public static void setCH3(int CH3) {
        CPU.CH3 = CH3;
    }
}


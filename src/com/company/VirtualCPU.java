package com.company;

/**
 * Created by suminskutis on 2017-03-26.
 */
public class VirtualCPU {

    private int PC;
    private int SP;
    private int PID;


    public VirtualCPU(){
        setPC(PC);
        setSP(SP);
        setPID(PID);
    }


    public void setPC(int PC) {
        this.PC = PC;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getPC() {
        return PC;
    }

    public int getSP() {
        return SP;
    }

    public int getPID() {
        return PID;
    }
}

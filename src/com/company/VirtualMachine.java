package com.company;

public class VirtualMachine {

    private VirtualCPU virtualCPU;

    private int index;

    private static int x, y, z;
    // Memory
    private VirtualMemory virtualMemory;

    public static final int MEMORY_SIZE = 256;

    final static int DATA_START = 0;
    final static int DATA_SIZE = 112;

    final static int PROGRAM_START = 112;
    final static int PROGRAM_SIZE = 112;

    final static int STACK_START = 224;
    final static int STACK_SIZE = 32;

    final int PC_ADDRESS = 109;
    final int SP_ADDRESS = 110;
    final int PID_ADDRESS = 111;

    // Default constructor
    public VirtualMachine(){
        this.virtualCPU = new VirtualCPU();
        this.virtualMemory = new VirtualMemory(MEMORY_SIZE);
    }

    public VirtualMemory getVirtualMemory(){
        return virtualMemory;
    }

    public void printMemory() {
        for(int i = 0; i < MEMORY_SIZE; i++)
            System.out.println(virtualMemory.getMemory()[i] + " ");
    }

    public int getPC(){
        return Word.wordToInt(PMMU.read(PC_ADDRESS));
    }
    public int getSP(){
        return Word.wordToInt(PMMU.read(SP_ADDRESS));
    }
    public int getPID(){
        return Word.wordToInt(PMMU.read(PID_ADDRESS));
    }

    public void savePC(int PC){
        PMMU.write(Word.intToWord(PC), PC_ADDRESS);
    }
    public void saveSP(int SP){
        PMMU.write(Word.intToWord(SP), SP_ADDRESS);
    }
    public void savePID(int PID){
        PMMU.write(Word.intToWord(PID), PID_ADDRESS);
    }

    public void cmdADD() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) + Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        VirtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdSUB() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) - Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        VirtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdMUL() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) * Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        VirtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdDIV() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) / Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        VirtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdWR(int x) {
        PMMU.write(PMMU.read(SP), x);
        CPU.decreaseTI();
    }

    public void cmdRD(int x) {
        PMMU.write(PMMU.read(x), SP);
        VirtualCPU.decreaseSP();
        CPU.decreaseTI();
    }



    public void cmdPUSH(int x, int y) {
        PMMU.write(PMMU.read(RealMachine.VM_SIZE_IN_BLOCKS * x + y), SP);
        VirtualCPU.increaseSP();
        CPU.decreaseTI();
    }

    public void cmdPOP(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.DATA_START+VirtualMachine.DATA_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.DATA_START){
            CPU.setPI(1);
            return;
        }
        VirtualCPU.increaseSP();
        PMMU.write(PMMU.read(SP), RealMachine.VM_SIZE_IN_BLOCKS * x + y);
        CPU.decreaseTI();
    }

    public void cmdPRTN() {
        CPU.decreaseTI();
        CPU.setSI(2);
    }

    public void cmdPRTS(){
        CPU.decreaseTI();
        CPU.setSI(1);
    }

    /*public void cmdJP(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.PROGRAM_START+VirtualMachine.PROGRAM_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.PROGRAM_START){
            CPU.setPI(1);
            return;
        }
        CPU.setPC(PMMU.WORDS_IN_BLOCK * x + y);
        TI--;
    }

    public void cmdJE(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.PROGRAM_START+VirtualMachine.PROGRAM_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.PROGRAM_START){
            CPU.setPI(1);
            return;
        }
        if (Word.wordToInt(main.PMMU.read(SP)) == 1) {
            CPU.setPC(PMMU.WORDS_IN_BLOCK * x + y);
            SP--;
        }
        TI--;
    }*/

    /* REIKIA EDITINTI
    public void cmdST(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) == VirtualMachine.DATA_START+VirtualMachine.DATA_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.DATA_START){
            CPU.setPI(1);
            return;
        }
        int PID = Word.wordToInt(PMMU.read(PMMU.WORDS_IN_BLOCK * x + y));
        if (PID != 0) {
            PMMU.write(Word.intToWord(0), PMMU.WORDS_IN_BLOCK * x + y);
            RealMachine.killVirtualMachine(PID);
        }
        TI--;
    }
    */
    public static void cmdSTOPF() {// BESALYGINIO SUSTOJIMO KOMANDA -> PAS MUS HALT
        CPU.setSI(5);

    }

    public static void cmdREAD() {

        CPU.decreaseTI();
        CPU.setSI(4);
    }
}


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

    public int getPC() throws CloneNotSupportedException {
        return Word.wordToInt(virtualMemory.read(PC_ADDRESS));
    }
    public int getSP() throws CloneNotSupportedException {
        return Word.wordToInt(virtualMemory.read(SP_ADDRESS));
    }
    public int getPID() throws CloneNotSupportedException {
        return Word.wordToInt(virtualMemory.read(PID_ADDRESS));
    }

    public void savePC(int PC) throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(PC), PC_ADDRESS);
    }
    public void saveSP(int SP) throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(SP), SP_ADDRESS);
    }
    public void savePID(int PID) throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(PID), PID_ADDRESS);
    }

    public void cmdADD() throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(Word.wordToInt(virtualMemory.read(getSP())) + Word.wordToInt(virtualMemory.read(getSP() - 1))), getSP() - 1);
        virtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdSUB() throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(Word.wordToInt(virtualMemory.read(getSP())) - Word.wordToInt(virtualMemory.read(getSP() - 1))), getSP() - 1);
        virtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdMUL() throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(Word.wordToInt(virtualMemory.read(getSP())) * Word.wordToInt(virtualMemory.read(getSP() - 1))), getSP() - 1);
        virtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdDIV() throws CloneNotSupportedException {
        virtualMemory.write(Word.intToWord(Word.wordToInt(virtualMemory.read(getSP())) / Word.wordToInt(virtualMemory.read(getSP() - 1))), getSP() - 1);
        virtualCPU.decreaseSP();
        CPU.decreaseTI();
    }

    public void cmdWR(int x) throws CloneNotSupportedException {
        virtualMemory.write(virtualMemory.read(getSP()), x);
        CPU.decreaseTI();
    }

    public void cmdRD(int x) throws CloneNotSupportedException {
        virtualMemory.write(virtualMemory.read(x), getSP());
        virtualCPU.decreaseSP();
        CPU.decreaseTI();
    }



    public void cmdPUSH(int x, int y) throws CloneNotSupportedException {
        virtualMemory.write(virtualMemory.read(RealMachine.VM_SIZE_IN_BLOCKS * x + y), getSP());
        virtualCPU.increaseSP();
        CPU.decreaseTI();
    }

    public void cmdPOP(int x, int y) throws CloneNotSupportedException {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.DATA_START+VirtualMachine.DATA_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.DATA_START){
            CPU.setPI(1);
            return;
        }
        virtualCPU.increaseSP();
        virtualMemory.write(virtualMemory.read(getSP()), RealMachine.VM_SIZE_IN_BLOCKS * x + y);
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

  /*  public int getSP(){
        return virtualCPU.getSP();
    }*/
}


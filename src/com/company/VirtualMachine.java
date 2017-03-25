package main;

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

    //REGISTRAI -> Veliau tikrinti gaunamas reiksmes
    private static int SP;
    private static int TI;
    private static int SI;
    private static int PID;

    // Default constructor
    public VirtualMachine(int index){
        this.virtualCPU = new VirtualCPU();
        this.virtualMemory = new VirtualMemory(MEMORY_SIZE);
        this.index = index;
        this.SP = CPU.getSP();
        this.TI = CPU.getTI();
        this.SI = CPU.getSI();
        this.PID = CPU.getPID();
    }

    public VirtualMemory getVirtualMemory(){
        return virtualMemory;
    }

    public void printMemory() {
        for(int i = 0; i < MEMORY_SIZE; i++)
            System.out.println(virtualMemory.getMemory()[i] + " ");
    }

    public VirtualMachine clone(){
        VirtualMachine VM = RealMachine.createVirtualMachine();
        VirtualMemory virtualMemory = VM.getVirtualMemory();

        for(int i = 0; i < MEMORY_SIZE; i++){
            this.virtualMemory.write(PMMU.read(i), i);
            virtualMemory.write(this.virtualMemory.read(i), i);
        }


        return VM;
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
    public int getIndex(){
        return index;
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
        SP--;
        TI--;
    }

    public void cmdSUB() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) - Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        SP--;
        TI--;
    }

    public void cmdMUL() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) * Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        SP--;
        TI--;
    }

    public void cmdDIV() {
        PMMU.write(Word.intToWord(Word.wordToInt(PMMU.read(SP)) / Word.wordToInt(PMMU.read(SP - 1))), SP - 1);
        SP--;
        TI--;
    }

    public void cmdWR(int x) {
        PMMU.write(PMMU.read(SP), x);
        TI--;
    }

    public void cmdRD(int x) {
        PMMU.write(PMMU.read(x), SP);
        SP--;
        TI--;
    }
    /* NEREIKALINGA
    public void cmdCMP() {
        //System.out.println(Word.wordToInt(PMMU.read(SP)) + ">" + Word.wordToInt(PMMU.read(SP-1)));
        if (Word.wordToInt(PMMU.read(SP)) > Word.wordToInt(PMMU.read(SP - 1))) {
            PMMU.write(Word.intToWord(0), SP);
        } else if (Word.wordToInt(PMMU.read(SP)) == Word.wordToInt(PMMU.read(SP - 1))) {
            PMMU.write(Word.intToWord(1), SP);
        } else {
            PMMU.write(Word.intToWord(2), SP);
        }
        //SP++;
        TI--;
    }

    public void cmdCPID() {
        if (Word.wordToInt(PMMU.read(SP)) != PID) {
            PMMU.write(Word.intToWord(0), SP);
        } else {
            PMMU.write(Word.intToWord(1), SP);
        }
        //SP--;
        TI--;
    }

    public void cmdLD(int x, int y) {
        PMMU.write(PMMU.read(RealMachine.VM_SIZE_IN_BLOCKS * x + y), SP);
        SP++;
        TI--;
    }

    public void cmdPT(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.DATA_START+VirtualMachine.DATA_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.DATA_START){
            CPU.setPI(1);
            return;
        }
        SP++;
        PMMU.write(PMMU.read(SP), RealMachine.VM_SIZE_IN_BLOCKS * x + y);
        TI--;
    }

    public void cmdPUN(int x) {
        SP++;
        main.PMMU.write(Word.intToWord(x), SP);
        TI--;
    }

    public void cmdPUS(Word x) {
        SP++;
        main.PMMU.write(x, SP);
        TI--;
    }
    */
    public void cmdPRTN() {
        TI -= 3;
        SI = 2;
    }

    public void cmdPRTS(){
        TI -= 3;
        SI = 1;
    }
    /* NEREIKALINGA
    public void cmdP(int x, int y, int z){
        if(x < 0 || x > 6 || y >= z) {
            CPU.setPI(1);
            return;
        }
        this.x = x;
        this.y = y;
        this.z = z;
        TI -= 3;
        SI = 3;
    }
    */
    public void cmdJP(int x, int y) {
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
    }
    /* JUMPAI NEREIKALINGI SIUO METU
    public void cmdJL(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.PROGRAM_START+VirtualMachine.PROGRAM_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.PROGRAM_START){
            CPU.setPI(1);
            return;
        }
        if (Word.wordToInt(main.PMMU.read(SP)) == 0) {
            CPU.setPC(PMMU.WORDS_IN_BLOCK * x + y);
            SP--;
        }
        TI--;
    }

    public void cmdJG(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.PROGRAM_START+VirtualMachine.PROGRAM_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.PROGRAM_START){
            CPU.setPI(1);
            return;
        }
        if (Word.wordToInt(main.PMMU.read(SP)) == 2) {
            CPU.setPC(PMMU.WORDS_IN_BLOCK * x + y);
            SP--;
        }
        TI--;
    }
    */
    public void cmdFO(int x, int y) {
        if((RealMachine.VM_SIZE_IN_BLOCKS * x + y) > VirtualMachine.DATA_START+VirtualMachine.DATA_SIZE || (RealMachine.VM_SIZE_IN_BLOCKS * x + y) < VirtualMachine.DATA_START){
            CPU.setPI(1);
            return;
        }
        this.x = x;
        this.y = y;
        TI--;
        SI = 6;
    }
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

        SI = 5;

    }

    public static void cmdREAD() {

        TI -= 3;
        SI = 4;
    }
}

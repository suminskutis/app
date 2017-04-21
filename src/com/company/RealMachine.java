package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class RealMachine {

    private CPU cpu;
    private Memory memory;
    private VirtualMachine virtualMachine;
    public final static int VM_SIZE_IN_BLOCKS = 16;


    public RealMachine() {
        cpu = new CPU();
        memory = new Memory();
    }
	/*
	public void Identify(String line, VirtualMachine VM) throws CloneNotSupportedException {
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
            VM.cmdPUSH(x, y);
        }
        else if(line.substring(0, 2).equals("PO")){
            String x, y;
            int z, w;
            x = line.substring(3, 4);
            y = line.substring(4);
            z = hexToDec(x);
            w = hexToDec(y);
            VM.cmdPOP(x, y);
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
        switch (s.charAt(0)) {
            default:
                return Integer.parseInt(s);
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;

        }
    }

    public void execute() throws IOException, CloneNotSupportedException {
        /*TODO aplamai
        * showMemory();
        * showRegisters(); Yra githube pirmi du metodai
        * Perziuret ar VM komandos geros|| Pagal 1a dokumenta pasizet
        * executeCommands(); funkcija vykdys komandas is virtualios atminties
        *
        *
        * */


        // if flashregistras = true then

        //InputDevice.openFile(); Gal ir kitos funkcijos reikia, Pradesim daryt suprasim
        //TODO surasom i SUPERVISOR atminti

        // patikrinam ar sintaksiskai gera programa ->> jeigu neteisinga PI=4 ....kvieciamas pertraukimas, atitinkantis sita klaida(CIA VELIAU JAUCIU SITAS)
        //TODO InputValidator pratikrins sintaxe programos

        // sukopijuojam flasho programa i HDD (visa nuo pradzios iki pabaigos, nesvarbu kur duomenu, kur code blokas)
        //TODO ""sita darysim tik tada kai veiks su viena programa"" Reikia pasidaryt start ir end taskus, kad zinotumeme kur prasideda ir kur baigiasi pirma, antra, trecia,..... programos


        virtualMachine = new VirtualMachine(); // kazkaip reikia pazymeti, kad realios atminties dalis tapo uzimta, nes isskyrem VIRTUAL MEMORY dali

        InputDevice.openFile(); // sitas turetu eiti pacioj EXECUTE pradzioje ->> surasytumeme i SUPERVISOR atminti.
                                // cia jau skaitysime programa is HDD, o ne is flasho.
        Word[] words;

        String line;
        VirtualMemory mem  = virtualMachine.getVirtualMemory();
        int counter = VirtualMachine.DATA_START;
            line = Word.wordsToString(InputDevice.getInput());
            if (line.equals("DATA")) {
                CPU.setCH1(1);
                words = InputDevice.getInput();
                CPU.setCH1(0);
                line = Word.wordsToString(words);
                while (!(line.equals("CODE"))) {
                    for (Word w : words) {
                                mem.write(w, VirtualMachine.DATA_START + counter++);
                    }
                    CPU.setCH1(1);
                    words = InputDevice.getInput();
                    CPU.setCH1(0);
                    line = Word.wordsToString(words);
                }

            }
        counter = 0;
        CPU.setCH1(1);
        words = InputDevice.getInput();
        CPU.setCH1(0);
        line = Word.wordsToString(words);
        while (!line.equals("HALT")) {
            for (Word w : words) {
                       mem.write(w, VirtualMachine.PROGRAM_START + counter++);

            }
            CPU.setCH1(1);
            words = InputDevice.getInput();
            CPU.setCH1(0);
            line = Word.wordsToString(words);
        }
        virtualMachine.printMemory();
    }


    }


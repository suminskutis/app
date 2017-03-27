package com.company;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class VirtualMemory {
    Word[] memory;
    int size = 256;

    public VirtualMemory(int size) {
        memory = new Word[size];
    }

    public Word[] getMemory(){
        return this.memory;
    }

    public Word read(int address) {
        return memory[address];
    }
    public void write(Word word, int address) {
        this.memory[address] = word;
    }
    public void print() throws CloneNotSupportedException {
        //String output = "";
        for(int i = 0; i < size; i++){
            System.out.println(i + ": " + Word.wordToInt(memory[i]));
        }
    }
}

package com.company;

/**
 * Created by suminskutis on 2017-03-26.
 */
public class HDD {
    Word[] memory;
    int size = 65536;

    public HDD() {
        memory = new Word[size];
    }

    public Word[] getMemory(){
        return memory;
    }

    public Word read(int address) {
        return memory[address];
    }
    public void write(Word word, int address) {
        memory[address] = word;
    }
}

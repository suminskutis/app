package com.company;

import java.util.Arrays;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class Memory {

    private int size = 4096;
    private Word[] memory;

    //užpildom atmintį nurodytu žodžių skaičiumi
    public Memory(){
        if (size > 0){
            this.size = size;
            memory = new Word[size];
            for (int i = 0; i < size; i++){
                memory[i] = new Word();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }


    public Word read(int address) throws CloneNotSupportedException {
        return memory[address].clone();
    }
    //?????????????? kodėl čia duodam clone?
    public void write(Word word, int address) throws CloneNotSupportedException {
        memory[address] = word.clone();

    }

    public int getSize(){
        return size;
    }

    public Word[] getMemory(){
        return Arrays.copyOf(memory, size);
    }

    public void print() throws CloneNotSupportedException {
        String output = "";
        for(int i = 0; i < this.size; i++){
            System.out.println(i + ": " + Word.wordToInt(memory[i]));
        }
    }
    public void printBlock(int address) throws CloneNotSupportedException {
        String output = "";
        for(int i = address; i < address + 16; i++){
            System.out.println(i + ": " + Word.wordToInt(memory[i]));
        }
    }
}

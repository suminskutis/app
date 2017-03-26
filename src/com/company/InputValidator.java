package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;



import sun.misc.IOUtils;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class InputValidator {
	
	public boolean validateString (String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		boolean DATASEG_found = false;
		boolean INTEGERS_found = false;
		boolean CODESEG_found = false;
		boolean HALT_found = false;
		boolean CMDS_found = false;
		
		int DATASEG_id = 0;
		int CODESEG_id = 0;
		int HALT_id = 0;
		 
		String[] File_Array = new String[30]; //FAILO DYDIS EILUTEM
		String[] SEGS_Array = {"DATASEG","CODESEG"};
		String[] EXITCMDS_Array = {"HALT"};
		String[] CMDS_Array = {"ADD", "SUB", "DIV", "MUL", "PRTS", "PU"};
		int CMDS_Array_Count = CMDS_Array.length; 

//			------------------------    READING FILE INTO ARRAY    ------------------------
		    int linecounter = 0;
		    String line = br.readLine();
		    while (line != null) {
		        File_Array[linecounter] = line;
		        line = br.readLine();
		        linecounter++;
		    }
		    br.close();
/*		    TESTINIS PRINTERIS 
		    for(int i = 0; i< linecounter; i++)
			{
				System.out.println(File_Array[i]);
			}
*/		    //TESTINIS PRINTERIS 
//			------------------------    DATASEG  VALIDATION    ------------------------
		    if(File_Array[0].equals(SEGS_Array[0]) ) //CHECK IF DATASEG is in place
		    {
		    	DATASEG_found = true;
//		    	System.out.println("DATASEG radau");
		    }
		    else return false; // No Data segment found = exit
//			------------------------    CODESEG & HALT VALIDATION    ------------------------
		    for(int i = DATASEG_id; i < linecounter; i++) // Looking for CODESEG and HALT command
		    {
		    	if(File_Array[i].equals(SEGS_Array[1])) //CODESEG
		    	{
		    		CODESEG_found = true;
		    		CODESEG_id = i;
//		    		System.out.println("CODESEG radau");
		    	}
		    	if(File_Array[i].equals(EXITCMDS_Array[0])) //HALT cmd
		    	{
		    		HALT_found = true;
		    		HALT_id = i;
//		    		System.out.println("HALT radau");
		    	}
		    }
		    if(!CODESEG_found || !HALT_found) return false; // No Code segment or HALT command found = exit
		    if(File_Array[HALT_id + 1] != null) //Assuming that HALT command is last if not = exit
		    {
		    	return false; 
		    }
//		    ------------------------    DATASEG VALUES VALIDATION    ------------------------
		    for(int i = DATASEG_id + 1; i < CODESEG_id; i++) // Looking for DATASEG values
		    {
		    	int numeric = Integer.parseInt(File_Array[i]); // Throws exception if its not int
		    	int length = File_Array[i].length();
		    	//System.out.println("int repo: " + numeric + " ilgis: " + length);
				if(length == 4 && numeric > 0)
				{
					INTEGERS_found = true;
				}
				else return false;	// Found not numeric or zero value = exit	    				
		    }
//			------------------------    CODESEG VALUES VALIDATION    ------------------------		    
		    for(int i = CODESEG_id + 1; i < HALT_id; i++) // Looking for CODESEG Commands
		    {
		    	//System.out.println("Comanda radau: " + File_Array[i]);
		    	boolean CMD_found = false;
//		    	System.out.println("Imu: " + File_Array[i]);
		    	for(int j = 0; j < CMDS_Array_Count; j++)
		    	{
//		    		System.out.println("Ieskau");
		    		if(File_Array[i].contains(CMDS_Array[j]))
		    		{
		    			CMD_found = true;
//		    			System.out.println("Comanda radau: " + File_Array[i]);
		    		}
		    	}
		    	if(!CMD_found) // Command checked didn't match any accepted commads = exit
		    	{
//		    		System.out.println("NIEKO GERO");
		    		return false;
		    	}
		    }
		    CMDS_found = true;
		    return true; // FILE VALIDATION SUCCESSFUL!
	}
}
/**
((4+3)*5) / 20

DATASEG
0004
0003
0005
0020
CODESEG
PU01
PU02
ADD
PU03
MUL
PU04
DIV
PRTS
HALT

*/
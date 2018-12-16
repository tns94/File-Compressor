package com.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;





public class Main {
	
	
	
public static void main(String[] args) throws IOException {
	
	Scanner sc=new Scanner(System.in);
	String cmd="";
	while(true)
	{
	System.out.println(" A : to compress");
	System.out.println(" B : to Decompress");
	System.out.println(" C : to EXIT");
	cmd=sc.next();
    if(cmd.equals("A"))
    {
    	System.out.println("Enter file Path to compress");
    			Compressor.compress(new File(sc.next()));
    	
    }
    else if(cmd.equals("B"))
    {
    	System.out.println("Enter file Path to Decompress");
    			Decompressor.decompress(new File(sc.next()));
    	
    }
    else if(cmd.equals("C"))
    {
    	System.exit(0);
    }
    else
    	System.out.println("Invalid Input");
	}
}


}

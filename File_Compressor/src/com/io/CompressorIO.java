package com.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.error.ErrorHandler;
import com.error.Error;
import com.pojo.Node;

public class CompressorIO {
	
	public static byte[] readFileinByte(File file)
	{
		byte[] buf=null;
		try {
	        buf=Files.readAllBytes(file.toPath());
	       
	     } catch (IOException i) {
	        i.printStackTrace();
	        
	     } 
		
		return buf;
	
	}
	
	
	
	public static void writeBitSetFile(byte[] byteArray,Node tree,String filePath)
	{	
		File file=new File(filePath+".tns");
		FileOutputStream fos = null;
		 try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			   
		
		try {
		ObjectOutputStream out = new ObjectOutputStream(fos);
	    out.writeObject(tree);
	   
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		    try {
				baos.write(byteArray);
				baos.writeTo(fos);
				fos.close();
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}
	public static Object[] readCompressedFile(String  filePath)
	{
		File file=new File(filePath);
		Node node=null;
		byte[] buf=null;
		try {
	        FileInputStream fileIn = new FileInputStream(file);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        node = (Node) in.readObject();
	        buf=new byte[node.getFrequency()];
	        fileIn.read(buf);
	        fileIn.close();
	        in.close();
	       
	     } catch (IOException i) {
	        i.printStackTrace();
	        
	     } catch (ClassNotFoundException c) {
	        c.printStackTrace();
	       
	     }
		
		Object[] objArray=new Object[2];
		objArray[0]=node;
		objArray[1]=buf;
		return objArray;
		
		  
	}
	
	
	static void writeDecompressedFile(String filePath,byte[]  data)
	{
		
		try {
			FileOutputStream fos=new FileOutputStream(new File(filePath.substring(0,filePath.length()-4)));
		fos.write(data);
		fos.flush();
		
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	//------------------------------
	
	void getByteReader(String filePath)
	{
		File file =new File(filePath);
		try {
			FileInputStream fis=new FileInputStream(file);
		} catch (FileNotFoundException e) {
			ErrorHandler.throwError(new Error(e.getMessage()));
		}
		
	}
	
}

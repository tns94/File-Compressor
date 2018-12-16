package com.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import com.io.CompressorIO;
import com.pojo.Node;

public class Decompressor {
	
	static void decompress(File file)
	{
		String filePath=file.getAbsolutePath();
		Object[] objArray=CompressorIO.readCompressedFile(filePath);
		Node decmnodetree=(Node)objArray[0];
		byte[]decmArray=(byte[])objArray[1];
		
		Decompressor.getDecompressed(decmnodetree, decmArray,filePath);
		//CompressorIO.writeDecompressedFile(filePath, data);
	}
	
	static void  getDecompressed(Node nodetree,byte[] byteArray,String filePath)
	{
		BitSet bs=BitSet.valueOf(byteArray);
		List<Byte> list=new ArrayList<Byte>();
		boolean bit;
		byte buf[]=new byte[1];
		
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(new File(filePath.substring(0,filePath.length()-4)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Node node=nodetree;
		for (int i = 0; i < bs.length()-1; i++) {
			bit=bs.get(i);	
				if(bit)
				{
					node=node.getRight();
					if(node.getLeft()==null && node.getRight()==null)//node.isLeaf())
						{
						list.add(node.getCh());
						node=nodetree;
						}
				}
				else
				{
					node=node.getLeft();
					if(node.getLeft()==null && node.getRight()==null)//node.isLeaf())
						{
						list.add(node.getCh());
						node=nodetree;
						}
				}
			
				int len=bs.length();
				if (list.size()>4096 || i== len-2)
				{
					buf=new byte[list.size()];
					for (int j = 0; j < list.size(); j++) {
						buf[j]=list.get(j);
					}
				try {
					fos.write(buf);
					fos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				list.clear();
				}
				
		}
		
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	return  buf;
	}
}

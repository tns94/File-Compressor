package com.main;

import java.io.File;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import com.io.CompressorIO;
import com.pojo.Node;

public class Compressor {
	static Map<Byte,StringBuilder> indexMap=new HashMap<Byte, StringBuilder>();
	static void compress(File file){

		byte[] filebuf=CompressorIO.readFileinByte(file);
		
		Map<Byte,Integer> frequencyMap=getFrequencyMap(filebuf); 
		
		PriorityQueue<Node> pq= getPriorityQueue(frequencyMap); 
		
		Node huffmanTree= getHuffmanTree(pq);
	
		StringBuilder comstr=new StringBuilder();
		encodingMap(huffmanTree,comstr);
		
		BitSet  bs=getEncodedString(filebuf);
		
		byte[] byteArray=bs.toByteArray();
		huffmanTree.setFrequency(byteArray.length); // reusing variable frequency to store length of compressed byte array   
		
		CompressorIO.writeBitSetFile(bs.toByteArray(), huffmanTree,file.getAbsolutePath());
		System.out.println("Original file size : "+filebuf.length+" bytes");
		System.out.println("Compressed file size : "+byteArray.length+" bytes");
}
static Map<Byte,Integer> getFrequencyMap(byte[] filebuf)
{
	Map<Byte,Integer> frequencyMap=new HashMap<>();
	for (int i = 0; i < filebuf.length; i++)   //Build frequencyMap
	{
		if (frequencyMap.containsKey(filebuf[i]))
			frequencyMap.put(filebuf[i], frequencyMap.get(filebuf[i])+1);
		else
			frequencyMap.put(filebuf[i],1);
		
	}
	return frequencyMap;
}
static PriorityQueue<Node> getPriorityQueue(Map<Byte,Integer> frequencyMap)
{
	PriorityQueue<Node> pq=new PriorityQueue<Node>(); 
	for (byte c:frequencyMap.keySet()) {   // Build Priority Queue
		Node node=new Node();
		node.setCh(c);
		node.setFrequency(frequencyMap.get(c));
		pq.offer(node);
	}
	return pq;
}
	
static Node getHuffmanTree(PriorityQueue<Node> pq)
{
	while(pq.size()>1)   //Build Huffman Tree
	{
		Node n1=pq.poll();
		Node n2=pq.poll();
		
		Node n3=new Node();
		
		n3.setFrequency(n1.getFrequency()+n2.getFrequency());
		n3.setLeft(n1);
		n3.setRight(n2);
		//n3.setLeaf(false);
		pq.offer(n3);
	}
	return pq.peek();
	
}
static void encodingMap(Node node,StringBuilder comst)
{
	StringBuilder comstr=new StringBuilder(comst);
	if (node.getLeft()==null && node.getRight()==null)//node.isLeaf())
	{
		indexMap.put(node.getCh(), comstr);
		return;
	}
	
		if(node.getLeft()!=null)
		{
			comstr.append("0");
			encodingMap(node.getLeft(),comstr);
		}
		comstr.deleteCharAt(comstr.length()-1);
		if(node.getRight()!=null)
		{
			comstr.append("1");	
			encodingMap(node.getRight(),comstr);
		}
}

static BitSet getEncodedString(byte[] filebuf)
{
	int l=0;
	BitSet  bs=new BitSet();
	StringBuilder comstr=new StringBuilder();
	for (int i = 0; i < filebuf.length; i++) 
	{
		comstr=indexMap.get(filebuf[i]);
		for (int j = 0; j < comstr.length(); j++) {
			char c=comstr.charAt(j);
			if (c=='0')
				bs.set(l+j, false);
			else
				bs.set(l+j,true);
		}
		l+=comstr.length();
		
	}
	bs.set(l+1,true);
	
	return bs;
}
/* Implement Tree without frequencies
 * frequencies are overhead in compressed file, since they are no further required after building huffman tree
 * 
static Node makeWritableTree(Node node)
{
	if (node.isLeaf())
	{
		
		return;
	}
	
		if(node.getLeft()!=null)
		{
			
			makeWritableTree(node.getLeft());
		}
		if(node.getRight()!=null)
		{
			makeWritableTree(node.getRight());
		}
}*/
}

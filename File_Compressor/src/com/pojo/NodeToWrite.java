package com.pojo;

import java.io.Serializable;
import java.util.*;

public class NodeToWrite implements Serializable{
	private byte ch;
	//private boolean leaf;
	/*public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}*/
	private NodeToWrite left;
	private NodeToWrite right;


	public byte getCh() {
		return ch;
	}
	public void setCh(byte ch) {
		this.ch = ch;
	}
	
	public NodeToWrite getLeft() {
		return left;
	}
	public void setLeft(NodeToWrite left) {
		this.left = left;
	}
	public NodeToWrite getRight() {
		return right;
	}
	public void setRight(NodeToWrite right) {
		this.right = right;
	}
	
@Override
public boolean equals(Object obj) {
	if (((NodeToWrite)obj).getCh()==this.getCh())
		return true;
	else
		return false;
}

@Override
public int hashCode() {
	return this.getCh();
}

	}

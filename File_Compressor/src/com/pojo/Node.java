package com.pojo;

import java.io.Serializable;



public class Node implements Comparable<Node>,Serializable{
private byte ch;
private int frequency;
private Node left;
private Node right;


/*private boolean leaf;
public boolean isLeaf() {
	return leaf;
}
public void setLeaf(boolean leaf) {
	this.leaf = leaf;
}
*/
public byte getCh() {
	return ch;
}
public void setCh(byte ch) {
	this.ch = ch;
}
public int getFrequency() {
	return frequency;
}
public void setFrequency(int frequency) {
	this.frequency = frequency;
}
public Node getLeft() {
	return left;
}
public void setLeft(Node left) {
	this.left = left;
}
public Node getRight() {
	return right;
}
public void setRight(Node right) {
	this.right = right;
}
@Override
public int compareTo(Node o) {
	return this.getFrequency()-o.getFrequency();
	
}



}

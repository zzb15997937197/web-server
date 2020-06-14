package com.webserver.demo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class charsetDemo {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String line="易";
		byte[] data=line.getBytes("UTF-8");
		System.out.println(Arrays.toString(data));
		String a=Integer.toBinaryString(-24);
		System.out.println(a);
		//10111+11000
		/*
		 *   -24
		 *   求23的二进制然后按位取反
		 *   23=16+4+2+1
		 *   00010111
		 *   取反：11101000
		 *   1 0010111+1 =1 0011000 -24
		 */
	}

}

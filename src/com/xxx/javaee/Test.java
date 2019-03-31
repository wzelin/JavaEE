package com.xxx.javaee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("D:/ccc.txt");
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        int number = 1;
        while ((str = br.readLine()) != null){
           System.out.print("{\n\tindex:"
                   +number+",\n\tname:\""+
                   str.split("\\s")[0]+
                   "\"\n\t},");
            number++;
        }
    }
}


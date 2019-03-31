package com.xxx.javaee.test;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class Test2 {
    public static void main(String[] args) {
        Reader r = null;
        Writer w = null;
        Set<Kaoshi2> set = new TreeSet<>();
        try {
            r = new FileReader("D:/Kaoshi2.txt");
            w = new FileWriter("D:/Kaoshi2.txt",true);
            char[] c = new char[8];
            int len = -1;
            while ((len =r.read(c)) != -1){
                String s = new String(c,0,len);
                System.out.print(s);
                set.add(new Kaoshi2(s.split(",")[0],new Integer(s.split(",")[1].substring(0,2))));
            }
          for(Kaoshi2 k :set){
              w.write("\r\n"+k.toString());
          }
            w.flush();
            System.out.println("OK");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                r.close();
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

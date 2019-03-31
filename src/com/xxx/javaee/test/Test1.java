package com.xxx.javaee.test;

public class Test1 {
    public static void main(String[] args) {
        int[] ints = {15,200,3,47,63,78,94,10};
        double ave = 0;//平均值
        int length = ints.length;
        for(int i = 0;i < length;i++){
            ave += ints[i];
        }
        ave/= length;
        int less = 0;//小于平均值个数
        int big = 0;//大于平均值个数
        for(int i = 0;i < length;i++){
            if(ints[i] < ave){
                less++;
            }else if(ints[i] > ave){
                big++;
            }
        }
        System.out.println("数组平均数为："+ave+"大于平均值个数为："+big+"小于平均值个数为："+less);
    }
}

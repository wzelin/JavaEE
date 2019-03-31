package com.xxx.javaee.test;

import java.util.Objects;

public class Kaoshi2 implements Comparable<Kaoshi2> {
    private String name;
    private int age;

    public Kaoshi2() {
    }

    public Kaoshi2(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kaoshi2 kaoshi2 = (Kaoshi2) o;
        return age == kaoshi2.age &&
                Objects.equals(name, kaoshi2.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return  name+":"+age;
    }

    @Override
    public int compareTo(Kaoshi2 o) {
        if(this.age > o.getAge()){
            return -1;
        }else if(this.age < o.getAge()){
            return 1;
        }else{
            return this.name.compareTo(o.getName());
        }
    }
}

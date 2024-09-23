package org.example.springa.users;

public class user {
    private String name;
    private int age;
    private double salary;
    private char gender;
    public user(){

    }
    public user(String name, int age, double salary, char gender) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }



    public void setGender(char gender) {
        this.gender = gender;
    }
}

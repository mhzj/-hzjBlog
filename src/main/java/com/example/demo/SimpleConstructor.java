package com.example.demo;

class AllOps{
  AllOps(int i){
    System.out.println("Creating Rock number"+i);
  }
}

public class SimpleConstructor{
  public static void main(String[] args){
    for (int i=0;i<10;i++){
      new AllOps(i);
    }
  }
}
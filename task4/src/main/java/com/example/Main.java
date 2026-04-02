package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = scanUrl();
        List<Integer> arr = new ArrayList<>();
        arr = parserFile(url);

        for(Integer i : arr){
            System.out.println(i);
        }
    }

    public static String scanUrl(){
        String url = new String();
        boolean flag = true;

        do{
            System.out.print("Enter file path: ");
            Scanner in = new Scanner(System.in);
            url = in.nextLine();
            File file = new File(url);
            if(file.exists() && file.isFile())
                flag = false;
            else
                System.out.println("The entered file path is incorrect");

        }while (flag);

        return url;
    }

    public static List<Integer> parserFile(String url){
        List<Integer> arr = new ArrayList<>();
        try(Scanner in = new Scanner(new File(url))){
            while (in.hasNextInt())
                arr.add(in.nextInt());
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return arr;
    }

}
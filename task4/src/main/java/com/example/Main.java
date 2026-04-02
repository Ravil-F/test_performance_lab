package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = scanUrl();
        List<Integer> arr = new ArrayList<>();
        arr = parserFile(url);
        int res = minNumberMove(arr);
        if( res > 20)
            System.out.println("More than 20 moves");
        else
            System.out.println("Minimum number of moves = " + res);
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

    public static int minNumberMove(List<Integer> arr){
        int numb = 0;
        int max = arr.stream()
                .max((a, b) -> a - b)
                .orElseThrow();

        int sub_max = arr.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElseThrow();

        numb = findMove(arr, max, sub_max);
        return numb;
    }

    private static int findMove(List<Integer> arr, int max, int sub){
        int numb = max - sub;
        for(int i = 0; i < arr.size(); i++){
            if((arr.get(i) != max) && (arr.get(i) != sub))
                numb = numb + (sub - arr.get(i));
        }
        return numb;
    }

}
package com.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        String file1 = scanPaths();
        String file2 = scanPaths();

        int[] center = new int[2];
        int[] radius = new int[2];
        scanFile(file1, center, radius);

        for(int i = 0; i < 2; i++){
            System.out.println("center[" + i + "] = " + center[i]
                                + " radius[" + i + "] = " + radius[i]);
        }

        List<Integer> res = logicMain(file2, center, radius);
        for(Integer i : res){
            System.out.println(i);
        }

    }

    public static String scanPaths(){
        boolean flag = true;
        String file_url = new String();
        do {
            System.out.print("Enter file path ");
            Scanner in = new Scanner(System.in);
            file_url = in.nextLine();
            File file = new File(file_url);
            if(file.exists() && file.isFile()) {
                flag = false;
            }
            else
                System.out.println("The entered file path is incorrect");
        }while (flag);

        return file_url;
    }

    public static void scanFile(String path, int[] center, int[]radius){
        try(Scanner scanner = new Scanner(new File(path))){
            String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            if(!line1.isEmpty() && !line2.isEmpty()) {
                String[] res1 = line1.split(" ");
                String[] res2 = line2.split(" ");

                center = toLineInNumber(center, res1);
                radius = toLineInNumber(radius, res2);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());;
        }
    }

    private static int[] toLineInNumber(int[] array, String[] line){
        for(int i = 0; i < 2; i++){
            try {
                array[i] = Integer.parseInt(line[i]);
            }catch (NumberFormatException e){
                System.out.println("Error number format exceprion: " + e.getMessage());
            }
        }
        return array;
    }

    public static List<Integer> logicMain(String file2, int[] center, int[] radius){
        List<Integer> list = new ArrayList<>();
        try(Scanner in = new Scanner(new File (file2))){
            while (in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
                int[] xy = parserLine(line);
                list.add(formulaElips(center[0], center[1],
                                        radius[0], radius[1],
                                        xy[0], xy[1]));
            }
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    private static int[] parserLine(String line){
        int[] arr = new int[2];
        if (!line.isEmpty()) {
            String[] lines = line.split(" ");
            int i = 0;
            for (String s : lines) {
                try {
                    arr[i] = (Integer.parseInt(s));
                    i++;
                } catch (NumberFormatException e) {
                    System.out.println("Error number format exception: " + e.getMessage());
                }
            }
        }
        return arr;
    }

    private static Integer formulaElips(int h, int k, int a, int b, int x, int y){
        Integer res = -1;

        double formula = (Math.pow((x -h), 2) / Math.pow(a, 2) )
                + (Math.pow((y - k), 2) / Math.pow(b, 2));

        if(formula == 0) res = 1;
        else if(formula > 1) res = 2;
        else if((formula <= 1) && (formula > 0)) res = 0;

        return res;
    }
}
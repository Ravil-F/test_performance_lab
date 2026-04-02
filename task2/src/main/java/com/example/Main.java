package com.example;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String file1 = scanPaths();
        String file2 = scanPaths();

        int[] cetner = new int[2];
        int[] radius = new int[2];
        scanFile(file1, cetner, radius);

        for(int i = 0; i < 2; i++){
            System.out.println("center[" + i + "] = " + cetner[i]
                                + " radius[" + i + "] = " + radius[i]);
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
}
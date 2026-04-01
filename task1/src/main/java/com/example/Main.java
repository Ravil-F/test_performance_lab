package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n, m;
        System.out.print("Enter n: ");
        n = scanNunb();

        System.out.print("Enter m: ");
        m = scanNunb();

        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = i + 1;
        }

        String res_path = res(array, n, m);
        System.out.println(res_path);
    }

    public static int scanNunb(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static String res(int[] array, int n, int m) {
        StringBuilder path = new StringBuilder();
        int tmp = 0;
        int[] flag = new int[n];

        for (int i = 0; i < n; i++) {
            if(flag[tmp] == 1) break;

            flag[tmp] = 1;
            path.append((array[tmp]));

            tmp = (tmp + m - 1) % n;
        }

        return path.toString();
    }
}
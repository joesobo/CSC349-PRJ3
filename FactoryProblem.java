import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class FactoryProblem{
    public static void main(String args[]) throws IOException {
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader reader;
        int n;
        int e1, e2;
        int x1, x2;

        //user input
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Enter file name: ");

        //open file
        String input = inputReader.nextLine();
        File file = new File(input);

        //read file
        reader = new BufferedReader(new FileReader(file));
        String text;
        while((text = reader.readLine()) != null){
            String[] items = text.split("\\s+");
            for(int i = 0; i < items.length; i++){
                if(!items[i].equals("")){
                    list.add(Integer.parseInt(items[i]));
                }
            }
        }

        n = list.get(0);
        e1 = list.get(1);
        e2 = list.get(2);
        x1 = list.get(3);
        x2 = list.get(4);

        int[][] a = new int[2][n];
        int[][] t = new int[2][n-1];

        int count = 5;
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < n; i++){
                a[j][i] =  list.get(count);
                count++;
            }
        }

        count = 5 + 2*n;
        for(int j = 0; j < 2; j++){
            for(int i = 0; i < n-1; i++){
                t[j][i] =  list.get(count);
                count++;
            }
        }

        factory(n, e1, e2, x1, x2, a, t);
        inputReader.close();
        reader.close();
    }

    public static void factory(int n, int e1, int e2, int x1, int x2, int[][]a, int[][]t){
        int f1[] = new int [n];
        int f2[] = new int [n];
        int l[][] = new int[2][n];

        f1[0] = e1 + a[0][0];
        f1[0] = e2 + a[1][0];

        for (int j = 2; j <= n; j++) {
            if (f1[j-1] + a[0][j] <= f2[j-1] + t[1][j-1] + a[0][j]) {
                f1[j] = f1[j-1] + a[0][j];
                l[0][j] = 1;
            }
            else {
                f1[j] = f2[j-1] + t[1][j-1] + a[0][j];
                l[0][j] = 2;
            }

            if (f2[j-1] + a[1][j] <= f1[j-1] + t[0][j-1] + a[1][j]){
                f2[j] = f2[j-1] + a[1][j];
                l[1][j] = 2;
            }
            else {
                f2[j] = f1[j-1] + t[0][j-1] + a[1][j];
                l[1][j] = 1;
            }
        }
         System.out.println("Fastest time is: " + minimum((f1[n] + x1), (f2[n] + x2)));
         printLine(n,l);
    }



    private static int minimum(int a, int b) {
        if ( a < b) {
            return a;
        }
        else
            return b;
        }

    private static void printLine(int n, int[][] l) {
        int i = 1;
        System.out.println("Station "+ i + ", line " + n);
        for (int j=n; j>=2; j--){
            i = l[i][j];
            System.out.println("Station "+ i + ", line"  + (j-1));
        }

    }
    }

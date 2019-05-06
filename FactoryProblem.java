import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class FactoryProblem{
    public static void main(String args[]){
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
        try{
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
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
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

        factory(n, e1, e2, x1, x1, a, t);
        inputReader.close();
    }

    public static void factory(int n, int e1, int e2, int x1, int x2, int[][]a, int[][]t){

    }
}
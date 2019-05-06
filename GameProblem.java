import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class GameProblem{
    public static void main(String args[]){
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader reader;
        int x;
        int y;

        //get user input
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Enter file name: ");

        //open file input
        String input = inputReader.nextLine();
        File file = new File(input);

        //read file
        try{
            reader = new BufferedReader(new FileReader(file));
            String text;
            while((text = reader.readLine()) != null){
                //split line input
                String[] items = text.split("\\s+");
                //convert input to integer
                for(int i = 0; i < items.length; i++){
                    if(!items[i].equals("")){
                        list.add(Integer.parseInt(items[i]));
                    }
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        //get grid size
        x = list.get(0);
        y = list.get(1);

        //fill array with input values
        int[][] A = new int[x][y];
        int count = 2;
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                A[i][j] = list.get(count);
                count++;
            }
        }

        game(x,y,A);
        inputReader.close();
    }

    public static void game(int n, int m, int[][] A){
        int[][] S = new int[n+1][m+1];
        char[][] R = new char[n+1][m+1];
        //right padding
        for(int k = 0; k < n; k++){
            S[n][k] = 0;
        }
        //bottom padding
        for(int k = 0; k < m; k++){
            S[k][m] = 0;
        }

        for(int i = m-1; i >= 0; i--){
            for(int j = n-1; j >= 0; j--){
                if(i==n && j==m){ //corner
                    S[i][j] = A[n][m];
                    R[i][j] = 'e';//exit
                }
                else if(j==m){ // last column
                    if(S[i+1][m] > 0){
                        S[i][j] = S[i+1][m] + A[i][m];
                        R[i][j] = 'b';//bottom
                    }else{
                        S[i][j] = A[i][m];
                        R[i][j] = 'e';//exit
                    }
                }
                else if(i==n){ //last row
                    if(S[i][j+1] > 0){
                        S[i][j] = S[n][j+1] + A[n][j];
                        R[i][j] = 'r';//right
                    }else{
                        S[i][j] = A[n][j];
                        R[i][j] = 'e';//exit
                    }
                }else{ //normal square
                    if(S[i+1][j] > S[i][j+1]){
                        S[i][j] = S[i+1][j] + A[i][j];
                        R[i][j] = 'b';//bottom
                    }else{
                        S[i][j] = S[i][j+1] + A[i][j];
                        R[i][j] = 'r';//right
                    }
                }
            }
        }

        //printValues(A, S, R);
        printCorrect(S, R);
    }

    private static void printValues(int[][]A, int[][]S, char[][]R){
        System.out.println("A:");
        for(int i = 0; i < A.length; i++){
            for(int j = 0; j < A[0].length; j++){
                System.out.printf("%3d", A[i][j]);
            }
            System.out.println("");
        }

        System.out.println("S:");
        for(int i = 0; i < S.length; i++){
            for(int j = 0; j < S[0].length; j++){
                System.out.printf("%3d", S[i][j]);
            }
            System.out.println("");
        }

        System.out.println("R:");
        for(int i = 0; i < R.length; i++){
            for(int j = 0; j < R[0].length; j++){
                System.out.printf("%3c", R[i][j]);
            }
            System.out.println("");
        }
    }

    private static void printCorrect(int[][]S, char[][]R){
        int maxScore = 0;
        int maxI = 0;
        int maxJ = 0;
        for(int i = 0; i < S.length; i++){
            for(int j = 0; j < S[0].length; j++){
                if(S[i][j] > maxScore){
                    maxScore = S[i][j];
                    maxI = i;
                    maxJ = j;
                }
            }
        }
        System.out.println("\nBest score: " + maxScore);

        System.out.print("Best route: ");
        while(S[maxI][maxJ] != 0){
            System.out.printf("[%d,%d] to ", maxI+1, maxJ+1);
            if(R[maxI][maxJ] == 'r'){
                maxJ++;
            }else{
                maxI++;
            }
        }
        System.out.print("exit\n"); 
    }
}
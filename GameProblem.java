import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

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
                        list.add(Integer.parseInt((items[i])));
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
    }

    public static void game(int n, int m, int[][] A){
        
    }
}
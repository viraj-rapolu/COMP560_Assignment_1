import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Parser {
    private static ArrayList<String> colors = new ArrayList<String>();
    private static ArrayList<String> states = new ArrayList<String>();
    private static ArrayList<String> borders = new ArrayList<String>();
    public Parser(String filePath){
        int blankIndex = 0; //how many blank line dividers has the parser gone through yet - distinguish colors from states from borders
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();
            while (line != null) {
                if(line.isEmpty()){ //parser is at a blank line dividing category of information
                    blankIndex++; //increase blank index iterator
                    line = reader.readLine(); //move to next line
                }
                if(blankIndex==0){
                    colors.add(line); //before the first blank line - all colors
                }
                if(blankIndex==1){
                    states.add(line); //before second blank line - all states
                }
                if(blankIndex==2){
                    borders.add(line); //after second blank line - all borders
                }
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            fileString = Files.readString(Path.of(filePath));
//            System.out.println(fileString);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    public static ArrayList<String> getColors(){
            return colors;
        }

    public static ArrayList<String> getBorders() {
        return borders;
    }

    public static ArrayList<String> getStates() {
        return states;
    }
//    public static void main(String[] args) {
//        String filePath = "C:\\Users\\manuk\\Documents\\COMP 560\\Australia.txt.txt";
//        parseFile(filePath);
//        System.out.println(getColors());
//        System.out.println(getStates());
//        System.out.println(getBorders());
//    }
}

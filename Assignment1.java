import java.util.ArrayList;
import java.util.List;

public class Assignment1 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\manuk\\Documents\\COMP 560\\Australia.txt.txt";
        Parser fileParse = new Parser(filePath);
        ArrayList allColors = new ArrayList();
        ArrayList allStates = new ArrayList();
        ArrayList allBorders = new ArrayList();
        allColors = fileParse.getColors();
        allStates = fileParse.getStates();
        allBorders = fileParse.getBorders();
        List<State> stateList = new ArrayList<State>();
        for(int i = 0; i<allStates.size(); i++){
            ArrayList<String> borders = new ArrayList<String>();
            String stateString = (String)(allStates.get(i));
            for(int j = 0; j<allBorders.size(); j++) {
                String borderString = (String) (allBorders.get(j));
                if (borderString.contains(stateString)) {
                    borderString = borderString.replace(stateString, "");
                    borderString = borderString.replace(" ", "");
                    borders.add(borderString);
                }
            }
            stateList.add(new State(stateString, borders, allColors));
        }
//        System.out.println(fileParse.getColors());
//        System.out.println(fileParse.getStates());
//        System.out.println(fileParse.getBorders());
    }
}
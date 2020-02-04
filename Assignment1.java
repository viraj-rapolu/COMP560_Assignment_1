import java.util.ArrayList;
import java.util.List;

public class Assignment1 {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\manuk\\Documents\\COMP 560\\Australia.txt.txt";
        Parser fileParse = new Parser(filePath); //parse file

        ArrayList allColors = new ArrayList();
        ArrayList allStates = new ArrayList();
        ArrayList allBorders = new ArrayList();

        allColors = fileParse.getColors();
        allStates = fileParse.getStates();
        allBorders = fileParse.getBorders();

        ArrayList <State> stateList = new ArrayList<State>(); //list of all States

        for(int i = 0; i<allStates.size(); i++){
            //System.out.println(i);

            ArrayList<String> borders = new ArrayList<String>(); //empty border list for current state
            String stateString = (String)(allStates.get(i)); //current state string
            for(int j = 0; j<allBorders.size(); j++) {
                String borderString = (String) (allBorders.get(j)); //current border pair string
                if (borderString.contains((String)(allStates.get(i)))) { //if border pair contains current state - then the other state borders it
                    borderString = borderString.replace((String)(allStates.get(i)), ""); //remove current state from border pair
                    borderString = borderString.replace(" ", ""); //remove spaces from border pair
                    borders.add(borderString); //add bordering state to list of states bordering current state
                }
            }
            //create new state object with name of current state, list of bordering states, list of colors and add to list of states
            stateList.add(new State(stateString, borders, allColors));
        }
//        System.out.println(fileParse.getColors());
//        System.out.println(fileParse.getStates());
//        System.out.println(fileParse.getBorders());
        //LocalSearch search = new LocalSearch(stateList, allColors);
        //search.randStart();
        for(int u = 0; u<stateList.size(); u++){
            State state = stateList.get(u);
            System.out.println(state.getName());
            System.out.println(state.getBorderStates());
        }
    }
}

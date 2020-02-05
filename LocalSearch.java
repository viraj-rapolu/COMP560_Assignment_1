import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class LocalSearch {
    private static ArrayList<State> stateList = new ArrayList<State>();
    private static ArrayList<String> colors = new ArrayList<String>();
    private static int totalViolations = 0;
    public LocalSearch(ArrayList<State> StateList, ArrayList<String> colorList){
        stateList = StateList;
        colors = colorList;
    }
    //random assignment of values to all variables
    public void randStart(){
        Random randColorGenerator = new Random();
        for(int i = 0; i<stateList.size(); i++){
            int randColorIndex = randColorGenerator.nextInt(colors.size());
            String randColor = colors.get(randColorIndex);
            stateList.get(i).assignedColor =randColor;
        }
    }
    //return state when given a string and list of states
    public State findStateByName(String name, ArrayList<State> states){
        for(State state : states){
            if(state.getName().equals(name)){
                return state;
            }
        }
        return null;
    }
    //finds number of cases where neighboring states have the same color, updates that property for each state and
    //calculates total number of violations
    public void findNumViolations(){
        totalViolations = 0;
        for(int j = 0; j<stateList.size(); j++) {
            int violations = 0;
            ArrayList<String> borderList = stateList.get(j).getBorderStates();
            for (int h = 0; h < borderList.size(); h++) {
                State stateToCompare = findStateByName(borderList.get(h), stateList);
                if (stateList.get(j).assignedColor == stateToCompare.assignedColor) {
                    violations++;
                    totalViolations = totalViolations + violations;
                }
            }
            stateList.get(j).setNumViolations(violations);
        }
    }
    //finds the state(s) with the most violations - of the states with most violations chooses randomly which to change
    public State findStateMostViol(){
        //find state with most violations, if tie then choose randomly
        ArrayList<State> mostViolStates = new ArrayList<State>();
        int max = 0;
        for(State state : stateList){
            if(state.getNumViolations()>max){
                max = state.getNumViolations();
            }
        }
        for(State state : stateList){
            if(state.getNumViolations()==max){
                mostViolStates.add(state);
            }
        }
        Random statePicker = new Random();
        int randStateIndex = statePicker.nextInt(mostViolStates.size());
        return mostViolStates.get(randStateIndex);
    }
    //change value of state chosen
    public ArrayList<State> fixViolation(){
        if(totalViolations ==0){
            return stateList;
        }
        else{
            State fixState = findStateMostViol();
            System.out.println("Changing following state: "+fixState.getName());
//            ArrayList<String> newColorList = colors;
//            System.out.println(newColorList);
//            newColorList.remove(fixState.assignedColor);
//            System.out.println("Possible colors: "+ newColorList);
            Random randColorGenerator = new Random();
            String randcolor = fixState.assignedColor;
            while(randcolor==fixState.assignedColor) {
                int randColorIndex = randColorGenerator.nextInt(colors.size());
                randcolor = (colors.get(randColorIndex));
            }
            fixState.setAssignedColor(randcolor);
            return stateList;
        }
    }
    //loops through checking violations and changing a state until there are no violations
    public void runSearch(){
        long start = System.currentTimeMillis();
        long end = start + (60*1000);
//        System.out.println(start);
//        System.out.println(end);
        this.randStart();
        this.findNumViolations();
        while((totalViolations>0)&&(System.currentTimeMillis() < end)){
           System.out.println("New iteration");
            for(State state : stateList){
                System.out.println(state.toString()+" "+state.getNumViolations());
            }
            stateList = fixViolation();
            this.findNumViolations();
            for(State state : stateList){
                System.out.println(state.toString()+" "+state.getNumViolations());
            }
        }
    }
}

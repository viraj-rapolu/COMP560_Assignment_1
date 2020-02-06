import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class LocalSearch {
    private static ArrayList<State> stateList = new ArrayList<State>();
    private static ArrayList<String> colors = new ArrayList<String>();
    private static int totalViolations = 0; //number of violations in whole map
    public LocalSearch(ArrayList<State> StateList, ArrayList<String> colorList){
        stateList = StateList;
        colors = colorList;
    }
    //random assignment of values to all variables
    public void randStart(){
        Random randColorGenerator = new Random();
        for(int i = 0; i<stateList.size(); i++){
            int randColorIndex = randColorGenerator.nextInt(colors.size()); //randomly generate index of color list
            String randColor = colors.get(randColorIndex); //get color
            stateList.get(i).assignedColor =randColor; //assign color to state
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
    //finds number of violations - where neighboring states have the same color - and updates that property for each
    //state and calculates total number of violations
    public void findNumViolations(){
        totalViolations = 0; //to start with, there are no violations on the map
        for(int j = 0; j<stateList.size(); j++) {
            int violations = 0; //state initially has no violations
            ArrayList<String> borderList = stateList.get(j).getBorderStates();
            for (int h = 0; h < borderList.size(); h++) {
                State stateToCompare = findStateByName(borderList.get(h), stateList);
                if (stateList.get(j).assignedColor == stateToCompare.assignedColor) { //for each bordering state, check if it has the same color
                    violations++;   //if bordering state has the same color as current state, increment number of violations for that state
                    totalViolations = totalViolations + violations; //add state's violations to the total violations on the map
                }
            }
            stateList.get(j).setNumViolations(violations); //set the number fo violations for that state
        }
    }
    //finds the state(s) with the most violations - of the states with most violations chooses randomly which to change
    public State findStateMostViol(){
        ArrayList<State> mostViolStates = new ArrayList<State>();
        int max = 0;
        for(State state : stateList){
            if(state.getNumViolations()>max){
                max = state.getNumViolations(); //find max value of violations
            }
        }
        for(State state : stateList){
            if(state.getNumViolations()==max){
                mostViolStates.add(state); //find states that have that max value of violations
            }
        }
        Random statePicker = new Random();
        int randStateIndex = statePicker.nextInt(mostViolStates.size());
        return mostViolStates.get(randStateIndex); //randomly choose state from collection of states that all have the highest number of violations
    }
    //change value of state chosen
    public ArrayList<State> fixViolation(){
        if(totalViolations ==0){ //if the map is correct already
            return stateList;
        }
        else{
            State fixState = findStateMostViol(); //finds state to fix
            //System.out.println("Changing following state: "+fixState.getName());
            Random randColorGenerator = new Random();
            String randcolor = fixState.assignedColor;
            while(randcolor==fixState.assignedColor) {
                //randomly picking color to change state to, and ensuring that the new color is not the same as what the state just was
                int randColorIndex = randColorGenerator.nextInt(colors.size());
                randcolor = (colors.get(randColorIndex));
            }
            fixState.setAssignedColor(randcolor); //assign the new color to the state
            return stateList;
        }
    }
    //loops through checking violations and changing a state until there are no violations
    public void runSearch(){
        System.out.println("Local Search");
        long start = System.currentTimeMillis();
        long end = start + (60*1000); //60 seconds after start
        this.randStart(); //randomly assign map
        this.findNumViolations(); //finds the number of violations in the map to start with and stores in totalViolations
        while((totalViolations>0)&&(System.currentTimeMillis() < end)){ //loops until the map is correct or 60 seconds have passed
//           System.out.println("New iteration");
//            for(State state : stateList){
//                System.out.println(state.toString()+" "+state.getNumViolations());
//            }
            stateList = fixViolation(); //change state with most violations to a new color
            this.findNumViolations(); //check violations
//            for(State state : stateList){
//                System.out.println(state.toString()+" "+state.getNumViolations());
//            }
        }
        for(State state : stateList){
            System.out.println(state.toString());
        }
    }
}

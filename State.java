import java.util.ArrayList;

public class State {
    public static String stateName; //name of state
    public static ArrayList borderStates = new ArrayList(); //list of bordering states
    public static ArrayList colorList = new ArrayList(); //list of all colors
    private static int numBorder; //number of bordering states
    public static ArrayList<String> brokenColors = new ArrayList(); //list of colors state cannot be
    public static int numBrokeColors = 0; //number of colors state cannot be
    public static String assignedColor = "blank"; //assigned color of state
    public State(String name, ArrayList<String> borderArray, ArrayList<String> colors){
        stateName = name;
        borderStates = borderArray;
        colorList = colors;
        numBorder = borderArray.size();
    }
    public static String getName(){
        return stateName;
    }
    public static ArrayList getBorderStates(){
        return borderStates;
    }
    public static ArrayList getColorList(){
        return colorList;
    }
    public static int getNumborder(){
        return numBorder;
    }
    public static void addBrokenColors(String color){
        //add color to list of not possible colors
        brokenColors.add(color);
        numBrokeColors = brokenColors.size();
    }
    public static ArrayList<String> getBrokenColors(){
        return brokenColors;
    }
    public static int getNumBrokeColors() {
        return numBrokeColors;
    }
    public static void setAssignedColor(String color){
        assignedColor = color;
    }
}


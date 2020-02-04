import java.util.ArrayList;

public class State {
    private String stateName; //name of state
    private ArrayList borderStates = new ArrayList(); //list of bordering states
    public ArrayList colorList = new ArrayList(); //list of all colors
    private int numBorder; //number of bordering states
    public ArrayList<String> brokenColors = new ArrayList(); //list of colors state cannot be
    public int numBrokeColors = 0; //number of colors state cannot be
    public String assignedColor = "blank"; //assigned color of state
    public State(String name, ArrayList<String> borderArray, ArrayList<String> colors){
        stateName = name;
        borderStates = borderArray;
        colorList = colors;
        numBorder = borderArray.size();
    }
    public String getName(){
        return stateName;
    }
    public ArrayList getBorderStates(){
        return borderStates;
    }
    public ArrayList getColorList(){
        return colorList;
    }
    public int getNumborder(){
        return numBorder;
    }
    public void addBrokenColors(String color){
        //add color to list of not possible colors
        brokenColors.add(color);
        numBrokeColors = brokenColors.size();
    }
    public ArrayList<String> getBrokenColors(){
        return brokenColors;
    }
    public int getNumBrokeColors() {
        return numBrokeColors;
    }
    public void setAssignedColor(String color){
        assignedColor = color;
    }
    public String toString(){
        return stateName+": "+assignedColor;
    }
}


import java.util.ArrayList;

public class State {
    private static String stateName;
    private static ArrayList borderStates = new ArrayList();
    private static ArrayList colorList = new ArrayList();
    public State(String name, ArrayList<String> borderArray, ArrayList<String> colors){
        stateName = name;
        borderStates = borderArray;
        colorList = colors;
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
}

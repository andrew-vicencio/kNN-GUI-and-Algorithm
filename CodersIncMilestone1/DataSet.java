import java.util.*;


public class DataSet {
    private ArrayList<Point> points;
    private HashMap<String, String> feilds;
    private DimensionalSpace grid;



    public DataSet(){
        feilds = new HashMap<String, String>();
        points = new ArrayList<Point>();
        grid = new DimensionalSpace();
    }
    //TODO: BB create interperter for atributes


    public void addPoint(String feild){
    //After seting all points set points in dimensional space
    }

    private void setPointsInGrid(){

    }

    //needs to be implemented
    public int knnValue(){
      return 0;
    }

    public void addField(String name, String type){
        feilds.put(name,type);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public boolean isFeildsLessThan2 (){
        return feilds.size() < 2;
    }

    public HashMap<String, String> getFeilds() {
        return feilds;
    }

    //TODO: BB create add point method
    //TODO: BB




}

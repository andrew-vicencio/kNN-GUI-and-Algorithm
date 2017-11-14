

import java.util.ArrayList;

public class CompositeCell extends Cell {

	private ArrayList<Cell> features;
	
	public CompositeCell(String key) {
		super(key);
		features = new ArrayList<Cell>();
	}
	
	public ArrayList<Cell> getSubCells() {
		return features;
	}
	
	public void setSubCells(ArrayList<Cell> features) {
		this.features = features;
	}
	
	public void addCell(Cell f) {
		features.add(f);
	}


	public String toString(){
        String finalString = "";
        System.out.println(features.size());
        for (Cell x: features) {
            System.out.println(finalString);
            SimpleCell i = (SimpleCell)x;
            if(finalString == ""){
                finalString = i.toString();
            }else{
                finalString = finalString + ", " + i.toString();
            }


        }

        return finalString;
    }


}

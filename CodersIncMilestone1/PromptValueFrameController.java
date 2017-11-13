import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JTextField;
public class PromptValueFrameController extends MainController {

	private PromptValueFrame pvf;
	private View view;

	/**
	 * Constructor for PromptValueController. Passes along the PromptValueFrame and View associated with it
	 * @param view: View
	 * @param pvf: PromptValueFrame
	 */
	public PromptValueFrameController(View view, PromptValueFrame pvf)
	{
		this.pvf = pvf;
		this.view = view;
	}
	
	/**
	 * Adds the data that was inputed in the PromptValueFrame to a Point, which is added to Dimensional Frame
	 */
	public void actionPerformed(ActionEvent e) {
		
            Point newPoint = new Point();
            HashMap<String, Cell> newConfiguredData = new HashMap<String, Cell>();
			//TODO BB : pvf.getFieldMap() is a Hash Map of the feature name and their corresponding JTextField.
			// You need iterate through it, get the text from the JTextField, and add to a point 
			// in Dimensional space
			pvf.dispose();

            for(String key : dataModel.getCellTypes().keySet()){
                if(dataModel.getCellTypes().get(key).equals("comp")){
                    createCompFeature(key, newConfiguredData);
                }else{
                    if(key.contains(".")){
                        String keyBrakDown = key.split("\\.")[0];
                        createCompFeature(keyBrakDown, newConfiguredData);
                        createSubFeature(key, pvf.getFieldMap().get(key).getText(), (CompositeCell) newConfiguredData.get(keyBrakDown));
                    }else{
                       newConfiguredData.put(key, createStanderedFeature(key, pvf.getFieldMap().get(key).getText()));
                    }
                }
            }
            newPoint.setHashMaprawValues(newConfiguredData);
            dataModel.addPoint(newPoint);


			if(dataModel.getNumberOfPoints() > 0)
			{
				view.enableTesting(true);
			}

	}

	private Cell createStanderedFeature(String key, String value){
        String type = dataModel.getCellTypes().get(key);
        System.out.println(key + "," + value);
        if(type.equals("int")){
            try{
                int valueInt = Integer.parseInt(value.replaceAll(" ", ""));
                SimpleCell<Integer> newCell = new SimpleCell<Integer>(key, valueInt);
                return newCell;
            }catch (NumberFormatException ex){
                //TODO: BB
                //TODO: GH Create error back out
            }

        }else if(type.equals("float")){
           try{
               float valueInt = Float.parseFloat(value);
               SimpleCell<Float> newCell = new SimpleCell<Float>(key, valueInt);
               return newCell;
           }catch (NumberFormatException ex){
               //TODO: BB
               //TODO: GH Create error back out
           }
        }else{
            SimpleCell<String> newCell = new SimpleCell<String>(key, value);
            return newCell;
        }

        return null;
    }

    private void createSubFeature(String key, String value, CompositeCell complexCell){
        Cell newSimpleCell = createStanderedFeature(key, value);
        complexCell.addCell(newSimpleCell);

    }

    private void createCompFeature(String key,  HashMap<String, Cell> newConfiguredData ) {
        if(!newConfiguredData.keySet().contains(key)){
            CompositeCell newComplexCell = new CompositeCell(key);
            newConfiguredData.put(key, newComplexCell);
        }
    }

    //this gets called if problem with casting needs to stop the action one aka exit that area
    private void errorCasting(){

    }

}

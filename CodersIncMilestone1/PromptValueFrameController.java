import java.awt.event.ActionEvent;
import java.util.HashMap;
/**
 * A controller class for a JFrame which prompts the user for a set of values corresponding to
 * predetermined features. This class is responsible for checking the user input to see if it 
 * is valid, and creating a Cell object for each. Those Cell objects make up a Point, which
 * will be add to DimensionalSpace
 * 
 * @author Gabrielle and Ben
 *
 */
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
	/**
	 * TODO: BB - documentation
	 * @param key
	 * @param value
	 * @return
	 */
	private Cell createStanderedFeature(String key, String value){
        String type = dataModel.getCellTypes().get(key);
        System.out.println(key + "," + value);
        if(type.equals("int")){
            try{
                int valueInt = Integer.parseInt(value.replaceAll(" ", ""));
                SimpleCell<Integer> newCell = new SimpleCell<Integer>(key, valueInt);
                pvf.dispose();
                return newCell;
            }catch (NumberFormatException ex){
                view.sendErrorFrame("One or more integers have incorrect formatting.");
            }

        }else if(type.equals("float")){
           try{
               float valueInt = Float.parseFloat(value);
               SimpleCell<Float> newCell = new SimpleCell<Float>(key, valueInt);
               pvf.dispose();
               return newCell;
           }catch (NumberFormatException ex){
               view.sendErrorFrame("One or more floats have incorrect formatting");
           }
        }else{
            SimpleCell<String> newCell = new SimpleCell<String>(key, value);
            pvf.dispose();
            return newCell;
        }

        return null;
    }
	/**
	 * TODO: BB - Documentation
	 * @param key
	 * @param value
	 * @param complexCell
	 */
    private void createSubFeature(String key, String value, CompositeCell complexCell){
        Cell newSimpleCell = createStanderedFeature(key, value);
        complexCell.addCell(newSimpleCell);

    }
    
    /**
     * TODO: BB - Documentation
     * @param key
     * @param newConfiguredData
     */
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

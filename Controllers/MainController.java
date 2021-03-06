package Controllers;

import DataModel.DimensionalSpace;

import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Controlers.MainController abstract class to provide static shared information of the model
 * to all controllers
 * @author Ben
 * @version Milestone 4
 *
 */
public abstract class MainController implements ActionListener, Serializable{


    protected static DimensionalSpace dataModel = new DimensionalSpace();

    /**
     * @return dataModel: Current DimensionalSpace being used
     */
    public DimensionalSpace getDataModel() {
        return dataModel;
    }

    //Should only ever be used for importing from a file
    /**
     * @param x: new DimensionalSpace to be used
     */
    public void setDataModel(DimensionalSpace x){
        dataModel = x;
    }

}

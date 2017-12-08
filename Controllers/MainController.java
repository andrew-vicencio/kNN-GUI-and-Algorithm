package Controllers;

import DataModel.DimensionalSpace;

import java.awt.event.ActionListener;
import java.io.Serializable;

/**
 * Controlers.MainController abstract class to provide static shared information of the model
 * to all controllers
 * @author Ben
 *
 */
public abstract class MainController implements ActionListener, Serializable{


    protected static DimensionalSpace dataModel = new DimensionalSpace();

    public DimensionalSpace getDataModel() {
        return dataModel;
    }

    //Should only ever be used for importing from a file
    public void setDataModel(DimensionalSpace x){
        dataModel = x;
    }

}

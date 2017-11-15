
import java.awt.event.ActionListener;
/**
 * MainController abstract class to provide static shared information of the model
 * to all controllers
 * @author Ben
 *
 */
public abstract class MainController implements ActionListener{


    protected static DimensionalSpace dataModel = new DimensionalSpace();

    public DimensionalSpace getDataModel() {
        return dataModel;
    }

}

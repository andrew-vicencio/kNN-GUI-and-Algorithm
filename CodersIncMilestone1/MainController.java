
import java.awt.event.ActionListener;

public abstract class MainController implements ActionListener{

    //Main controller used to make sure that all other controllers can access the data set

    protected static DimensionalSpace dataModel = new DimensionalSpace();

    public DimensionalSpace getDataModel() {
        return dataModel;
    }

}

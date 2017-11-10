
import java.awt.event.ActionListener;

public abstract class MainController implements ActionListener{

    //Main controller used to make sure that all other controllers can access the data set

    private static DataSet dataModel = new DataSet();

    public DataSet getDataModel() {
        return dataModel;
    }
}

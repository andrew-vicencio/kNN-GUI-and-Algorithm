package Controllers;

import DataModel.DimensionalSpace;
import ImportExport.SerialExport;
import ImportExport.SerialImport;
import View.*;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SaveControler extends MainController {
    private View view;
    private PromptSaveFrame frame;


    public SaveControler(View view, PromptSaveFrame frame){
        this.view = view;
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getActionCommand().equals("Done")){
                if(frame.isSave()){
                    SerialExport exportFile = new SerialExport();
                    try {
                        exportFile.exportDimensionalSpace(frame.getFileName(), this.getDataModel());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    SerialImport importFile = new SerialImport();
                    DimensionalSpace tempHold;
                    try {
                        tempHold = importFile.importDimensionalSpace(frame.getFileName());
                        tempHold.setView(view);
                        this.setDataModel(tempHold);
                        view.setDataModel();
                        view.clearContentPanel();
                        this.getDataModel().updateView();

                        view.enableTesting(true);
                        view.enableNewDataSet(false);
                        view.enableFeatureCreation(false);
                        view.enableDataInput(true);
                        view.enableTesting(true);
                        view.enableNewDataSet(false);
                        view.enableTestData(false);
                        view.getDoneButton().setVisible(false);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
        }
        frame.dispose();
    }
}

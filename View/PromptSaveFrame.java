package View;

import Controllers.SaveController;

import javax.swing.*;
import java.awt.*;

public class PromptSaveFrame  extends JFrame {

    private View view;
    private JPanel mainPanel, footerPanel, promptPanel;
    private JLabel name;
    private JTextField fileName;
    private JButton done;
    private boolean save;

    /**
     * Prompt user for the string input for the file location
     * @param view
     * @param save
     */
    public PromptSaveFrame(View view, boolean save){
        this.save = save;
        this.view = view;
        mainPanel = new JPanel();
        fileName = new JTextField(15);
        promptPanel = new JPanel();
        footerPanel = new JPanel();
        name = new JLabel("Enter File Location: ");
        promptPanel.add(name);
        promptPanel.add(fileName);

        done  = new JButton("Done");
        done.addActionListener(new SaveController(view, this));
        mainPanel.setLayout(new GridLayout(0,1));
        setSize(350,125);
        footerPanel.add(done);
        mainPanel.add(promptPanel);
        setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * get the file name/location
     * @return
     */
    public String getFileName(){
        return fileName.getText();
    }

    /**
     * Checking if the user chose import or export
     * @return
     */
    public boolean isSave() {
        return save;
    }
}

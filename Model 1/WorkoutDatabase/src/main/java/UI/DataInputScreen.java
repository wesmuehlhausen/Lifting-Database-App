package UI;

import Database.Database;
import Helper.DataCleaner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class DataInputScreen {

    public final int FRAME_WIDTH = 1000;
    public final int NORMAL_WIDTH = 100;
    public final int NORMAL_HEIGHT = 100;
    public final int SMALL_WIDTH = 50;
    public final int LARGE_WIDTH = 200;
    public final int FRAME_HEIGHT = 200;
    public final int COLUMNS_X = 10;
    public final int ROWS_Y = 4;
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JButton footerButton;
    private JPanel gridLabels;
    private JPanel gridEntries;
    private Database data;

    public DataInputScreen(Database data){
        prepareGUI();
        populateScreen();
        this.data = data;
    }

    private void prepareGUI(){

        //Create Frame that everything will be in
        mainFrame = new JFrame("Lifting Database.Database");
        mainFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setLayout(new GridLayout(ROWS_Y, 0));//Grid Layout!! as many columns as necessary (0)

        //Row 1: Header Label
        headerLabel = new JLabel("",JLabel.CENTER );

        //Row 2: Labels for the entries
        gridLabels = new JPanel();
        gridLabels.setLayout(new GridLayout(0,COLUMNS_X));
        gridLabels.setBackground(Color.MAGENTA);

        //Row 3: Entries for the table
        gridEntries = new JPanel();
        gridEntries.setLayout(new GridLayout(0,COLUMNS_X));

        //Row 4: Footer Button
        footerButton = new JButton("Enter");

        //Add elements to frame
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        mainFrame.add(headerLabel);
        mainFrame.add(gridLabels);
        mainFrame.add(gridEntries);
        mainFrame.add(footerButton);
        mainFrame.setVisible(true);
    }

    public void populateScreen(){
        //Row 1: Header
        headerLabel.setText("Lifting Database.Database: Enter a workout");

        //Row 2: Labels
        ArrayList<JLabel> labels = new ArrayList<>();
        labels.add(new JLabel("Workout #", JLabel.CENTER));
        labels.add(new JLabel("Date", JLabel.CENTER));
        labels.add(new JLabel("Type", JLabel.CENTER));
        labels.add(new JLabel("Target", JLabel.CENTER));
        labels.add(new JLabel("Name", JLabel.CENTER));
        labels.add(new JLabel("Sets", JLabel.CENTER));
        labels.add(new JLabel("Reps", JLabel.CENTER));
        labels.add(new JLabel("Weight LBS", JLabel.CENTER));
        labels.add(new JLabel("Single Arm", JLabel.CENTER));
        labels.add(new JLabel("Notes", JLabel.CENTER));
        //Add to JPanel
        for(JLabel l : labels)
            gridLabels.add(l);

        //Row 3: Entries
        ArrayList<JComponent> gridEntry = new ArrayList<>();
        gridEntry.add(new JTextField("0"));
        gridEntry.add(new JTextField("MM/DD/YYYY"));
        gridEntry.add(new JComboBox<>(new String[]{"Push", "Pull", "Legs", "Other"}));
        gridEntry.add(new JComboBox<>(new String[]{"Strength", "Mass", "Other"}));
        gridEntry.add(new JTextField());
        gridEntry.add(new JTextField());
        gridEntry.add(new JTextField());
        gridEntry.add(new JTextField());
        gridEntry.add(new JCheckBox());
        gridEntry.add(new JTextField());
        //Add elements
        for(JComponent l : gridEntry)
            gridEntries.add(l);


        //WHEN THE ENTER BUTTON IS CLICKED
        footerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addLiftData(collectData(gridEntry));//Adds value to database
            }
        });
        mainFrame.setVisible(true);
    }

    private String[] collectData(ArrayList<JComponent> entry){
        //New empty entry list
        String[] entryValues = new String[10];

        //Iterate through every entry box and fill in array of values
        for(int i = 0; i < entry.size(); ++i){
            //Default string is @ for an error flag
            String input = "@";
            //If we are in position 1 and 3, its a ComboBox
            if(i == 2 || i == 3){//Combo Box
                JComboBox comboBox = (JComboBox) entry.get(i);
                input = (String) comboBox.getSelectedItem();
            }
            //Position 8 is a Checkbox
            else if(i == 8){//Checkbox
                JCheckBox checkBox = (JCheckBox) entry.get(i);
                if(checkBox.isSelected())
                    input = ("true");
                else
                    input = ("false");
            }
            //Everything else is a text field
            else{//Text field
                JTextField tf = (JTextField) entry.get(i);
                input = tf.getText();
            }

            entryValues[i] = DataCleaner.cleanData(input, i);//Add cleaned value to database
        }

        return entryValues;
    }

}

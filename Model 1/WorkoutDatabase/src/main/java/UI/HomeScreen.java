package UI;

import Database.PostgreSQL;
import ProjectObjects.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HomeScreen extends JFrame{


    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel mainContentPanel;
    private JButton homeButton; //TODO set back to private
    private JButton liftingButton;
    private JButton dietButton;
    private JButton sleepDiet;
    private JButton settingsButton;
    private JPanel leftHomePanel;
    private JPanel leftCenterPanel;
    private JPanel leftBottomPanel;
    private JPanel mainContentPanelHeader;
    private JPanel mainContentPanelBody;
    private JPanel mainContentPanelHeaderTitle;
    private JPanel mainContentPanelHeaderSubTitle;

    private ArrayList<JButton> buttons;

    public final Color MAIN_COLOR = new Color(34, 40, 44);
    public final Color TAB_COLOR = new Color(21, 25, 28);
    public final Color MOUSE_HOVER = new Color(37, 94, 128);
    public final Color TITLE_COLOR = new Color(155,155,155);
    public final Color SUB_TITLE_COLOR = new Color(215,215,215);
    public final Color NORMAL_TEXT = Color.WHITE;
    public final Color MOUSE_PRESS = Color.BLUE;

    public final Font titleFont = new Font("Roboto Th", Font.PLAIN, 24);
    public final Font subTitleFont = new Font("Roboto Th", Font.PLAIN, 12);

    public final int SCREEN_X = 1400;
    public final int SCREEN_Y = 800;
    public final int PANEL_X = 120;
    public final int PANEL_Y = 1400;
    public final int MAIN_X = 1280;
    public final int MAIN_Y = 800;
    public final int MAIN_HEADER_X = 1280;
    public final int MAIN_HEADER_Y = 120;
    public final int MAIN_HEADER_TITLE_Y = 80;
    public final int MAIN_HEADER_SUB_TITLE_Y = 40;
    public final int MAIN_CONTENT_X = 1280;
    public final int MAIN_CONTENT_Y = 680;
    public final int NORMAL = 120;
    public final int SMALL = 60;

    private HomeScreenLogic homeScreenLogic;

    public HomeScreen(String username, PostgreSQL psqlDatabase){

        homeScreenLogic = new HomeScreenLogic(username, psqlDatabase);
        initializeCanvas();
        initializeButtons();
        setColors();
        setFonts();
        setupHomeScreen();
    }


    private void initializeButtons()  {
        buttons = new ArrayList<>();
        buttons.add(homeButton);
        buttons.add(liftingButton);
        buttons.add(dietButton);
        buttons.add(sleepDiet);
        buttons.add(settingsButton);
    }

    private void setFonts(){

    }

    private void setColors(){
        mainPanel.setBackground(Color.RED);//TODO set to black after testing
        mainContentPanel.setBackground(MAIN_COLOR);
        mainContentPanelBody.setBackground(MAIN_COLOR);
        mainContentPanelHeader.setBackground(MAIN_COLOR);
        leftPanel.setBackground(TAB_COLOR);
        leftCenterPanel.setBackground(TAB_COLOR);
        leftBottomPanel.setBackground(TAB_COLOR);
        leftHomePanel.setBackground(TAB_COLOR);
        mainContentPanelHeaderTitle.setBackground(MAIN_COLOR);
        mainContentPanelHeaderSubTitle.setBackground(Color.RED);//TODO set to black after testing

        for(JButton b : buttons){
            b.setOpaque(true);
            b.setBackground(MAIN_COLOR);
            b.setForeground(Color.white);
            b.setBorderPainted(false);
            b.setBorder(null);
            b.setFocusPainted(false);

            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    b.setBackground(MOUSE_PRESS);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    b.setBackground(MOUSE_HOVER);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    b.setBackground(MOUSE_HOVER);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    b.setBackground(MAIN_COLOR);
                }
            });
            b.addMouseListener(new MouseAdapter() {
            });
        }

    }

    private void initializeCanvas(){
        setContentPane(mainPanel);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1400,800);
    }

    private void setupHomeScreen(){
        //Create panels
        JLabel heading = new JLabel("Welcome,");
        JLabel subheading = new JLabel(homeScreenLogic.getName());
        //Set font
        heading.setFont(titleFont);
        subheading.setFont(subTitleFont);
        //Set color
        heading.setForeground(TITLE_COLOR);
        subheading.setForeground(SUB_TITLE_COLOR);
//        heading.setOpaque(true);//TODO
//        heading.setBackground(Color.GREEN);//TODO


        //Add HEADER
//        JPanel p1 = createCustomPanel(SMALL, SMALL, MAIN_COLOR);
//        mainContentPanelHeaderTitle.add(p1);
        mainContentPanelHeaderTitle.add(heading);
        mainContentPanelHeaderSubTitle.add(subheading);
        //Set visible true
        mainContentPanelHeaderTitle.setVisible(true);
        mainContentPanelHeaderSubTitle.setVisible(true);
        //Allign
    }

    private JPanel createCustomPanel(int x, int y, Color color){
        JPanel jPanel = new JPanel();
        Dimension dim = new Dimension(x, y);
        jPanel.setPreferredSize(dim);
        jPanel.setBackground(color);
        return jPanel;
    }

}

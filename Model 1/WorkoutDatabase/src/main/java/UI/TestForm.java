package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class TestForm extends JFrame{
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

    private ArrayList<JButton> buttons;

    public final Color MAIN_COLOR = new Color(34, 40, 44);
    public final Color TAB_COLOR = new Color(21, 25, 28);
    public final Color MOUSE_HOVER = new Color(37, 94, 128);
    public final Color MOUSE_PRESS = Color.BLUE;



    public TestForm(){
        initializeCanvas();
        initializeButtons();
        setColors();


    }

    private void initializeButtons()  {
        buttons = new ArrayList<>();
        buttons.add(homeButton);
        buttons.add(liftingButton);
        buttons.add(dietButton);
        buttons.add(sleepDiet);
        buttons.add(settingsButton);
    }

    private void setColors(){
        mainContentPanel.setBackground(MAIN_COLOR);
        leftPanel.setBackground(TAB_COLOR);
        leftCenterPanel.setBackground(TAB_COLOR);
        leftBottomPanel.setBackground(TAB_COLOR);
        leftHomePanel.setBackground(TAB_COLOR);

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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1400,800);
    }

}

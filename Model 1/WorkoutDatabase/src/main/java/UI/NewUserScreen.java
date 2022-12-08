package UI;
import Database.PostgreSQL;
import ProjectObjects.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class NewUserScreen extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel usernameLabel = new JLabel("USERNAME");
    JLabel userLabel = new JLabel("name");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JLabel passwordLabelDuplicate = new JLabel("RE-ENTER");
    JTextField usernameTextField = new JTextField();
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JPasswordField passwordFieldDuplicate = new JPasswordField();
    JButton createButton = new JButton("SUBMIT");
    JButton resetButton = new JButton("CANCEL");
    JCheckBox showPassword = new JCheckBox("Show Passwords");
    PostgreSQL psqlDatabase;
    LoginScreen previousScreen;


    public NewUserScreen(LoginScreen previousScreen) {


        psqlDatabase = new PostgreSQL();
        this.previousScreen = previousScreen;
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        runLoginScreen();//Runs screen


    }

    public void runLoginScreen(){
        setTitle("Lifting Database - Create Account");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {

        int yOffset = 70;
        usernameLabel.setBounds(50, 80, 100, 30);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        passwordLabelDuplicate.setBounds(50, 220+yOffset, 100, 30);

        usernameTextField.setBounds(150, 80, 150, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        passwordFieldDuplicate.setBounds(150, 220+yOffset, 150, 30);

        showPassword.setBounds(150, 250+yOffset, 150, 30);
        createButton.setBounds(50, 300+yOffset, 100, 30);
        resetButton.setBounds(200, 300+yOffset, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(createButton);
        container.add(resetButton);
        container.add(passwordLabelDuplicate);
        container.add(passwordFieldDuplicate);
        container.add(usernameLabel);
        container.add(usernameTextField);
    }

    public void addActionEvent() {
        createButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {

            //Check if passwords match
            if(passwordFieldDuplicate.getText().equals(passwordField.getText())){
                //Check if passwords are over 8
                if(passwordField.getText().length() >= 8 && usernameTextField.getText().length() >= 8){
                    if(psqlDatabase.userExists(usernameTextField.getText()) == false){
                        User user = new User(usernameTextField.getText(), userTextField.getText());
                        psqlDatabase.createUser(user, passwordField.getText());
                        previousScreen.setFields(user.getUsername_id(), passwordField.getText());
                        setVisible(false);
                        previousScreen.setVisible(true);
                    }
                    else
                        JOptionPane.showMessageDialog(this, "Username is already registered");
                }
                else
                    JOptionPane.showMessageDialog(this, "Username and Password need to be at least 3 characters");
            }
            else
                JOptionPane.showMessageDialog(this, "Passwords need to match");
            //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }


        if (e.getSource() == resetButton) {
            setVisible(false);
            previousScreen.setVisible(true);
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
                passwordFieldDuplicate.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
                passwordFieldDuplicate.setEchoChar('*');
            }
        }
    }

}

package UI;

import Database.PostgreSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton signUp = new JButton("SIGN UP");
    JCheckBox showPassword = new JCheckBox("Show Password");
    PostgreSQL psqlDatabase;


    public LoginScreen() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        runLoginScreen();//Runs screen
        psqlDatabase = new PostgreSQL();
    }

    public void runLoginScreen(){
        setTitle("Lifting Database - Login");
        setVisible(true);
        setBounds(10, 10, 370, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        signUp.setBounds(200, 300, 100, 30);
    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(signUp);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        signUp.addActionListener(this);
        showPassword.addActionListener(this);
    }

    public void setFields(String username, String password){
        userTextField.setText(username);
        passwordField.setText(password);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginButton) {
            if(psqlDatabase.validateUserLogin(userTextField.getText(), passwordField.getText())){//If connected to database correctly, return true
                String username = userTextField.getText();
                setVisible(false);
                HomeScreen homeScreen = new HomeScreen(username, psqlDatabase);
            }
            else
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }

        if (e.getSource() == signUp) {
            setVisible(false);
            NewUserScreen newUserScreen = new NewUserScreen(this);
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}


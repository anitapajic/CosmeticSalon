package gui;

import net.miginfocom.swing.MigLayout;
import repository.MainRepository;
import service.MainService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    JButton btnLogin = new JButton("Login");
    JButton btnExit = new JButton("Exit");
    JButton btnRegister = new JButton("Register");

    JLabel lblUsername = new JLabel("USERNAME: ");
    JLabel lblPassword = new JLabel("PASSWORD:");

    JTextField txtUsername = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);

    MainRepository mainRepository;
    MainService mainService;

    public LoginWindow(MainRepository mainRepository, MainService mainService) {
        this.mainRepository = mainRepository;
        this.mainService = mainService;
        setTitle("Login");
        setLayout(new MigLayout("wrap 2", "[] 30 []", "[] 30 [] [] 30 []"));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        getContentPane().setBackground(new Color(214, 179, 171));
        GUI();
        pack();
        setLocationRelativeTo(null);
        actions();
    }

    private void GUI() {

        getRootPane().setDefaultButton(btnLogin);

        add(new JLabel("Welcome! Log In to our system"), "span 2, center, wrap");
        add(this.lblUsername);
        add(this.txtUsername);
        add(this.lblPassword);
        add(this.passwordField);
        add(new JLabel(""));
        add(this.btnLogin, "split 2");
        add(this.btnExit);
        add(this.btnRegister);
    }

    private void actions() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitCheck();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitCheck();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                if (!username.equals("") & !password.equals("")) {
                    try {
                        mainService.getUserService().login(username, password);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter username and password!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterWindow registerWindow = new RegisterWindow(mainRepository, mainService);
                registerWindow.setVisible(true);
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    txtUsername.requestFocusInWindow();
                }
            }
        });

        txtUsername.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    passwordField.requestFocusInWindow();
                }
            }
        });

    }

    public static void exitCheck() {
        int option = JOptionPane.showConfirmDialog(null, "Are you sure that you want to exit?",
                "End of work", JOptionPane.YES_NO_CANCEL_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


}

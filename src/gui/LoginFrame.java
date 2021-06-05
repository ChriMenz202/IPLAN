/*Project: IPlaner
 *Package: gUI
 *Description:
 *Author: Christoph Menzinger
 *Last Change:  29.05.2021
 */

package gui;
import data.Value;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame {

    JLabel userLabel;
    JLabel passwordLabel;
    JTextField username;
    JPasswordField password;
    JButton loginButton;

    public LoginFrame(){
        new Value();
        Value.frame = new JFrame();
        Value.frame.setSize(400, 200);
        Value.frame.setTitle("Login");
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(false);

        userLabel = new JLabel("Username: ");
        userLabel.setBounds(20,20,200,40);
        Value.frame.getContentPane().add(userLabel);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(20,50,340,40);
        Value.frame.getContentPane().add(passwordLabel);

        username = new JTextField();
        password = new JPasswordField();
        username.setBounds(100,25,200,28);
        password.setBounds(100,55,200,28);
        Value.frame.getContentPane().add(username);
        Value.frame.getContentPane().add(password);

        loginButton = new JButton();

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 90, 150, 28);
        loginButton.setVisible(true);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < Value.users.getData().size(); i++) {
                        String[] singleUser = Value.users.getData().get(i).split(";");
                        if(String.valueOf(username.getText()).equals(singleUser[0]) && String.valueOf(password.getPassword()).equals(singleUser[1])){
                            if(singleUser[2].equals("isAdmin")){
                                Value.frame.setVisible(false);
                                new AdminFrame(singleUser[0]);
                                break;
                            }else if(singleUser[2].equals("isAssist")){
                                System.out.println("ja assistent");
                                break;
                            }else if(singleUser[2].equals("isStudent")){
                                Value.frame.setVisible(false);
                                new StudentFrame(singleUser[0]);
                                break;
                            }
                    }
                }
            }
        });
        Value.frame.getContentPane().add(loginButton);

        Value.frame.setVisible(true);

    }
}

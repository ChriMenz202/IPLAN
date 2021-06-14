/*Project: IPLAN
 *Package: data
 *Description:
 *Author: Christoph Menzinger
 *Last Change:  11.06.2021
 */

package data;


import gui.Editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Messages {

    TextArea message = new TextArea();
    CsvReader c;
    String path = System.getProperty("user.dir");

    public Messages(String user) {
        Value.frame = new JFrame();
        Value.frame.setSize(1000, 460);
        Value.frame.setTitle("Messages");
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(true);

        //student table

        message.setEditable(true);
        message.setBounds(10, 10, 700, 400);
        message.setVisible(true);


        File f = new File(path, user);
        c = new CsvReader(user);
        for (String line : c.getData()) {
            message.append(line + "\n");
        }
        Value.frame.getContentPane().add(message);

        try {
            FileWriter w = new FileWriter(f);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton users = new JButton("Verlassen");
        users.setBounds(720, 10, 155, 28);
        users.setVisible(true);
        users.setBackground(Color.WHITE);
        users.setForeground(Color.BLACK);
        users.setFocusPainted(false);
        users.addActionListener(e -> {
            for (String line : message.getText().split("\n")) {
                new CsvWriter(f, line);
            }
            Value.frame.setVisible(false);
            new Editor("/res/UserList.csv");

        });
        Value.frame.getContentPane().add(users);
        Value.frame.setVisible(true);
    }
}

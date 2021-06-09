/*Project: IPLAN
 *Package: data
 *Description:
 *Author: Christoph Menzinger
 *Last Change:  05.06.2021
 */

package gui;

import data.CsvReader;
import data.CsvWriter;
import data.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Editor {

    JTextArea field = new JTextArea();

    ///res/rooms.csv
    public Editor(String path) {
        Value.frame = new JFrame();
        Value.frame.setSize(500, 500);
        Value.frame.setTitle("EDITOR");
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(false);

        //student table
        field.setLineWrap(true);
        field.setWrapStyleWord(true);
        field.setEditable(true);
        field.setBounds(10, 10, 300, 435);
        field.setVisible(true);
        Value.frame.getContentPane().add(field);

        JButton save = new JButton("Speichern");
        save.setBounds(320, 417, 155, 28);
        save.setVisible(true);
        save.setBackground(Color.WHITE);
        save.setForeground(Color.BLACK);
        save.setFocusPainted(false);

        //rooms editor
        //coursesEditor
        //userListEditor
        switch (path) {
            case "/res/rooms.csv" -> {
                for (int i = 0; i < Value.rooms.getData().size(); i++) {
                    field.append(Value.rooms.getData().get(i) + "\n");
                }
                save.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String current = System.getProperty("user.dir");
                        File f = new File(current, path);
                        try {
                            FileWriter w = new FileWriter(f);
                            w.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        for (String line : field.getText().split("\n")) {
                            CsvWriter c = new CsvWriter(f, line);
                        }

                        refillLists("/res/rooms.csv");

                        Value.frame.setVisible(false);
                        new AdminFrame("ADMIN");
                    }
                });
                Value.frame.getContentPane().add(save);
                Value.frame.setVisible(true);
            }
            case "/res/Courses.csv" -> {
                for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                    field.append(Value.allCourses.getData().get(i) + "\n");
                }
                save.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String current = System.getProperty("user.dir");
                        File f = new File(current, path);
                        try {
                            FileWriter w = new FileWriter(f);
                            w.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        for (String line : field.getText().split("\n")) {
                            CsvWriter c = new CsvWriter(f, line);
                        }
                        refillLists("/res/Courses.csv");


                        Value.frame.setVisible(false);
                        new AdminFrame("ADMIN");
                    }
                });
                Value.frame.getContentPane().add(save);
                Value.frame.setVisible(true);
            }
            case "/res/UserList.csv" -> {
                for (int i = 0; i < Value.users.getData().size(); i++) {
                    field.append(Value.users.getData().get(i) + "\n");
                }
                save.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String current = System.getProperty("user.dir");
                        File f = new File(current, path);
                        try {
                            FileWriter w = new FileWriter(f);
                            w.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        for (String line : field.getText().split("\n")) {
                            CsvWriter c = new CsvWriter(f, line);
                        }
                        refillLists("/res/UserList.csv");

                        Value.frame.setVisible(false);
                        new AdminFrame("ADMIN");
                    }
                });
                Value.frame.getContentPane().add(save);
                Value.frame.setVisible(true);
            }
        }
    }

    public void refillLists(String directory){
        CsvReader c;
        switch (directory) {
            case "/res/UserList.csv" -> {
                for (int i = 0; i < Value.users.getData().size(); i++) {
                Value.users.getData().remove(i);
                }
                 c = new CsvReader(directory);
                Value.users = c;
            }
            case "/res/Courses.csv" -> {
                for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                    Value.allCourses.getData().remove(i);
                }
                 c = new CsvReader(directory);
                Value.allCourses = c;
            }
            case "/res/rooms.csv" ->{
                for (int i = 0; i < Value.rooms.getData().size(); i++) {
                    Value.rooms.getData().remove(i);
                }
                c = new CsvReader(directory);
                Value.rooms = c;
            }
        }
    }
}

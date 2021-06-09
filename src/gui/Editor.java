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

    /**
     * Create an rework courses, rooms and userList by reading the given
     * csv file, empty the file and after pushing the button fill the file
     * with the input of the TextArea
     *
     * @param path switch case for the path to rebuild the given file
     */
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

        switch (path) {
            case "/res/rooms.csv" -> {
                for (int i = 0; i < Value.rooms.getData().size(); i++) {
                    field.append(Value.rooms.getData().get(i) + "\n");
                }
                save.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(checkRooms()) {
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
                        }else{
                            JOptionPane.showMessageDialog(null, "Ihre Eingabe ist nicht korrekt, Raum muss folgend eingegeben werden \"R[1-9]\"", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                        }
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

    /**
     *delete Value.<lists> and empty the associated file
     *
     * @param directory of the file which gets deleted
     */
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

    /**
     * check if inputs in roomEditor are correct
     * @return boolean true or false
     */
    public boolean checkRooms(){
        boolean checked = false;
        for (String line: field.getText().split("\n")) {
            if (line.matches("R[0-9]*")){
                checked = true;
            }else{
                checked = false;
                break;
            }
        }
        return checked;
    }
}

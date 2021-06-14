/*Project: IPLAN
 *Package: data
 *Description:
 *Author: Christoph Menzinger
 *Last Change:  10.06.2021
 */

package data;

import compare.Course;
import compare.DayCompare;
import gui.Editor;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class StudentData {
    JTextArea field = new JTextArea();
    JTextField jf;
    CsvReader c;
    String name;
    String path = System.getProperty("user.dir");
    File f;
    ArrayList<Course> compareList = new ArrayList<>();

    /**
     * Create an rework courses, rooms and userList by reading the given
     * csv file, empty the file and after pushing the button fill the file
     * with the input of the TextArea
     *
     * @param loginDir switch case for the path to rebuild the given file
     */
    public StudentData(String loginDir) {

        fileConnection(loginDir);

        Value.frame = new JFrame();
        Value.frame.setSize(500, 500);
        Value.frame.setTitle("EDITOR");
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(false);

        //student table
        field.setLineWrap(true);
        field.setWrapStyleWord(true);
        field.setEditable(false);
        field.setBounds(10, 10, 300, 435);
        field.setVisible(true);

        fillField();
        Value.frame.getContentPane().add(field);

        jf = new JTextField();
        JLabel jLabel = new JLabel("Wähle einen Kurs");
        jf.setBounds(320, 10, 155, 28);
        jLabel.setBounds(350, 35, 150, 28);
        Value.frame.getContentPane().add(jf);
        Value.frame.getContentPane().add(jLabel);

        JButton save = new JButton("Zurück");
        save.setBounds(320, 417, 155, 28);
        save.setVisible(true);
        save.setBackground(Color.WHITE);
        save.setForeground(Color.BLACK);
        save.setFocusPainted(false);
        save.addActionListener(e -> {

            Value.frame.setVisible(false);
            new Editor("/res/UserList.csv");

        });
        Value.frame.getContentPane().add(save);

        JButton delete = new JButton("Löschen");
        delete.setBounds(320, 377, 155, 28);
        delete.setVisible(true);
        delete.setBackground(Color.WHITE);
        delete.setForeground(Color.BLACK);
        delete.setFocusPainted(false);
        delete.addActionListener(e -> {
            if (!(jf.getText().equals(""))) {
                changeFile();
            } else {
                JOptionPane.showMessageDialog(null, "Bitte geben sie einen Kurs ein", "Achtung", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        Value.frame.getContentPane().add(delete);

        Value.frame.setVisible(true);

    }

    public void fileConnection(String loginDir) {
        this.name = loginDir.toLowerCase(Locale.ROOT);

        //create the correct dir if not exists
        this.path = path + "\\res\\" + loginDir;
        f = new File(path + "\\course.csv");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.c = new CsvReader("/res/" + loginDir + "/course.csv");
    }

    public void fillField() {
        for (String c : c.getData()) {
            System.out.println(c);
        }

        field.setText("");
        for (int i = 0; i < c.getData().size(); i++) {
            String tempCourse = String.valueOf(c.getData().get(i));
            for (int j = 0; j < Value.allCourses.getData().size(); j++) {
                String tempCompare = String.valueOf(Value.allCourses.getData().get(j).split(";")[0]);
                if (String.valueOf(tempCourse).equals(String.valueOf(tempCompare))) {
                    String[] tempLine = Value.allCourses.getData().get(j).split(";");
                    Course c = new Course(tempLine[0], tempLine[1], tempLine[2], tempLine[3], tempLine[4]);
                    compareList.add(c);
                }
            }
        }
        comparing();
    }

    public void comparing() {
        DayCompare dc = new DayCompare();
        compareList.sort(dc);
        for (Course c : compareList) {
            field.append(c.getDay() + "   " + c.getName() + "   " + c.getTime() + "   " + c.getRoom() + "   " + c.getTeacher());
            field.append("\n");
        }
    }

    public void changeFile() {
        for (String f : c.getData()) {
            if (f.equals(jf.getText().toUpperCase(Locale.ROOT))) {
                c.getData().remove(f);
                break;
            }
        }
        field.setText("");

        try {
            FileWriter fw = new FileWriter(f, false);
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String a : c.getData()) {
            new CsvWriter(f, a);
            for (String all : Value.allCourses.getData()) {
                if (a.equals(all.split(";")[0])) {
                    Course c = new Course(all.split(";")[0], all.split(";")[1], all.split(";")[2], all.split(";")[3], all.split(";")[4]);
                    field.append(c.getDay() + "   " + c.getName() + "   " + c.getTime() + "   " + c.getRoom() + "   " + c.getTeacher());
                    field.append("\n");
                }
            }
        }
    }
}

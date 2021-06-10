/*Project: IPLAN
 *Package: gui
 *Description: assistant-frame with their courses
 *Author: Christoph Menzinger
 *Last Change:  10.06.2021
 */

package gui;

import compare.Course;
import compare.DayCompare;
import data.CsvWriter;
import data.Value;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class AssistantFrame {

    File f;
    String path = System.getProperty("user.dir");
    JTextArea course = new JTextArea();
    JTextArea courses = new JTextArea();
    JTextField courseField;
    ArrayList<Course> compareList = new ArrayList<>();
    String name;

    public AssistantFrame(String loginDir) {
        this.name = loginDir;

        for (String line : Value.allCourses.getData()) {
            if (name.toUpperCase(Locale.ROOT).equals(line.split(";")[4].toUpperCase(Locale.ROOT))) {
                Course c = new Course(line.split(";")[0], line.split(";")[1], line.split(";")[2], line.split(";")[3], line.split(";")[4]);
                compareList.add(c);
            }
        }
        comparing();

        Value.frame = new JFrame();
        Value.frame.setSize(500, 500);
        Value.frame.setTitle(loginDir.toUpperCase(Locale.ROOT));
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(false);

        //assistant table
        course.setLineWrap(true);
        course.setWrapStyleWord(true);
        course.setEditable(false);
        course.setBounds(10, 10, 300, 435);
        course.setVisible(true);
        Value.frame.getContentPane().add(course);

        JButton addCourse = new JButton("Kurse");
        addCourse.setBounds(320, 10, 155, 28);
        addCourse.setVisible(true);
        addCourse.setBackground(Color.WHITE);
        addCourse.setForeground(Color.BLACK);
        addCourse.setFocusPainted(false);
        Value.frame.getContentPane().add(addCourse);
        Value.frame.setVisible(true);

        addCourse.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setSize(500, 500);
            frame.setTitle("Kurse");
            frame.setLayout(null);
            frame.setLocation(1000, 270);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setVisible(true);

            courseField = new JTextField();
            JLabel jLabel = new JLabel("Gib eine Präferenz ein");
            courseField.setBounds(320, 10, 155, 28);
            jLabel.setBounds(335, 35, 150, 28);
            frame.getContentPane().add(courseField);
            frame.getContentPane().add(jLabel);

            courses = new JTextArea();
            courses.setLineWrap(true);
            courses.setWrapStyleWord(true);
            courses.setEditable(false);
            courses.setBounds(10, 10, 300, 435);
            courses.setVisible(true);
            courses.append("Kurse:\n\n");
            frame.getContentPane().add(courses);


            for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                String[] temp = Value.allCourses.getData().get(i).split(";");
                for (String s : temp) {
                    courses.append(s + ";");
                }
                courses.append("\n");
            }
            JButton addCourse1 = new JButton("Senden");
            addCourse1.setBounds(320, 80, 155, 28);
            addCourse1.setVisible(true);
            addCourse1.setBackground(Color.WHITE);
            addCourse1.setForeground(Color.BLACK);
            addCourse1.setFocusPainted(false);
            addCourse1.addActionListener(e1 -> sendMessage());
            frame.getContentPane().add(addCourse1);
            JButton exit = new JButton("schließen");
            exit.setBounds(320, 417, 155, 28);
            exit.setVisible(true);
            exit.setBackground(Color.WHITE);
            exit.setForeground(Color.BLACK);
            exit.setFocusPainted(false);
            exit.addActionListener(e12 -> frame.setVisible(false));
            frame.getContentPane().add(exit);
            frame.setVisible(true);
        });
        JButton exit = new JButton("Logout");
        exit.setBounds(320, 417, 155, 28);
        exit.setVisible(true);
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.BLACK);
        exit.setFocusPainted(false);
        exit.addActionListener(e -> {
            Value.frame.setVisible(false);
            Value.frame.setVisible(false);
            new LoginFrame();
        });
        Value.frame.getContentPane().add(exit);
        Value.frame.getContentPane().add(addCourse);
        Value.frame.setVisible(true);
    }

    public void comparing() {
        DayCompare dc = new DayCompare();
        compareList.sort(dc);
        for (Course c : compareList) {
            course.append(c.getDay() + "   " + c.getName() + "   " + c.getTime() + "   " + c.getRoom());
            course.append("\n");
        }
    }

    public void sendMessage() {
        path = System.getProperty("user.dir");
        path = path + "\\res\\admin\\messages.txt";
        f = new File(path);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean exist = false;
        for (String line : Value.allCourses.getData()) {
            if (line.split(";")[0].toUpperCase(Locale.ROOT).equals(courseField.getText().toUpperCase(Locale.ROOT))) {
                String message ="Von "+name.toUpperCase(Locale.ROOT)+ "\n\nSehr geehrter Admin!,\nHiermit teile ich Ihnen mit, dass ich mich für den Kure \"" + courseField.getText() + "\" als Lehrpersonal zur Verfügung stellen möchte!\n" +
                        "--------------------------------------------------------------------------------\n";
                new CsvWriter(f, message);
                courseField.setText("");
                JOptionPane.showMessageDialog(null, "Ihre Präferenz wurde dem Admin mitgeteilt");
                exist = true;
                break;
            }
        }
        if (!exist) {
            JOptionPane.showMessageDialog(null, "Der Angegebene Kurs existiert nicht", "Achtung", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

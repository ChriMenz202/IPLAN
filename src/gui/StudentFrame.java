/*Project: IPLAN
 *Package: gui
 *Description: student-frame with their courses
 *Author: Christoph Menzinger
 *Last Change:  04.06.2021
 */

package gui;

import compare.Course;
import compare.DayCompare;
import data.CsvReader;
import data.CsvWriter;
import data.Value;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * if the login data match with a student call this class
 */
public class StudentFrame {
    File f;
    String path = System.getProperty("user.dir");
    JTextArea course = new JTextArea();
    JTextField courseField = new JTextField();
    JTextArea courses = new JTextArea();
    CsvReader studentCourses;
    ArrayList<Course> compareList = new ArrayList<>();
    String name;

    /**
     * create student object
     * students can see all courses and can sign up to other courses
     *
     * @param loginDir = is the students login name; login name = direction of his storage in /res
     */
    public StudentFrame(String loginDir) {
        this.name = loginDir;

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

        this.studentCourses = new CsvReader("/res/" + loginDir + "/course.csv");

        Value.frame = new JFrame();
        Value.frame.setSize(500, 500);
        Value.frame.setTitle(loginDir.toUpperCase(Locale.ROOT));
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(false);

        //student table
        course.setLineWrap(true);
        course.setWrapStyleWord(true);
        course.setEditable(false);
        course.setBounds(10, 10, 300, 435);
        course.setVisible(true);
        Value.frame.getContentPane().add(course);

        //TODO TIME MATCH QUERY
        addStudentCourses();

        JButton addCourse = new JButton("Kurse");
        addCourse.setBounds(320, 10, 155, 28);
        addCourse.setVisible(true);
        addCourse.setBackground(Color.WHITE);
        addCourse.setForeground(Color.BLACK);
        addCourse.setFocusPainted(false);
        //student can sign in in courses
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
            JLabel jLabel = new JLabel("Wähle einen Kurs");
            courseField.setBounds(320, 10, 155, 28);
            jLabel.setBounds(350, 35, 150, 28);
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
            JButton addCourse1 = new JButton("Kurs beitreten");
            addCourse1.setBounds(320, 80, 155, 28);
            addCourse1.setVisible(true);
            addCourse1.setBackground(Color.WHITE);
            addCourse1.setForeground(Color.BLACK);
            addCourse1.setFocusPainted(false);
            addCourse1.addActionListener(e1 -> courseExist());
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

        JButton message = new JButton("Ticket");
        message.setBounds(320, 50, 155, 28);
        message.setVisible(true);
        message.setBackground(Color.WHITE);
        message.setForeground(Color.BLACK);
        message.setFocusPainted(false);
        message.addActionListener(e -> {

            JFrame frame = new JFrame();
            frame.setSize(500, 500);
            frame.setTitle("Kurse");
            frame.setLayout(null);
            frame.setLocation(1000, 270);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.setVisible(true);

            JLabel label = new JLabel("Nachricht an den Admin");
            label.setBounds(20, 10, 200, 40);
            frame.getContentPane().add(label);

            courses = new JTextArea();
            courses.setLineWrap(true);
            courses.setWrapStyleWord(true);
            courses.setEditable(true);
            courses.setBounds(10, 40, 300, 435);
            courses.setVisible(true);
            frame.getContentPane().add(courses);

            JButton send = new JButton("Nachricht Senden");
            send.setBounds(320, 448, 155, 28);
            send.setVisible(true);
            send.setBackground(Color.WHITE);
            send.setForeground(Color.BLACK);
            send.setFocusPainted(false);
            send.addActionListener(e13 -> {
                sendMessage();
                frame.setVisible(false);
            });
            frame.getContentPane().add(send);
        });

        Value.frame.getContentPane().add(message);

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

    /**
     * checks if the course exists or if you are already in there
     */
    public void courseExist() {
        String chose = courseField.getText().toUpperCase(Locale.ROOT);
        boolean check = true;
        boolean exist = true;
        String set = "";
        for (int i = 0; i < Value.allCourses.getData().size(); i++) {
            if (chose.equals(Value.allCourses.getData().get(i).split(";")[0])) {
                exist = true;
                break;
            } else {
                exist = false;
            }
        }
        if (!exist) {
            JOptionPane.showMessageDialog(null, "Dieser Kurs existiert nicht!", "Achtung", JOptionPane.INFORMATION_MESSAGE);
        }
        for (int i = 0; i < studentCourses.getData().size(); i++) {

            //course already sign in
            if (chose.equals(String.valueOf(studentCourses.getData().get(i).split(";")[0]))) {
                JOptionPane.showMessageDialog(null, "Du bist bereits in diesem Kurs!", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                check = false;
            }
        }
        if (check && exist) {
            for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                if (chose.equals(String.valueOf(Value.allCourses.getData().get(i).split(";")[0]))) {
                    set = Value.allCourses.getData().get(i);
                }
            }
            studentCourses.setData(chose);
            new CsvWriter(f, chose);
            String[] temp = set.split(";");
            for (String s : temp) {
                course.append(s + "    ");
            }
            course.append("\n");
        }
    }

    /**
     * Send messages to the admin (messages.txt)
     */
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
        String header = "Von "+name.toUpperCase(Locale.ROOT)+"\n";
        new  CsvWriter(f,header);
        for (String line : courses.getText().split("\n")) {
            new CsvWriter(f, line);
        }
        String message = "--------------------------------------------------------------------------------";
        new  CsvWriter(f,message);
    }

    /**
     * compare 1 element of students course with all courses
     * if the course exist print course infos
     */
    public void addStudentCourses() {
        course.setText("");
        for (int i = 0; i < studentCourses.getData().size(); i++) {
            String tempCourse = String.valueOf(studentCourses.getData().get(i));
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
            course.append(c.getDay() + "   " + c.getName() + "   " + c.getTime() + "   " + c.getRoom() + "   " + c.getTeacher());
            course.append("\n");
        }
    }
}

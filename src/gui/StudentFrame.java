/*Project: IPLAN
 *Package: gui
 *Description: student-frame with their courses
 *Author: Christoph Menzinger
 *Last Change:  04.06.2021
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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


    /**
     * create student object
     * students can see all courses and can sign up to other courses
     *
     * @param loginDir = is the students login name; login name = direction of his storage in /res
     */
    public StudentFrame(String loginDir) {

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
        course.append("Course  Day  Time  Room\n\n");
        Value.frame.getContentPane().add(course);


        //TODO SORT DAYS
        //TODO TIME MATCH QUERY


        /*
           compare 1 element of students course with all courses
            if the course exist print course infos
         */

        for (int i = 0; i < studentCourses.getData().size(); i++) {
            String tempCourse = String.valueOf(studentCourses.getData().get(i));
            for (int j = 0; j < Value.allCourses.getData().size(); j++) {
                String tempCompare = String.valueOf(Value.allCourses.getData().get(j).split(";")[0]);
                if (String.valueOf(tempCourse).equals(String.valueOf(tempCompare))) {
                    String[] tempLine = Value.allCourses.getData().get(j).split(";");
                    for (int t = 0; t < tempLine.length; t++) {
                        course.append(tempLine[t]+";");
                    }
                    course.append("\n");
                }
            }
        }

        JButton addCourse = new JButton("Kurse");
        addCourse.setBounds(320, 10, 155, 28);
        addCourse.setVisible(true);
        addCourse.setBackground(Color.WHITE);
        addCourse.setForeground(Color.BLACK);
        addCourse.setFocusPainted(false);
        addCourse.addActionListener(new ActionListener() {

            //student can sign in in courses
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setSize(500, 500);
                frame.setTitle("Kurse");
                frame.setLayout(null);
                frame.setLocation(1000,270);
                frame.setUndecorated(true);
                frame.setResizable(false);
                frame.setVisible(true);

                courseField = new JTextField();
                JLabel jLabel = new JLabel("Wähle einen Kurs");
                courseField.setBounds(320,10,155,28);
                jLabel.setBounds(350,35,150,28);
                frame.getContentPane().add(courseField);
                frame.getContentPane().add(jLabel);


                courses = new JTextArea();
                courses.setLineWrap(true);
                courses.setWrapStyleWord(true);
                courses.setEditable(false);
                courses.setBounds(10, 10, 300, 435);
                courses.setVisible(true);
                courses.append("Course:\n\n");
                frame.getContentPane().add(courses);

                for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                    String[] temp = Value.allCourses.getData().get(i).split(";");
                    for (int j = 0; j < temp.length; j++) {
                        courses.append(temp[j] + ";");
                    }
                    courses.append("\n");
                }
                JButton addCourse = new JButton("Kurs beitreten");
                addCourse.setBounds(320, 80, 155, 28);
                addCourse.setVisible(true);
                addCourse.setBackground(Color.WHITE);
                addCourse.setForeground(Color.BLACK);
                addCourse.setFocusPainted(false);
                addCourse.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        courseExist();
                        //TODO
                    }
                });
                frame.getContentPane().add(addCourse);

                JButton exit = new JButton("schließen");
                exit.setBounds(320, 417, 155, 28);
                exit.setVisible(true);
                exit.setBackground(Color.WHITE);
                exit.setForeground(Color.BLACK);
                exit.setFocusPainted(false);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.setVisible(false);
                    }
                });
                frame.getContentPane().add(exit);
                frame.setVisible(true);
            }
        });
        JButton exit = new JButton("Logout");
        exit.setBounds(320, 417, 155, 28);
        exit.setVisible(true);
        exit.setBackground(Color.WHITE);
        exit.setForeground(Color.BLACK);
        exit.setFocusPainted(false);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Value.frame.setVisible(false);
                Value.frame.setVisible(false);
                new LoginFrame();
            }
        });
        Value.frame.getContentPane().add(exit);
        Value.frame.getContentPane().add(addCourse);
        Value.frame.setVisible(true);
    }

    public void courseExist(){
        String chose = courseField.getText().toUpperCase(Locale.ROOT);
        boolean check = true;
        boolean exist = true;
        String set = "";
        for (int i = 0; i < Value.allCourses.getData().size(); i++) {
            if(chose.equals(Value.allCourses.getData().get(i).split(";")[0])){
                exist = true;
                break;
            }else{
                exist = false;
            }
        }
        if (exist == false){
            JOptionPane.showMessageDialog(null, "Dieser Kurs existiert nicht!", "Achtung", JOptionPane.INFORMATION_MESSAGE);
        }
        for (int i = 0; i < studentCourses.getData().size(); i++) {

            //course already sign in
            if (chose.equals(String.valueOf(studentCourses.getData().get(i).split(";")[0]))) {
                JOptionPane.showMessageDialog(null, "Du bist bereits in diesem Kurs!", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                check = false;
            }
        }
        if (check && exist){
            for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                if(chose.equals(String.valueOf(Value.allCourses.getData().get(i).split(";")[0]))){
                    set= Value.allCourses.getData().get(i);
                }
            }
            studentCourses.setData(chose);
            CsvWriter c = new CsvWriter(f,chose);
            String[] temp = set.split(";");
            for (int i = 0; i < temp.length; i++) {
                course.append(temp[i]+"    ");
            }
            course.append("\n");
        }
    }

}

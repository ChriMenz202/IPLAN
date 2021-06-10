/*Project: IPLAN
 *Package: data
 *Description: editing with Gui and change the associated file
 *Author: Christoph Menzinger
 *Last Change:  05.06.2021
 */

package gui;

import data.CsvReader;
import data.CsvWriter;
import data.StudentData;
import data.Value;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Editor {

    JTextArea field = new JTextArea();
    JTextField role;

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
                save.addActionListener(e -> {
                    if (checkRooms() && uniqueCheck()) {
                        String current = System.getProperty("user.dir");
                        File f = new File(current, path);
                        try {
                            FileWriter w = new FileWriter(f);
                            w.close();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        for (String line : field.getText().split("\n")) {
                            new CsvWriter(f, line);
                        }

                        refillLists("/res/rooms.csv");

                        Value.frame.setVisible(false);
                        new AdminFrame("ADMIN");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ihre Eingabe ist nicht korrekt, Raum muss folgend eingegeben werden \"R[1-9]\"", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                Value.frame.getContentPane().add(save);
                Value.frame.setVisible(true);
            }
            case "/res/Courses.csv" -> {
                for (int i = 0; i < Value.allCourses.getData().size(); i++) {
                    field.append(Value.allCourses.getData().get(i) + "\n");
                }
                save.addActionListener(e -> {

                    try {
                        if (checkTimeSpan() && checkMinutes() && checkroomsExist() && checkDate() && compareRoomCourse() && checkHours() && uniqueCheck()) {
                            String current = System.getProperty("user.dir");
                            File f = new File(current, path);
                            try {
                                FileWriter w = new FileWriter(f);
                                w.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            for (String line : field.getText().split("\n")) {
                                new CsvWriter(f, line);
                            }
                            refillLists("/res/Courses.csv");

                            Value.frame.setVisible(false);
                            new AdminFrame("ADMIN");
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Die Eingabe ist nicht Korrekt!\n Halten Sie sich an die Vorgaben!\n");
                    }
                });
                Value.frame.getContentPane().add(save);
                Value.frame.setVisible(true);
            }
            case "/res/UserList.csv" -> {
                role = new JTextField();
                role.setBounds(320, 10, 155, 28);
                JLabel jLabel = new JLabel("Wähle Student");
                jLabel.setBounds(360, 35, 150, 28);
                Value.frame.getContentPane().add(role);
                Value.frame.getContentPane().add(jLabel);

                JButton edit = new JButton("Bearbeiten");
                edit.setBounds(320, 377, 155, 28);
                edit.setVisible(true);
                edit.setBackground(Color.WHITE);
                edit.setForeground(Color.BLACK);
                edit.setFocusPainted(false);
                edit.addActionListener(e -> {
                    if(role.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Keine Eingabe getätigt", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        Value.frame.setVisible(false);
                        new StudentData(role.getText());
                    }

                });
                Value.frame.getContentPane().add(edit);

                for (int i = 0; i < Value.users.getData().size(); i++) {
                    field.append(Value.users.getData().get(i) + "\n");
                }
                save.addActionListener(e -> {
                    try {
                        if (checkUser() && uniqueCheck()) {
                            String current = System.getProperty("user.dir");
                            File f = new File(current, path);
                            try {
                                FileWriter w = new FileWriter(f);
                                w.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            for (String line : field.getText().split("\n")) {
                                new CsvWriter(f, line);
                            }
                            refillLists("/res/UserList.csv");

                            Value.frame.setVisible(false);
                            new AdminFrame("ADMIN");
                        }
                    } catch (ArrayIndexOutOfBoundsException ae) {
                        JOptionPane.showMessageDialog(null, "Eingabe nicht Korrekt\n Keine Rolle vergeben!\n");
                    }
                });
                Value.frame.getContentPane().add(save);
                Value.frame.setVisible(true);
            }
        }
    }

    /**
     * delete Value.<lists> and empty the associated file
     *
     * @param directory of the file which gets deleted
     */
    public void refillLists(String directory) {
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
            case "/res/rooms.csv" -> {
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
     *
     * @return boolean true or false
     * false JOptionPane
     */
    public boolean checkRooms() {
        boolean checked = false;
        for (String line : field.getText().split("\n")) {
            if (line.matches("R[0-9]*")) {
                checked = true;
            } else {
                checked = false;
                break;
            }
        }
        return checked;
    }

    /**
     * checks if the minuets is 0
     *
     * @return ture if all minutes are correct
     * false JOptionPane
     * @throws ArrayIndexOutOfBoundsException to catch wrong inputs
     */
    public boolean checkMinutes() throws ArrayIndexOutOfBoundsException {
        boolean checked = false;
        for (String line : field.getText().split("\n")) {
            String[] temp = line.split(";");
            String[] i = (temp[2].split("-"));
            int from = Integer.parseInt(i[0].split(":")[1]);
            int to = Integer.parseInt(i[1].split(":")[1]);
            if (from != 0 || to != 0) {
                checked = false;
                JOptionPane.showMessageDialog(null, "Eingegebene Zeit ist nicht Korrekt", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                checked = true;
            }
        }
        return checked;
    }

    /**
     * checks if the courses between 8 and 22:59
     *
     * @return ture if all courses are correct
     * false JOptionPane
     * @throws ArrayIndexOutOfBoundsException to catch wrong inputs
     */
    public boolean checkTimeSpan() throws ArrayIndexOutOfBoundsException {
        boolean checked = false;
        for (String line : field.getText().split("\n")) {
            String[] temp = line.split(";");
            String[] i = (temp[2].split("-"));
            int from = Integer.parseInt(i[0].split(":")[0]);
            int to = Integer.parseInt(i[1].split(":")[0]);
            if (from < 8 || from >= 23 || (to >= 23 || to < 8)) {
                checked = false;
                JOptionPane.showMessageDialog(null, "Eingegebene Zeit ist nicht Korrekt", "Achtung", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                checked = true;
            }
        }
        return checked;
    }

    /**
     * checks every room-index in Courses
     * int counter = founded room which matches one in the roomlist
     * int field = each room in Courses
     *
     * @return true when all rooms in courses matches rooms in the roomList
     * false JOptionPane
     */
    public boolean checkroomsExist() {
        int counter = 0;
        int linesInField = 0;
        for (String line : field.getText().split("\n")) {
            String room = line.split(";")[3];
            linesInField++;
            for (String rooms : Value.rooms.getData()) {
                if (room.equals(rooms)) {
                    counter++;
                }
            }
        }
        if (counter == linesInField) {
            return true;
        } else {
            StringBuilder options = new StringBuilder();
            for (String line : Value.rooms.getData()) {
                options.append(line).append(" ");
            }
            JOptionPane.showMessageDialog(null, "Ein angegebener Raum existiert nicht\n" + "Wähle aus den folgenden: \n" + options);
            return false;
        }
    }

    /**
     * checks every day-index in Courses
     * int counter = founded day which matches one in the day-array
     * int field = each day in Courses
     *
     * @return true when all days in courses matches days in the array
     * false JOptionPane
     */
    public boolean checkDate() {
        String[] days = {"Mo", "Di", "Mi", "Do", "Fr"};
        String allDays = "Mo; Di; Mi; Do; Fr";
        int counter = 0;
        int linesInField = 0;
        for (String line : field.getText().split("\n")) {
            String day = line.split(";")[1];
            linesInField++;
            for (String d : days) {
                if (d.equals(day)) {
                    counter++;
                }
            }
        }
        if (linesInField == counter) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Eingegebener Tag nicht korrekt\n Wähle einen der folgenend Tage aus:\n" + allDays);
            return false;
        }
    }

    /**
     * @return true if there is no redundancies
     * @throws ArrayIndexOutOfBoundsException to catch wrong inputs
     */
    public boolean compareRoomCourse() throws ArrayIndexOutOfBoundsException {
        boolean check = true;
        for (int i = 0; i < field.getText().split("\n").length; i++) {
            String line = field.getText().split("\n")[i];
            String day = line.split(";")[1];
            String room = line.split(";")[3];
            String hourFrom = line.split(";")[2].split("-")[0].split(":")[0];
            String hourTo = line.split(";")[2].split("-")[1].split(":")[0];

            for (int j = i + 1; j < field.getText().split("\n").length; j++) {
                String input = field.getText().split("\n")[j];
                String compareDay = input.split(";")[1];
                String compareRoom = input.split(";")[3];
                String compareHourFrom = input.split(";")[2].split("-")[0].split(":")[0];
                if (day.equals(compareDay) && room.equals(compareRoom) && (hourFrom.equals(compareHourFrom) || Integer.parseInt(compareHourFrom) <= Integer.parseInt(hourTo))) {
                    JOptionPane.showMessageDialog(null, "Die eingegebenen Zeitfenster überschneiden sich mit den Räumen!\n Überprüfen sie ihre Eingabe!\n");
                    check = false;
                    break;
                }
            }
        }
        return check;
    }

    /**
     * check if the input time is valid
     *
     * @return ture if hour one is less than hour two
     */
    public boolean checkHours() {
        boolean checked = true;
        for (String line : field.getText().split("\n")) {
            int hourFrom = Integer.parseInt(line.split(";")[2].split("-")[0].split(":")[0]);
            int hourTo = Integer.parseInt(line.split(";")[2].split("-")[1].split(":")[0]);
            if (hourTo <= hourFrom) {
                checked = false;
                JOptionPane.showMessageDialog(null, "Die Zeiteingabe ist nicht Korrekt\n Überprüfen sie ihre Studen-Eingabe!\n");
            }
        }
        return checked;
    }

    /**
     * checkes if the given role exist
     *
     * @return true if all users in UserList are in a existing role
     * @throws ArrayIndexOutOfBoundsException if line is wrong
     */
    public boolean checkUser() throws ArrayIndexOutOfBoundsException {
        boolean checked = true;
        for (String line : field.getText().split("\n")) {
            String[] input = line.split(";");
            int length = input.length - 1;
            if (!(input[length].matches("isAdmin")) && !(input[length].matches("isStudent")) && !(input[length].matches("isAssist"))) {
                checked = false;
                break;
            }
        }
        if (!checked) {
            JOptionPane.showMessageDialog(null, "Die Eingaben sind nicht Korrekt\n Überprüfen sie die Konvention!\n");
        }
        return checked;
    }

    /**
     * compares each element with each other to check redundancies
     *
     * @return true if there is every course unique
     */
    public boolean uniqueCheck() {
        boolean check = true;
        for (int j = 0; j < field.getText().split("\n").length; j++) {
            String line = field.getText().split("\n")[j];
            for (int i = j + 1; i < field.getText().split("\n").length; i++) {
                String checkLine = field.getText().split("\n")[i];
                if (line.equals(checkLine)) {
                    check = false;
                    JOptionPane.showMessageDialog(null, "Achtung Redundanzen!\n Überprüfen sie ihre Eingaben!\n");
                }
            }
        }
        return check;
    }
}

/*Project: IPLAN
 *Package: gui
 *Description: admin frame
 *Author: Christoph Menzinger
 *Last Change:  05.06.2021
 */

package gui;


import data.Value;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Locale;

public class AdminFrame{

/**
 * create student object
 * students can see all courses and can sign up to other courses
 *
 * @param loginDir = is the students login name; login name = direction of his storage in /res
 */
public AdminFrame(String loginDir){

        Value.frame=new JFrame();
        Value.frame.setSize(500,500);
        Value.frame.setTitle(loginDir.toUpperCase(Locale.ROOT));
        Value.frame.setLayout(null);
        Value.frame.setLocationRelativeTo(null);
        Value.frame.setResizable(false);


        //save button
        JButton save = new JButton("Speichern");
        save.setBounds(320, 417, 155, 28);
        save.setVisible(true);
        save.setBackground(Color.WHITE);
        save.setForeground(Color.BLACK);
        save.setFocusPainted(false);
        save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        Value.frame.setVisible(false);
                        new LoginFrame();
                }
        });
        Value.frame.getContentPane().add(save);

        JButton rooms = new JButton("Räume verwalten");
        rooms.setBounds(320, 10, 155, 28);
        rooms.setVisible(true);
        rooms.setBackground(Color.WHITE);
        rooms.setForeground(Color.BLACK);
        rooms.setFocusPainted(false);
        rooms.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        Value.frame.setVisible(false);
                        new Editor("/res/rooms.csv");
                }
        });

        Value.frame.getContentPane().add(rooms);

        JButton courses = new JButton("Kurse verwalten");
        courses.setBounds(320, 50, 155, 28);
        courses.setVisible(true);
        courses.setBackground(Color.WHITE);
        courses.setForeground(Color.BLACK);
        courses.setFocusPainted(false);
        courses.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        Value.frame.setVisible(false);
                        new Editor("/res/Courses.csv");
                }
        });

        Value.frame.getContentPane().add(courses);

        JButton users = new JButton("User verwalten");
        users.setBounds(320, 90, 155, 28);
        users.setVisible(true);
        users.setBackground(Color.WHITE);
        users.setForeground(Color.BLACK);
        users.setFocusPainted(false);
        users.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        Value.frame.setVisible(false);
                        new Editor("/res/UserList.csv");
                }
        });

        Value.frame.getContentPane().add(users);


        Value.frame.setVisible(true);
        }
}
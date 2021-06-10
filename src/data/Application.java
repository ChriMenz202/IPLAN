/*Project: IPLAN
 *Package: PACKAGE_NAME
 *Description: main class access point of the program
 *Author: Christoph Menzinger
 *Last Change:  04.06.2021
 */

package data;

import gui.AdminFrame;
import gui.AssistantFrame;
import gui.LoginFrame;
import gui.StudentFrame;


/**
 * TODO
 * Studierende sollten nicht in zwei Kursen eingeschrieben zur gleichen Zeit sein
 * only choose student role
 * Message field in Admin to read and delete Messages
 * evtl student cousefield aktualieseiren
 */

public class Application {
    public static void main(String[] args) {
        //new AdminFrame("admin");
        //new StudentData("student");
        //new StudentFrame("student");
        new LoginFrame();
        //new AssistantFrame("assist");
    }
}

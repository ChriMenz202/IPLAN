/*Project: IPLAN
 *Package: PACKAGE_NAME
 *Description: main class access point of the program
 *Author: Christoph Menzinger
 *Last Change:  04.06.2021
 */

package data;

import gui.AdminFrame;
import gui.LoginFrame;
import gui.StudentFrame;


/**
 * TODO
 * LehrPersonal präferenz
 * Studierende sollten nicht in zwei Kursen eingeschrieben zur gleichen Zeit sein
 */

public class Application {
    public static void main(String[] args) {
        //new AdminFrame("admin");
//        new StudentFrame("student");
        new LoginFrame();
    }
}

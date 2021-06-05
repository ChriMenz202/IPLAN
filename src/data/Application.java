/*Project: IPLAN
 *Package: PACKAGE_NAME
 *Description:
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
 * Zwei Kurse im selben Zeitfenster
 * Keine Kurse vor 8 und nach 23 Uhr
 * Studierende sollten nicht in zwei Kursen eingeschrieben zur gleichen Zeit sein
 */

public class Application {
    public static void main(String[] args){
//        new AdminFrame("admin");
//        new StudentFrame("student");
        new LoginFrame();
    }
}

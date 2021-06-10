/*Project: IPLAN
 *Package: data
 *Description: collection of static values = can be used throughout the program
 *Author: Christoph Menzinger
 *Last Change:  04.06.2021
 */

package data;

import javax.swing.*;
import java.io.File;

public class Value {

    public static JFrame frame;
    public static CsvReader users = new CsvReader("/res/UserList.csv");
    public static CsvReader allCourses = new CsvReader("/res/Courses.csv");
    public static CsvReader rooms = new CsvReader("/res/rooms.csv");

    public Value() {

        //create directory for all users in UserList (if not exists)
        for (int i = 0; i < Value.users.getData().size(); i++) {
            String current = System.getProperty("user.dir");
            File dir = new File(current + "/res", Value.users.getData().get(i).split(";")[0]);
            if (!dir.exists()) {
                dir.mkdir();
            }
        }
    }
}

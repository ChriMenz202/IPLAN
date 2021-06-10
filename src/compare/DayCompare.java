/*Project: IPLAN
 *Package: compare
 *Description: Compare courses by day and than by time
 *Author: Christoph Menzinger
 *Last Change:  10.06.2021
 */

package compare;

import java.util.Comparator;

public class DayCompare implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        if (o1.getDay().compareTo(o2.getDay()) == 0) {
            return o1.getTime().compareTo(o2.getTime());
        } else {
            return o1.getDay().compareTo(o2.getDay());
        }
    }
}

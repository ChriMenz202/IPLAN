/*Project: IPLAN
 *Package: data
 *Description: enum for comparing days
 *Author: Christoph Menzinger
 *Last Change:  10.06.2021
 */

package compare;

public enum Day {
    Mo("Mo"),
    Di("Di"),
    Mi("Mi"),
    Do("Do"),
    Fr("Fr");

    String day;

    Day(String day) {
        this.day = day;
    }

    public String toString() {
        return day;
    }
}

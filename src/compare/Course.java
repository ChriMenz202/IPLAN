/*Project: IPLAN
 *Package: data
 *Description: edit course for comparing
 *Author: Christoph Menzinger
 *Last Change:  10.06.2021
 */

package compare;

public class Course {
    private final String name;
    private final Day day;
    private final String time;
    private final String room;
    private final String teacher;

    public Course(String name, String day, String time, String room, String teacher) {
        this.name = name;
        this.day = Day.valueOf(day);
        this.time = time;
        this.room = room;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public Day getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getRoom() {
        return room;
    }

    public String getTeacher() {
        return teacher;
    }

}

package entities;

import orm.config.annotations.Column;
import orm.config.annotations.Entity;
import orm.config.annotations.Id;

@Entity(name ="courses")
public class Courses {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "length_in_weeks")
    private int weekLength;

    public Courses(String name, int weekLength) {
        this.name = name;
        this.weekLength = weekLength;
    }
}

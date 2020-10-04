package studentsTeams.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String teamNumber;

    private LinkedHashSet<Student> students;

    private Integer size;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "teams")
    public LinkedHashSet<Student> getStudents() {
        return students;
    }

    public String getTeamNumber() {
        return teamNumber;
    }


    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    public void setStudents(LinkedHashSet<Student> students) {
        this.students = students;
    }

    public Team() {
    }

    public Team(String teamNumber) {
        this.size = 0;
        this.teamNumber = teamNumber;
        this.students = new LinkedHashSet<Student>();
    }
}

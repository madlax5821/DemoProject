package demoProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table (name = "hobbies")
public class Hobby {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "student_id")
    private Student student;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudent(Student student){
        this.student=student;
    }

    public Student getStudent(){
        return this.student;
    }

    public void addStudent(Student student){
        student.getHobbies().add(this);
        this.setStudent(student);
    }

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

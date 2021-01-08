package demoProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "course")
public class Course {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String name;
    @Column
    private String info;

    @JsonIgnore
    @ManyToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private Set<Student> students;


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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setStudents(Set<Student> students){
        this.students=students;
    }

    public Set<Student> getStudents(){
        if (this.students==null){
            students=new HashSet<>();
        }
        return students;
    }

    public void addStudent(Student student){
        student.getCourses().add(this);
        this.getStudents().add(student);
    }

    public void removeStudent(Student student){
        student.getCourses().remove(this);
        this.getStudents().remove(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}

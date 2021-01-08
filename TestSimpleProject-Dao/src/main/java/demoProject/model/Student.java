package demoProject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    long id;
    @Column (name = "name")
    String name;
    @Column (name = "age")
    int age;
    @Column (name = "personality")
    String personality;

    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Hobby> hobbies;

    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getId(){
        return this.id;
    }

    public void setCourses(Set<Course> courses){
        this.courses=courses;
    }

    public Set<Course> getCourses(){
        if (this.courses==null){
            courses = new HashSet<>();
        }
        return courses;
    }

    public void setHobbies(Set<Hobby> hobbies){
        this.hobbies = hobbies;
    }

    public Set<Hobby> getHobbies(){
        if (this.hobbies==null){
            hobbies=new HashSet<>();
        }
        return hobbies;
    }

    public void addCourse(Course course){
        this.getCourses().add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Course course){
        this.getCourses().remove(course);
        course.getStudents().remove(this);
    }

    public void addHobbies(Hobby hobby){
        this.hobbies.add(hobby);
        hobby.addStudent(this);
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", personality='" + personality + '\'' +
                '}';
    }
}

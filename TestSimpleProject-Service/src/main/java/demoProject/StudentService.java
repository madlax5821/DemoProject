package demoProject;

import demoProject.dao.StudentDao;
import demoProject.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public Student save(Student student){return studentDao.save(student);}
}

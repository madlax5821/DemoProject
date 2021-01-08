package demoProject.repository;

import demoProject.dao.StudentDao;
import demoProject.model.Student;
import demoProject.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {
    private Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);

    @Override
    public Student save(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(student);
            transaction.commit();
            session.close();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback();
            }
            logger.error("failed to insert record, error={}", e.getMessage());
            session.close();
        }
        return student;
    }
}

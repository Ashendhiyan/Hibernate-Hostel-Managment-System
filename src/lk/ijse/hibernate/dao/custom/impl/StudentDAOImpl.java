package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String text) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, text);


        transaction.commit();
        session.close();
        return student;
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student load = session.load(Student.class, pk);
        session.delete(load);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Student> findAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM student";
        List<Student> StudentList = session.createQuery(hql).list();


        transaction.commit();
        session.close();
        return StudentList;
    }

    @Override
    public boolean findByPk(String pk) throws SQLException, ClassNotFoundException, IOException {
        search(pk);
        return true;
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT student_id FROM  student ";

        List<String> studentIdList = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return studentIdList;
    }
}

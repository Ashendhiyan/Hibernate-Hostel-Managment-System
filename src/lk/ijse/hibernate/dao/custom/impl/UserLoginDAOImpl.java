package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.UserLoginDAO;
import lk.ijse.hibernate.entity.UserLogin;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserLoginDAOImpl implements UserLoginDAO {
    @Override
    public boolean save(UserLogin entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(UserLogin entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public UserLogin search(String text) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        UserLogin user = session.find(UserLogin.class, text);



        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        UserLogin load = session.load(UserLogin.class, pk);
        session.delete(load);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<UserLogin> findAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM user_login ";
        Query query=session.createQuery(hql);
        List<UserLogin> userLogins=query.list();

        transaction.commit();
        session.close();
        return userLogins;
    }

    @Override
    public boolean findByPk(String pk) throws SQLException, ClassNotFoundException, IOException {
        search(pk);
        return true;
    }

    @Override
    public List<String> searchName(String name) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT user_name FROM user_login";

        List<String> list = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return list;
    }
}

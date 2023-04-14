package lk.ijse.hibernate.dao.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.util.FactoryConfiguration;
import lk.ijse.hibernate.view.dtm.RemainKeyMoneyTM;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public boolean save(Reservation entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation search(String text) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.get(Reservation.class, text);

        transaction.commit();
        session.close();
        return reservation;
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation load = session.load(Reservation.class, pk);
        session.delete(load);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Reservation> findAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Reservation ";

        List<Reservation> reservationList = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return reservationList;
    }

    @Override
    public boolean findByPk(String pk) throws SQLException, ClassNotFoundException, IOException {
        search(pk);
        return true;
    }

    @Override
    public ObservableList<RemainKeyMoneyTM> getRemainKeyMoney() throws SQLException, ClassNotFoundException, IOException {
        ObservableList<RemainKeyMoneyTM> students = FXCollections.observableArrayList();

        Session session = FactoryConfiguration.getInstance().getSession();
        String hql="FROM Reservation WHERE status=' Paid Later ' ";
        List<Reservation> list = session.createQuery(hql).list();
        for (Reservation reservation : list) {
            String studentId = reservation.getStudent().getStudent_id();
            String name = reservation.getStudent().getName();
            String status = reservation.getStatus();

            students.add(new RemainKeyMoneyTM(studentId,name,status));
        }
        return students;
    }

    @Override
    public String generateNewID() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();

        Query query = session.createQuery("SELECT res_id FROM Reservation ORDER BY res_id DESC").setMaxResults(1);
        List list = query.list();
        session.close();

        String newUserId = "";

        String lastUserId = list.toString();
        String[] split = lastUserId.split("[A-z]");
        if(split.length==0){
            return "R001";
        }else{
            Integer integer = Integer.valueOf(split[2]);
            ++integer;

            if(!list.isEmpty()){
                if (integer>=100) {
                    newUserId = "R" + String.valueOf(integer) ;
                }else if(integer>=10){
                    newUserId = "R0" + String.valueOf(integer);
                }else{
                    newUserId = "R00" + String.valueOf(integer);
                }
                return newUserId;

            }else{
                return "R001";
            }

        }
    }

}

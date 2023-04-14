package lk.ijse.hibernate.dao.custom.impl;

import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String text) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, text);
//        List<Room> roomList=new ArrayList<>();
//        roomList.add(room);

        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public boolean deleteByPk(String pk) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room load = session.load(Room.class, pk);
        session.delete(load);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<Room> findAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM room";

        List<Room> roomList = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return roomList;
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

        String hql="SELECT room_type_id FROM room";

        List<String> roomIdList = session.createQuery(hql).list();

        transaction.commit();
        session.close();
        return roomIdList;
    }
}

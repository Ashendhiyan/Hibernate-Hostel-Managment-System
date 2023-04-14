package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.RoomDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.StudentDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.UserLoginDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory==null)? daoFactory=new DAOFactory() : daoFactory;
    }

    public<T extends SuperDAO> T getDAO(DAOTypes types){
            switch (types){
                case STUDENT:
                    return (T) new StudentDAOImpl();
                case ROOM:
                    return (T) new RoomDAOImpl();
                case USER:
                    return (T) new UserLoginDAOImpl();
                case RESERVATION:
                    return (T) new ReservationDAOImpl();
                default:
                    return null;
            }
    }
}

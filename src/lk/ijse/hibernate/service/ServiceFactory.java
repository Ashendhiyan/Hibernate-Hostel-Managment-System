package lk.ijse.hibernate.service;

import lk.ijse.hibernate.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hibernate.dao.custom.impl.UserLoginDAOImpl;
import lk.ijse.hibernate.service.custom.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return (serviceFactory==null)?serviceFactory=new ServiceFactory() : serviceFactory;
    }

    public <T extends SuperService> T getService(ServiceTypes types){
        switch (types){
            case STUDENT:
                return (T) new StudentServiceImpl();
            case ROOM:
                return (T) new RoomServiceImpl();
            case PERCHASE_RESERVE:
                return (T) new PurchaseReserveServiceImpl();
            case USER:
                return (T)new UserLoginServiceImpl();
            case RESERVATION:
                return (T) new ReservationServiceImpl();
            default:
                return null;
        }

    }
}

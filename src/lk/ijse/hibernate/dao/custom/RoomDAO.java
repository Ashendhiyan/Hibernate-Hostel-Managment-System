package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.Room;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomDAO extends CrudDAO<Room,String> {
    public List<String> loadIds() throws SQLException, ClassNotFoundException, IOException;
}

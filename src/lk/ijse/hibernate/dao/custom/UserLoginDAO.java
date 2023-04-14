package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.CrudDAO;
import lk.ijse.hibernate.entity.UserLogin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserLoginDAO extends CrudDAO<UserLogin,String> {
    List<String> searchName(String name) throws SQLException, IOException;
}

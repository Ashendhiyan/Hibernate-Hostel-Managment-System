package lk.ijse.hibernate.service.custom;

import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.dto.UserLoginDTO;
import lk.ijse.hibernate.service.SuperService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserLoginService extends SuperService {
    List<UserLoginDTO> findUser() throws SQLException, IOException,NullPointerException;
    boolean saveUser(UserLoginDTO dto) throws SQLException, IOException;
    boolean updateUser(UserLoginDTO dto) throws SQLException, IOException;
    boolean deleteUser(String id) throws SQLException, IOException;
    UserLoginDTO searchUser(String id) throws SQLException, IOException;

    List<String> searchName(String id) throws SQLException, IOException;
}

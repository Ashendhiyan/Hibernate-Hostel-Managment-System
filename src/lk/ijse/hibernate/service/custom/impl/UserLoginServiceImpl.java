package lk.ijse.hibernate.service.custom.impl;

import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.UserLoginDAO;
import lk.ijse.hibernate.dto.UserLoginDTO;
import lk.ijse.hibernate.entity.UserLogin;
import lk.ijse.hibernate.service.custom.UserLoginService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserLoginServiceImpl implements UserLoginService {

        UserLoginDAO userLoginDAO= DAOFactory.getInstance().getDAO(DAOTypes.USER);

    @Override
    public List<UserLoginDTO> findUser() throws SQLException, IOException, NullPointerException {
        List<UserLogin> all = userLoginDAO.findAll();
        ArrayList<UserLoginDTO> arrayList=new ArrayList<>();

        for (UserLogin u : all) {
            arrayList.add(new UserLoginDTO(
                    u.getUser_id(),
                    u.getUser_name(),
                    u.getPassword()
            ));
        }
        return arrayList;
    }

    @Override
    public boolean saveUser(UserLoginDTO dto) throws SQLException, IOException {
        return userLoginDAO.save(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean updateUser(UserLoginDTO dto) throws SQLException, IOException {
        return userLoginDAO.update(new UserLogin(
                dto.getUserID(),
                dto.getUserName(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, IOException {
        return userLoginDAO.deleteByPk(id);
    }

    @Override
    public UserLoginDTO searchUser(String id) throws SQLException, IOException {
        UserLogin search = userLoginDAO.search(id);

        return new UserLoginDTO(search.getUser_id(), search.getUser_name(), search.getPassword());
    }

    @Override
    public List<String> searchName(String id) throws SQLException, IOException {
        return userLoginDAO.searchName(id);
    }
}

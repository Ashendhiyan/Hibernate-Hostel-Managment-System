package lk.ijse.hibernate.dao;

import lk.ijse.hibernate.entity.SuperEntity;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO {
    public boolean save(T entity) throws SQLException, IOException;
    public boolean update(T entity) throws SQLException, IOException;
    T search(ID text) throws SQLException, IOException;
    public boolean deleteByPk(ID pk) throws SQLException, IOException;
    public List<T> findAll() throws SQLException, IOException;
    boolean findByPk(ID pk) throws SQLException, ClassNotFoundException, IOException;
}

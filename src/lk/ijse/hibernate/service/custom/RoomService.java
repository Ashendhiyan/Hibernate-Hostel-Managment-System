package lk.ijse.hibernate.service.custom;

import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.service.SuperService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoomService extends SuperService {
    List<RoomDTO> findRoom() throws SQLException, IOException,NullPointerException;
    boolean saveRoom(RoomDTO dto) throws SQLException, IOException;
    boolean updateRoom(RoomDTO dto) throws SQLException, IOException;
    boolean deleteRoom(String id) throws SQLException, IOException;
    RoomDTO searchRoom(String id) throws SQLException, IOException;
}

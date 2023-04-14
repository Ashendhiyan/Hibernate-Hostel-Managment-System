package lk.ijse.hibernate.service.custom.impl;

import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.service.custom.RoomService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {

    RoomDAO roomDAO= (RoomDAO) DAOFactory.getInstance().getDAO(DAOTypes.ROOM);

    @Override
    public List<RoomDTO> findRoom() throws SQLException, IOException, NullPointerException {
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO> roomDTOArrayList =new ArrayList<>();

        for (Room r : all) {
            roomDTOArrayList.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()
            ));
        }
        return roomDTOArrayList;
    }

    @Override
    public boolean saveRoom(RoomDTO dto) throws SQLException, IOException {
        return roomDAO.save(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()
        ));
    }

    @Override
    public boolean updateRoom(RoomDTO dto) throws SQLException, IOException {
        return roomDAO.update(new Room(
                dto.getRoomID(),
                dto.getRoomType(),
                dto.getKeyMoney(),
                dto.getRoomQty()
        ));
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, IOException {
        return roomDAO.deleteByPk(id);
    }

    @Override
    public RoomDTO searchRoom(String id) throws SQLException, IOException {
        Room search = roomDAO.search(id);

        return new RoomDTO(search.getRoom_type_id(), search.getType(), search.getKey_money(), search.getQty());
    }
}

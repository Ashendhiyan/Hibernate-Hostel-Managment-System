package lk.ijse.hibernate.service.custom.impl;

import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.ReservationDAO;
import lk.ijse.hibernate.dao.custom.RoomDAO;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dto.ReservationDTO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Reservation;
import lk.ijse.hibernate.entity.Room;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.service.custom.PurchaseReserveService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseReserveServiceImpl implements PurchaseReserveService {

        RoomDAO roomDAO= DAOFactory.getInstance().getDAO(DAOTypes.ROOM);
        StudentDAO studentDAO=DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);
        ReservationDAO reservationDAO=DAOFactory.getInstance().getDAO(DAOTypes.RESERVATION);


    @Override
    public boolean purchaseReserveSave(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.save(new Reservation(
                dto.getRes_id(),
                dto.getDate(),
                dto.getKey_money(),
                dto.getQty(),
                dto.getStatus(),
                dto.getRoomID(),
                dto.getStudentID()
        ));
    }

    @Override
    public boolean UpdateReservation(ReservationDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.update(new Reservation(
                dto.getRes_id(),
                dto.getDate(),
                dto.getKey_money(),
                dto.getQty(),
                dto.getStatus(),
                dto.getRoomID(),
                dto.getStudentID()
        ));
    }

    @Override
    public boolean deleteReservation(String id) throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.deleteByPk(id);
    }

    @Override
    public RoomDTO searchRooms(String id) throws SQLException, ClassNotFoundException, IOException {
        Room room = roomDAO.search(id);


        return new RoomDTO(room.getRoom_type_id(),room.getType(),room.getKey_money(),room.getQty());
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException, IOException {
        Student s=studentDAO.search(id);

        return new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContact_no(),s.getDob(),s.getGender());
    }

    @Override
    public ReservationDTO searchReservation(String id) throws SQLException, ClassNotFoundException, IOException {
        Reservation reservation=reservationDAO.search(id);

        return new ReservationDTO(reservation.getRes_id(), reservation.getDate(),reservation.getStudent(),
                reservation.getRoom(), reservation.getKey_money(), reservation.getStatus(), reservation.getQty());
    }

    @Override
    public boolean checkRoomIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException {
        return roomDAO.findByPk(id);
    }

    @Override
    public boolean checkStudentIsAvailable(String id) throws SQLException, ClassNotFoundException, IOException {
        return studentDAO.findByPk(id);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException, IOException {
        return reservationDAO.generateNewID();
    }

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {
        List<Student> all = studentDAO.findAll();
        ArrayList<StudentDTO> allStudents = new ArrayList<>();

        for(Student s: all){
            allStudents.add(new StudentDTO(
                    s.getStudent_id(),
                    s.getName(),
                    s.getAddress(),
                    s.getContact_no(),
                    s.getDob(),
                    s.getGender()));
        }

        return allStudents;
    }

    @Override
    public List<RoomDTO> getAllRooms() throws Exception {
        List<Room> all = roomDAO.findAll();
        ArrayList<RoomDTO> allRooms = new ArrayList<>();

        for(Room r: all){
            allRooms.add(new RoomDTO(
                    r.getRoom_type_id(),
                    r.getType(),
                    r.getKey_money(),
                    r.getQty()
            ));
        }

        return allRooms;
    }

    @Override
    public List<ReservationDTO> getAllReservation() throws Exception {
        List<Reservation> all = reservationDAO.findAll();
        ArrayList<ReservationDTO> allReservation = new ArrayList<>();

        for(Reservation r: all){
            allReservation.add(new ReservationDTO(
                    r.getRes_id(),
                    r.getDate(),
                    r.getStudent(),
                    r.getRoom(),
                    r.getKey_money(),
                    r.getStatus(),
                    r.getQty()
            ));
        }

        return allReservation;
    }

    @Override
    public List getStudentIds() throws IOException, SQLException, ClassNotFoundException {
        return studentDAO.loadIds();
    }

    @Override
    public List getRoomIds() throws IOException, SQLException, ClassNotFoundException {
        return roomDAO.loadIds();
    }
}

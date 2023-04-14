package lk.ijse.hibernate.service.custom;

import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.service.SuperService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentService extends SuperService {
    List<StudentDTO> findStudent() throws SQLException, IOException,NullPointerException;
     boolean saveStudent(StudentDTO dto) throws SQLException, IOException;
     boolean updateStudent(StudentDTO dto) throws SQLException, IOException;
     boolean deleteStudent(String id) throws SQLException, IOException;
     StudentDTO searchStudent(String id) throws SQLException, IOException;
}

package lk.ijse.hibernate.service.custom.impl;

import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.DAOTypes;
import lk.ijse.hibernate.dao.custom.StudentDAO;
import lk.ijse.hibernate.dto.StudentDTO;
import lk.ijse.hibernate.entity.Student;
import lk.ijse.hibernate.service.custom.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDAO studentDAO= (StudentDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public List<StudentDTO> findStudent() throws SQLException, IOException {
        List<Student> all = studentDAO.findAll();
        ArrayList<StudentDTO> allStudents=new ArrayList<>();

        for (Student s : all) {
            allStudents.add(new StudentDTO(
                    s.getStudent_id(),
                    s.getName(),
                    s.getAddress(),
                    s.getContact_no(),
                    s.getDob(),
                    s.getGender()
            ));
        }
        return allStudents;
    }

    @Override
    public boolean saveStudent(StudentDTO dto) throws SQLException, IOException {
        return studentDAO.save(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws SQLException, IOException {
        return studentDAO.update(new Student(
                dto.getStudentId(),
                dto.getName(),
                dto.getAddress(),
                dto.getTelNo(),
                dto.getDob(),
                dto.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws SQLException, IOException {
        return studentDAO.deleteByPk(id);
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, IOException {
        Student search = studentDAO.search(id);

        return new StudentDTO(search.getStudent_id(), search.getName(), search.getAddress(), search.getContact_no(),search.getDob(), search.getGender());
    }
}

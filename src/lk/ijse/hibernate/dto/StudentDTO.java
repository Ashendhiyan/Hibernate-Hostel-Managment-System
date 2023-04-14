package lk.ijse.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {

    private String studentId;
    private String name;
    private String address;
    private String telNo;
    private LocalDate dob;
    private  String gender;


}

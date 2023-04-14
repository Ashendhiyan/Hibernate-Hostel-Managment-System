package lk.ijse.hibernate.view.dtm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM {
    private String studentId;
    private String name;
    private String address;
    private String telNo;
    private LocalDate dob;
    private String gender;
}

package lk.ijse.hibernate.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "student")

public class Student implements SuperEntity {
@Id
    private String student_id;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String address;
    @Column(nullable = false)
    private String contact_no;
    @Column(columnDefinition = "DATE",nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String gender;

    @OneToMany(mappedBy = "student")
    @Cascade(CascadeType.ALL)
    private
    List<Reservation> reservations=new ArrayList<>();

    public Student(String student_id, String name, String address, String contact_no, LocalDate dob, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        this.contact_no = contact_no;
        this.dob = dob;
        this.setGender(gender);
    }


}

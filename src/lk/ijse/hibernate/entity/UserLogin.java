package lk.ijse.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user_login")
public class UserLogin implements SuperEntity{
@Id
    private String user_id;
    @Column(nullable = false)
    private String user_name;
    @Column(nullable = false)
    private String password;
}

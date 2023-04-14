package lk.ijse.hibernate.view.dtm;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReservationTM {
    private String reserveID;
    private String roomID;
    private String roomType;
    private int studentQty;
    private double keyMoney;
    private String status;



}

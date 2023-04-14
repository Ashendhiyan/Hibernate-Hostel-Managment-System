package lk.ijse.hibernate.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDTO {
    private String roomID;
    private String roomType;
    private String keyMoney;
    private int roomQty;
}

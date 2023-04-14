package lk.ijse.hibernate.view.dtm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomTm {
    private String roomID;
    private String roomType;
    private String keyMoney;
    private int roomQty;

}

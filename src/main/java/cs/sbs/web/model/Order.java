package cs.sbs.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private int id;
    private String customer;
    private String food;
    private int quantity;
    private Double totalPrice;
    private LocalDateTime createTime;

}

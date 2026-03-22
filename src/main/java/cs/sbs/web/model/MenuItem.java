package cs.sbs.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuItem {

    private String footname;
    private double price;

//    public MenuItem(String name, int price) {
//        this.name = name;
//        this.price = price;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public int getPrice() {
//        return price;
//    }

}
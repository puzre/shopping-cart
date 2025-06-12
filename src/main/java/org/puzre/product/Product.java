package org.puzre.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements IProduct {

    private long id;
    private String name;
    private int price;


    @Override
    public void describe() {
        System.out.println(id + " " + name + " " + "$" + price);
    }

}

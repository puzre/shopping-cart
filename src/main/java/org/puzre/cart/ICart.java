package org.puzre.cart;

import org.puzre.product.Product;

public interface ICart {

    void addProduct(Product product);

    void removeProduct(long productId);

    int getProductQuantity();

    int getTotalAmount();

    void clear();

    void describe();

}

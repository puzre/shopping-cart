package org.puzre;

import org.puzre.cart.Cart;
import org.puzre.payment.GooglePayProcessorPayment;
import org.puzre.payment.IPaymentProcessor;
import org.puzre.product.Product;

public class Main {
    public static void main(String[] args) {

        Cart cart = new Cart();

        Product p1 = new Product(1, "Chips", 50);
        Product p2 = new Product(2, "Chocolate", 76);

        cart.addProduct(p1);
        cart.addProduct(p2);

        cart.describe();

        IPaymentProcessor googlePayProcessor = new GooglePayProcessorPayment();

        cart.pay(googlePayProcessor);

    }
}
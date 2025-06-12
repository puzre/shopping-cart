package cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.puzre.cart.Cart;
import org.puzre.exception.PaymentException;
import org.puzre.exception.ProductNotFound;
import org.puzre.payment.GooglePayProcessorPayment;
import org.puzre.payment.IPaymentProcessor;
import org.puzre.payment.PaypalPaymentProcessor;
import org.puzre.product.Product;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    Cart cart;
    Product p1, p2;

    @BeforeEach
    public void setUp() {

        cart = new Cart();
        p1 = new Product(1, "Chips", 50);
        p2 = new Product(2, "Chocolate", 76);

    }

    @Test
    @DisplayName(value = "Testing add products to the cart")
    public void addProductTest() {

        cart.addProduct(p1);
        cart.addProduct(p2);

        int expected = 2;
        int actual = cart.getProductQuantity();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName(value = "Testing remove products from the cart")
    public void removeProduct() {

        cart.addProduct(p1);
        cart.addProduct(p2);

        long productId = p1.getId();

        cart.removeProduct(productId);

        int expected = 1;
        int actual = cart.getProductQuantity();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName(value = "Testing invalid id product in remove products from cart")
    public void removeProductNotFoundExceptionTest() {

        cart.addProduct(p1);
        cart.addProduct(p2);

        int wrongProductId = 3;

        assertThrows(ProductNotFound.class, () -> cart.removeProduct(wrongProductId));

    }

    @Test
    @DisplayName(value = "Testing get total amount of the cart")
    public void totalAmountTest() {

        cart.addProduct(p1);
        cart.addProduct(p2);

        int expected = 126;
        int actual = cart.getTotalAmount();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName(value = "Testing clear all products of the cart")
    public void clearCartTest() {

        cart.addProduct(p1);
        cart.addProduct(p2);

        cart.clear();

        int expected = 0;
        int actual = cart.getProductQuantity();

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName(value = "Testing create payment using Paypal processor")
    public void paypalPaymentTest() {

        IPaymentProcessor paypalProcessor = new PaypalPaymentProcessor();

        assertThrows(PaymentException.class, () -> cart.pay(paypalProcessor));

    }

    @Test
    @DisplayName(value = "Testing create payment using Google Pay processor")
    public void googlePayPaymentTest() {

        cart.addProduct(p1);
        cart.addProduct(p2);

        IPaymentProcessor googlePayProcessor = new GooglePayProcessorPayment();

        assertDoesNotThrow(() -> cart.pay(googlePayProcessor));

    }

}

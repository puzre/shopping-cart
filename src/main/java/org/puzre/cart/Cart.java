package org.puzre.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.puzre.exception.ProductNotFound;
import org.puzre.payment.IPaymentProcessor;
import org.puzre.payment.IPayable;
import org.puzre.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements ICart, IPayable {

    private List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public void removeProduct(long productId) {

        int productIndex = IntStream.range(0, this.products.size())
                .filter(index -> this.products.get(index).getId() == productId)
                .findFirst()
                .orElse(-1);

        if (productIndex < 0)
            throw new ProductNotFound("Product wit the id == " + productId + "does not exist");

        this.products.remove(productIndex);

    }

    @Override
    public int getProductQuantity() {
        return this.products.size();
    }

    @Override
    public int getTotalAmount() {
        return this.products.stream()
                .map(Product::getPrice)
                .reduce(0, Integer::sum);
    }

    @Override
    public void clear() {
        this.products.clear();
    }

    @Override
    public void pay(IPaymentProcessor paymentProcessor) {
        paymentProcessor.createPayment(this.getTotalAmount());
    }

    @Override
    public void describe() {
        System.out.println("Cart description:");
        if (this.getProductQuantity() > 0)
            this.products.stream().forEach(Product::describe);
        else
            System.out.println("There is not any product in your cart.");
        System.out.println("Total amount: $" + this.getTotalAmount());
    }

}

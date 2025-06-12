package org.puzre.payment;

import org.puzre.exception.PaymentException;

public class PaypalPaymentProcessor implements IPaymentProcessor {

    @Override
    public void createPayment(int total) {
        throw new PaymentException("Something went wrong");
    }

}

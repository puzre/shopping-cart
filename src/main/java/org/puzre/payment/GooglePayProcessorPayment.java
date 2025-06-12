package org.puzre.payment;

public class GooglePayProcessorPayment implements IPaymentProcessor {

    @Override
    public void createPayment(int total) {
        System.out.println("$" + total + " paid using Google Pay");
    }

}

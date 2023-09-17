package problema2.service;

public class PaypalService implements OnlinePaymentService{

    //double PAYMENT_FEE_PERCENTAGE = 0.02;
    //double MONTHLY_INTEREST_PERCENTAGE = 0.01;

    @Override
    public double paymentFee(Double amount) {
        return amount * 0.02;
    }

    @Override
    public double interest(Double amount, Integer mounths) {
        return amount * 0.01 * mounths;
    }
}

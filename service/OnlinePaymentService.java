package problema2.service;

public interface OnlinePaymentService {
    double paymentFee(Double amount);
    double interest(Double amount, Integer mounths);
}

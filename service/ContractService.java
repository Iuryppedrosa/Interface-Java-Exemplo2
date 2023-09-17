package problema2.service;

import problema2.entities.Contract;
import problema2.entities.Installment;

import java.time.LocalDate;

public class ContractService {

    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.paymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {

        double basicQuota = contract.getTotalValue() / months;
        
        for (int i = 1; i <= months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i);
            double interestAmount = paymentService.interest(basicQuota, i);
            double fee = paymentService.paymentFee(basicQuota + interestAmount);
            //double totalAmount = basicQuota + interestAmount + paymentService.paymentFee(basicQuota);
            double quota = basicQuota + interestAmount + fee;
            contract.addInstallment(new Installment(dueDate, quota, contract));
        }
    }

}

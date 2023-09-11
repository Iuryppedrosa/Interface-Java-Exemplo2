package problema2.service;

import problema2.entities.Contract;
import problema2.entities.Installment;

public class ContractService {

    private OnlinePaymentService paymentService;

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.paymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months) {
        for (int i = 1; i <= months; i++) {
            double installmentAmount = contract.getTotalValue() / months; // descobre o valor mensal do contrato, mensalidade
            double interestAmount = paymentService.interest(installmentAmount, i);
            double totalAmount = installmentAmount + interestAmount + paymentService.paymentFee(installmentAmount);
            contract.addInstallment(new Installment(contract.getDate().plusMonths(i), totalAmount, contract));
        }
    }

}

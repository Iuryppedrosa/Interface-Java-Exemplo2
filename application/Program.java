package problema2.application;

import problema2.entities.Contract;
import problema2.entities.Installment;
import problema2.service.ContractService;
import problema2.service.OnlinePaymentService;
import problema2.service.PaypalService;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Entre os dados do contrato:");

        System.out.print("Número: ");
        int numeroContrato = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data (dd/MM/yyyy): ");
        String dataString = scanner.nextLine();
        LocalDate dataContrato = LocalDate.parse(dataString, fmt);

        System.out.print("Valor do contrato: ");
        double valorContrato = scanner.nextDouble();

        System.out.print("Entre com o número de parcelas: ");
        int numeroParcelas = scanner.nextInt();

        Contract contract = new Contract(numeroContrato, dataContrato, valorContrato);

        OnlinePaymentService paymentService = new PaypalService();
        ContractService contractService = new ContractService(paymentService);

        contractService.processContract(contract, numeroParcelas);

        System.out.println("\nDados do contrato:");
        System.out.println("Número: " + contract.getNumber());
        System.out.println("Data: " + contract.getDate());
        System.out.println("Valor do contrato: " + contract.getTotalValue());
        System.out.println("Número de parcelas: " + numeroParcelas);

        System.out.println("\nParcelas:");
        for (Installment installment : contract.getInstallments()) {
            System.out.print(installment.getDueDate() + " - " + new DecimalFormat("#.00").format(installment.getAmount()));
        }

        scanner.close();
    }
}



package com.ironhack.Midtem.Project.service.interfaces;

import com.ironhack.Midtem.Project.Repository.*;
import com.ironhack.Midtem.Project.Utils.Money;
import com.ironhack.Midtem.Project.enums.Status;
import com.ironhack.Midtem.Project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public interface ITransactionService {

    //================================================
    //Transaction management
    //================================================

    //The main class in the service, here we call the method with the logic in transactions account to account
    public void makeATransaction(String senderAccountId, String beneficiaryAccountId, Money transactionBalance);

    //The main class in the service, here we call the method with the logic in transactions account to thirdParty
    public void makeATransaction(String senderAccountId, Money transactionBalance);



    //All those methods are implemented in makeATransaction
    public Transaction createTransaction(Optional<Account> sender, Optional<Account> beneficiary, Money amount);
    public Transaction createTransaction(Optional<Account> sender, Money amount);
    public void checkCreditLimit (Optional<Account> sender, Money transactionBalance);
    public BigDecimal getMinimumBalance (Optional<Account> sender);
    public void checkCorrectTransactionTime(Optional<Account> sender);
    public void checkActive(Optional<Account> sender, Optional<Account> beneficiary);
    public void checkActive(Optional<Account> sender);
    public void checkEnoughBalance(Optional<Account> sender, Money transactionBalance);
    public void addBeneficiaryAmount(Optional<Account> beneficiary, Money transactionBalance);
    public void subtractSenderAmount(Optional<Account> sender, Money transactionBalance);
    public void checkHighestDailyTotal (Account sender, Money transactionBalance);

}

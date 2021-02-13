package com.ironhack.Midtem.Project.model;

import com.ironhack.Midtem.Project.Utils.Money;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Account payer;
    @ManyToOne
    @JoinColumn(name = "beneficiary_id")
    private Account beneficiary;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "amount", column = @Column(name = "transaction_amount")),
            @AttributeOverride(name = "currency", column = @Column (name = "transaction_currency"))})
    private Money amount;
    private LocalDateTime transactionTime;

    public Transaction() {
    }

    public Transaction(Account payer, Money amount) {
        this.payer = payer;
        this.amount = amount;
        setTransactionTime();
    }

    public Transaction(Account payer, Account beneficiary, Money amount) {
        this.payer = payer;
        this.beneficiary = beneficiary;
        this.amount = amount;
        setTransactionTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getPayer() {
        return payer;
    }

    public void setPayer(Account payer) {
        this.payer = payer;
    }

    public Account getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Account beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime() {
        this.transactionTime = LocalDateTime.now();
    }


    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}

package com.star.rewards.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;


public class CustomerRewards {

    @NotNull
    private Integer customerId;

    @NotNull
    private String customerName;

    @Positive
    private Long transactionId;

    @NotNull
    private Date transactionDate;

    @PositiveOrZero
    private Integer transactionAmt;

    @PositiveOrZero
    private Integer rewardPoints;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionAmt() {
        return transactionAmt;
    }

    public void setTransactionAmt(Integer transactionAmt) {
        this.transactionAmt = transactionAmt;
    }

    public Integer getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(Integer rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void calculateRewardPoints(){
        if(this.transactionAmt <=50){
            this.rewardPoints = 0;
        }else if(this.transactionAmt <=100){
            this.rewardPoints = (this.transactionAmt - 50)*1;
        }else{
           this.rewardPoints = (50*1) + (this.transactionAmt-100)*2;
        }
    }
}

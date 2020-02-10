package com.star.rewards.model;

import java.util.HashMap;

public class CustomerMonthlyRewards {

    private Integer customerId;
    private String custName;
    private HashMap<String, Integer> monthlySummary;
    private Integer totalRewardPoints;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public HashMap<String, Integer> getMonthlySummary() {
        return monthlySummary;
    }

    public void setMonthlySummary(HashMap<String, Integer> monthlySummary) {
        this.monthlySummary = monthlySummary;
    }

    public Integer getTotalRewardPoints() {
        return totalRewardPoints;
    }

    public void setTotalRewardPoints(Integer totalRewardPoints) {
        this.totalRewardPoints = totalRewardPoints;
    }
}

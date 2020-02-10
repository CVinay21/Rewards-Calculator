package com.star.rewards.business;

import com.star.rewards.model.CustomerMonthlyRewards;
import com.star.rewards.model.CustomerRewards;
import com.star.rewards.model.CustomerRewardsList;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RewardsBusService {

    public List<CustomerMonthlyRewards> summarizeCustTransactions(CustomerRewardsList custRewardsList){
        List<CustomerMonthlyRewards> customerMonthlyRewardsList = null;
        CustomerMonthlyRewards custMonthlyRewards = null;
        if(custRewardsList.getCustRewardsList()!=null && !custRewardsList.getCustRewardsList().isEmpty()){

            for(CustomerRewards custRewards:custRewardsList.getCustRewardsList()){
                if(customerMonthlyRewardsList==null){
                    customerMonthlyRewardsList = new ArrayList<CustomerMonthlyRewards>();
                    customerMonthlyRewardsList.add(mapToCustomerMonthlyRewards(custRewards));
                }else if(!custExistsInList(custRewards, customerMonthlyRewardsList)){
                    customerMonthlyRewardsList.add(mapToCustomerMonthlyRewards(custRewards));
                }else{
                    custMonthlyRewards = getCustomerFromList(custRewards, customerMonthlyRewardsList);
                    updateMonthlySummary(custMonthlyRewards, custRewards);
                }
            }
        }

        return customerMonthlyRewardsList;
    }

    private CustomerMonthlyRewards mapToCustomerMonthlyRewards(CustomerRewards custRewards) {
        CustomerMonthlyRewards custMonthlyRewards = new CustomerMonthlyRewards();
        custMonthlyRewards.setCustName(custRewards.getCustomerName());
        custMonthlyRewards.setCustomerId(custRewards.getCustomerId());
        custMonthlyRewards.setTotalRewardPoints(custRewards.getRewardPoints());

        String monthYear = getMonthYear(custRewards);

        HashMap<String, Integer> monthlySummary = new HashMap<String, Integer>();
        monthlySummary.put(monthYear, custRewards.getRewardPoints());

        custMonthlyRewards.setMonthlySummary(monthlySummary);

        return custMonthlyRewards;
    }

    private boolean custExistsInList(CustomerRewards custRewards, List<CustomerMonthlyRewards> customerMonthlyRewardsList) {
        for(CustomerMonthlyRewards custMonthlyRewards:customerMonthlyRewardsList){
            if(custMonthlyRewards.getCustomerId().equals(custRewards.getCustomerId())){
                return true;
            }
        }
        return false;
    }

    private CustomerMonthlyRewards getCustomerFromList(CustomerRewards custRewards, List<CustomerMonthlyRewards> customerMonthlyRewardsList) {
        for(CustomerMonthlyRewards custMonthlyRewards:customerMonthlyRewardsList){
            if(custMonthlyRewards.getCustomerId().equals(custRewards.getCustomerId())){
                return custMonthlyRewards;
            }
        }
        return null;
    }

    private void updateMonthlySummary(CustomerMonthlyRewards custMonthlyRewards, CustomerRewards custRewards) {
        String monthYear = getMonthYear(custRewards);
        Integer monthlySummaryPoints = null;
        Boolean monthlySummaryFound = false;
        for (Map.Entry<String, Integer> entry : custMonthlyRewards.getMonthlySummary().entrySet()) {
            if(monthYear.equals(entry.getKey())){
                monthlySummaryPoints = entry.getValue() + custRewards.getRewardPoints();
                custMonthlyRewards.getMonthlySummary().replace(entry.getKey(), entry.getValue(), monthlySummaryPoints);
                monthlySummaryFound = true;
            }
        }

        if(!monthlySummaryFound){
            custMonthlyRewards.getMonthlySummary().put(monthYear, custRewards.getRewardPoints());
        }

        custMonthlyRewards.setTotalRewardPoints(custMonthlyRewards.getTotalRewardPoints() + custRewards.getRewardPoints());

    }

    private String getMonthYear(CustomerRewards custRewards){
        Calendar cal = Calendar.getInstance();
        cal.setTime(custRewards.getTransactionDate());
        return(new SimpleDateFormat("MMM").format(cal.getTime()) + "_" + new SimpleDateFormat("YYYY").format(cal.getTime()));
    }
}

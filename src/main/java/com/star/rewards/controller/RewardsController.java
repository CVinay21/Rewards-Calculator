package com.star.rewards.controller;

import com.star.rewards.business.RewardsBusService;
import com.star.rewards.model.CustomerMonthlyRewards;
import com.star.rewards.model.CustomerRewards;
import com.star.rewards.model.CustomerRewardsList;
import com.star.rewards.model.CustomerRewardsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * This Rest controller will receive any requests coming to /rewards url
 */
@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    RewardsBusService rewardsBusService;

    //This method is used to handle single customer transaction update and calculates rewards earned from it
    @RequestMapping(path = "/postCustomerTransaction", method = RequestMethod.POST)
    public CustomerRewards postCustomerTransaction(@Valid @RequestBody CustomerRewards custRewards){
        custRewards.calculateRewardPoints();
        return custRewards;
    }

    //This method is used to handle a list of customer transactions, calculates rewards earned for each
    // and summarizes the rewards per customer
    @RequestMapping(path = "/postCustomerTransactionList", method = RequestMethod.POST)
    public List<CustomerMonthlyRewards> postCustomerTransaction(@Valid @RequestBody CustomerRewardsList custRewardsList){
        custRewardsList.getCustRewardsList().forEach(custRewards->
            custRewards.calculateRewardPoints()
        );

        return rewardsBusService.summarizeCustTransactions(custRewardsList);
    }
}

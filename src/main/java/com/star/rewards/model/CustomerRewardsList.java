package com.star.rewards.model;

import com.star.rewards.model.CustomerRewards;

import javax.validation.constraints.NotNull;
import java.util.List;

public class CustomerRewardsList {

    @NotNull
    private List<com.star.rewards.model.CustomerRewards> custRewardsList;

    public List<CustomerRewards> getCustRewardsList() {
        return custRewardsList;
    }

    public void setCustRewardsList(List<CustomerRewards> custRewardsList) {
        this.custRewardsList = custRewardsList;
    }
}

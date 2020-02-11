package com.star.rewards;

import com.star.rewards.model.CustomerMonthlyRewards;
import com.star.rewards.model.CustomerRewards;
import com.star.rewards.model.CustomerRewardsList;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RewardsCalculatorControllerTest extends AbstractTest {
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void postCustomerTransaction() throws Exception {
        String uri = "/rewards/postCustomerTransaction";
        CustomerRewards custRewards = new CustomerRewards();
        custRewards.setCustomerId(345);
        custRewards.setCustomerName("Test Customer");
        custRewards.setTransactionId(10201304l);
        custRewards.setTransactionAmt(120);

        Calendar cal = Calendar.getInstance();
        cal.set(2020,02,10);
        custRewards.setTransactionDate(cal.getTime());

        String inputJson = super.mapToJson(custRewards);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        CustomerRewards custReward1 = super.mapFromJson(content, CustomerRewards.class);
        assertEquals(Integer.valueOf(90), custReward1.getRewardPoints());
    }

    @Test
    public void postCustomerTransactionList() throws Exception {
        String uri = "/rewards/postCustomerTransactionList";
        CustomerRewardsList customerRewardsList = new CustomerRewardsList();
        List<CustomerRewards> custRewardsList = new ArrayList<CustomerRewards>();

        Calendar cal = Calendar.getInstance();

        CustomerRewards custRewards = new CustomerRewards();
        custRewards.setCustomerId(345);
        custRewards.setCustomerName("Test Customer");
        custRewards.setTransactionId(10201304l);
        custRewards.setTransactionAmt(120);
        cal.set(2020,02,10);
        custRewards.setTransactionDate(cal.getTime());

        CustomerRewards custRewards2 = new CustomerRewards();
        custRewards2.setCustomerId(345);
        custRewards2.setCustomerName("Test Customer");
        custRewards2.setTransactionId(10201305l);
        custRewards2.setTransactionAmt(220);
        cal.set(2020,02,02);
        custRewards2.setTransactionDate(cal.getTime());

        CustomerRewards custRewards3 = new CustomerRewards();
        custRewards3.setCustomerId(225);
        custRewards3.setCustomerName("Test Customer2");
        custRewards3.setTransactionId(10201205l);
        custRewards3.setTransactionAmt(320);
        cal.set(2020,01,02);
        custRewards3.setTransactionDate(cal.getTime());

        custRewardsList.add(custRewards);
        custRewardsList.add(custRewards2);
        custRewardsList.add(custRewards3);
        customerRewardsList.setCustRewardsList(custRewardsList);


        String inputJson = super.mapToJson(customerRewardsList);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
    }
}

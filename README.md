# Rewards-Calculator
This program is a Spring Boot MVC application to calculate customer rewards for the "Star" Retailer.

I have used 3 model classes as follows - 
1) CustomerRewards models the input for each transaction that the program will be processing
2) CustomerRewardsList models the list of Customer Rewards that the program will be processing for the monthly summary for each customer
3) CustomerMonthlyRewards models the summarized monthly rewards for each customer over the period of the transaction list

RewardsController is the Spring MVC Rest Controller class that has the different request handlers.

I have also written a couple of Junit tests in the RewardsCalculatorControllerTest class to test the two method handlers from the controller class.

Apart from the Test class, I also tested this program using Postman for the two URLs - 

1) POST operation on URL - http://localhost:8080/rewards/postCustomerTransaction
Sample Json input - 
{
    "customerId": "123",
    "customerName": "Test Customer",
    "transactionId": 523423,
    "transactionDate": "2020-02-09",
    "transactionAmt": 220,
    "rewardPoints":0
}
Json output - 
{
    "customerId": 123,
    "customerName": "Test Customer",
    "transactionId": 523423,
    "transactionDate": "2020-02-09T00:00:00.000+0000",
    "transactionAmt": 220,
    "rewardPoints": 290
}

2) POST operation on URL - http://localhost:8080/rewards/postCustomerTransactionList
Sample Json input - 
{
	"custRewardsList":[{
		"customerId": "123",
		"customerName": "Test Customer",
		"transactionId": 523423,
		"transactionDate": "2020-02-09",
		"transactionAmt": 220,
		"rewardPoints":0
	},{
		"customerId": "123",
		"customerName": "Test Customer",
		"transactionId": 523424,
		"transactionDate": "2020-02-10",
		"transactionAmt": 120,
		"rewardPoints":0
	},{
		"customerId": "123",
		"customerName": "Test Customer",
		"transactionId": 523524,
		"transactionDate": "2020-01-10",
		"transactionAmt": 80,
		"rewardPoints":0
	},{
		"customerId": "321",
		"customerName": "Test Customer2",
		"transactionId": 523522,
		"transactionDate": "2020-01-04",
		"transactionAmt": 350,
		"rewardPoints":0
	},{
		"customerId": "321",
		"customerName": "Test Customer2",
		"transactionId": 523525,
		"transactionDate": "2020-01-25",
		"transactionAmt": 125,
		"rewardPoints":0
	},{
		"customerId": "326",
		"customerName": "Test Customer3",
		"transactionId": 523528,
		"transactionDate": "2020-01-15",
		"transactionAmt": 430,
		"rewardPoints":0
	},{
		"customerId": "326",
		"customerName": "Test Customer3",
		"transactionId": 523529,
		"transactionDate": "2020-01-25",
		"transactionAmt": 125,
		"rewardPoints":0
	}
	
	
	]
}

Json output - 

[
    {
        "customerId": 123,
        "custName": "Test Customer",
        "monthlySummary": {
            "Feb_2020": 380,
            "Jan_2020": 30
        },
        "totalRewardPoints": 410
    },
    {
        "customerId": 321,
        "custName": "Test Customer2",
        "monthlySummary": {
            "Jan_2020": 650
        },
        "totalRewardPoints": 650
    },
    {
        "customerId": 326,
        "custName": "Test Customer3",
        "monthlySummary": {
            "Jan_2020": 810
        },
        "totalRewardPoints": 810
    }
]
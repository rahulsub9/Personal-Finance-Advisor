import java.util.*;
public class FinancialAdvisor
{
    //Constants for financial thresholds
    //Used 15.0 for savings rate percent for more achievable target
    private static final double ADEQUATE_SAVINGS_RATE_PERCENT = 15.0;
    private static final double HIGH_DEBT_RATIO_PERCENT = 36.0;
    private static final int ADEQUATE_EMERGENCY_FUND_MONTHS = 3;
    private static final int GOOD_EMERGENCY_FUND_MONTHS = 6;

    //Generates personalized savings advice for the user
    public ArrayList<String> generateSavingsAdvice(User user)
    {
        //Initialize all of the needed information from the user object
        ArrayList<String> advice = new ArrayList<>();
        double savingsRate = user.calculateSavingsRate();
        double debtToIncomeRatio = user.calculateDebtToIncomeRatio();
        int emergencyFundMonths = user.calculateEmergencyFundMonths();
        double disposableIncome = user.calculateDisposableIncome();

        //Add advice to the ArrayList with formatted strings with certain float levels for each percentage or number
        advice.add(String.format("Your current monthly disposable income is $%.2f.", disposableIncome);
        //advice on savings rate
        //if savings rate is less than what is needed
        if (savingsRate < ADEQUATE_SAVINGS_RATE_PERCENT)
        {
            advice.add(String.format("Your savings rate is %.2f%%. Consider increasing it. A good target is at least %.2f%% of your income.", savingsRate, ADEQUATE_SAVINGS_RATE_PERCENT));
            advice.add("Look for ways to reduce non-essential expenses or increase income to boost your savings.");
        }
        //if savings rate is adequate
        else
        {
            advice.add(String.format("Congratulations! Your savings rate of %.2f%% is good. Keep it up!", savingsRate));
        }

        //advice on emergency fund
        //if current emergency fund is less than the adequate amount
        if (emergencyFundMonths < ADEQUATE_EMERGENCY_FUND_MONTHS)
        {
            advice.add(String.format("Your emergency fund covers %d months of expenses. Aim for at least %d-%d months.", emergencyFundMonths, ADEQUATE_EMERGENCY_FUND_MONTHS, GOOD_EMERGENCY_FUND_MONTHS));
            advice.add("Prioritize building your emergency fund to cover unexpected events.");
            //Check if the user's total expenses are greater than 0, if so, then give the user how much more money they need to have the adequate amount of emergency months
            if (user.calculateTotalExpenses() > 0)
            {
                advice.add(String.format("You need $%.2f for a %d-month emergency fund.", user.calculateTotalExpenses() * ADEQUATE_EMERGENCY_FUND_MONTHS, ADEQUATE_EMERGENCY_FUND_MONTHS));
            }
        }
        //if the user has adequate emergency fund months but not good months
        else if (emergencyFundMonths < GOOD_EMERGENCY_FUND_MONTHS)
        {
            advice.add(String.format("Your emergency fund covers %d months. This is a good start! Consider increasing it to %d months for greater security.", emergencyFundMonths, GOOD_EMERGENCY_FUND_MONTHS));
        }
        //if the user has good emergency fund months
        else
        {
            advice.add(String.format("Excellent! Your emergency fund covers %d months of expenses, which is a strong safety net.", emergencyFundMonths));
        }

        //Advice on debt
        //Check if the user's debt to income ratio is higher than a high ratio, if so give them advice to pay their debt
        if (debtToIncomeRatio > HIGH_DEBT_RATIO_PERCENT)
        {
            advice.add(String.format("Your debt-to-income ratio is %.2f%%, which is considered high. Focus on strategies to pay down debt.", debtToIncomeRatio));
            advice.add("Consider the snowball or avalanche method for debt repayment.");
        }
        //Check if the user has any debt
        else if (debtToIncomeRatio > 0)
        {
            advice.add(String.format("Your debt-to-income ratio is %.2f%%. Continue managing your debt effectively.", debtToIncomeRatio));
        }
        //If the user has no debt or a low ratio then applaud them
        else
        {
            advice.add("You have a low or no debt-to-income ratio, which is great for financial health!");
        }

        //General advice based on financial goal
        String goal = user.getFinancialGoal();
        //if the user's goal is to build an emergency fund and they don't already have good emergency fund months advise them to add some money to their savings
        if (goal.equals("Build emergency fund") && emergencyFundMonths < GOOD_EMERGENCY_FUND_MONTHS)
        {
            advice.add("Since your goal is to build an emergency fund, allocate a significant portion of your disposable income towards it.");
        }
        //If the user's goal is to pay off debt and they have a debt greater than 0 then advise them to create a repayment plan
        else if (goal.equals("Pay off debt") && user.getTotalDebt() > 0)
        {
            advice.add("Focus on your 'Pay off debt' goal by creating a repayment plan. Extra payments can save interest.");
        }
        //If the user's goal is to save for a large purchase give them advice to identify the goal and make steps to get there
        else if (goal.equals("Save for a large purchase"))
        {
            advice.add("For your 'Save for large purchase' goal, determine the target amount and timeline. Create a dedicated savings plan.");
        }
        //if the user's goal is to invest for retirement make sure that they are contributing regularly
        else if (goal.equals("Invest for retirement"))
        {
            advice.add("If 'Invest for retirement' is your goal, ensure you are contributing regularly to retirement accounts.");
        }

        //If the advice ArrayList is empty then praise the user as their finances are perfect
        if (advice.isEmpty())
        {
            advice.add("Your finances seem to be in good order based on the information provided! Keep up the good work.");
        }
        //finally return the advice ArrayList after adding all of the advice Strings
        return advice;
    }

    //Generates personalize investment recommendations for the user
    public ArrayList<String> generateInvestmentAdvice(User user)
    {
        //Initialize all of the needed information from the user object 
        ArrayList<String> recommendations = new ArrayList<>();
        String riskProfile = user.getRiskProfileDescription();
        int emergencyFundMonths = user.calculateEmergencyFundMonths();
        //Give a quick disclaimer to go to a real professional for real financial advice
        recommendations.add("--- IMPORTANT DISCLAIMER ---");
        reccomendations.add("The following is general investment information, not financial advice.");
        recommendations.add("Consult with a qualified financial advisor before making any investment decisions.");
        recommendations.add("----------------------------");

        //if the user does not have adequate 
        if (emergencyFundMonths < ADEQUATE_EMERGENCY_FUND_MONTHS)
        {
            recommendations.add(String.format("Before investing, ensure you have an adequate emergency fund (at least %d months of expenses). You currently have %d.", ADEQUATE_EMERGENCY_FUND_MONTHS, emergencyFundMonths));
            return recommendations;
        }

        recommendations.add(String.format("Based on your risk profile, you are classified as: %s.", riskProfile));

        //Give the user recommendations if they are conservative
        if (riskProfile.equals("Conservative"))
        {
            recommendations.add("Consider low-risk investments such as:");
            recommendations.add("- High-yield savings accounts");
            recommendations.add("- Certificates of Deposit (CDs)");
            recommendations.add("- Government bonds");
            recommendations.add("Focus on capital preservation.");
        }
        //Give the user recommendations if they are moderate
        else if (riskProfile.equals("Moderate"))
        {
            recommendations.add("Consider a balanced portfolio including:");
            recommendations.add("- Diversified index funds (e.g., S&P 500 ETFs)");
            recommendations.add("- Blue-chip stocks");
            recommendations.add("- Corporate bonds");
            recommendations.add("Aim for a mix of growth and stability.");
        }
        //Give the user recommendations if they are aggressive
        else if (riskProfile.equals("Aggressive"))
        {
            recommendations.add("You might explore higher-growth potential investments, understanding the higher risk:");
            recommendations.add("- Growth stocks");
            recommendations.add("- Sector-specific ETFs");
            recommendations.add("- Potentially a small allocation to alternative investments (with caution and research).");
            recommendations.add("Ensure diversification even with an aggressive strategy.");
        }
        //Default case
        else
        {
            recommendations.add("Could not determine specific recommendations for your risk profile.");
        }

        //Check the timeframes
        //if the timeframe is long-term
        if (user.getInvestmentTimeframe() == 3)
        {
            recommendation.add("With along-term investment timeframe, you can generally afford to take on more risk for potentially higher returns.");
        }
        //if the timeframe is short-term
        else if (user.getInvestmentTimeframe() == 1)
        {
            recommendations.add("For short-term goals, prioritize capital preservation and liquidity");
        }
        //Add a recommendation to diversify your account to every user
        recommendations.add("Always remember to diversify your investments to manage risk.");
        //Add a recommendation to review and rebalance your portfolio regularly to every user
        recommendations.add("Regularly review and rebalance your portfolio as your circumstances and goals change.");
        //finally return the recommendations ArrayList
        return recommendations;
    }
}

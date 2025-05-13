import java.util.Arrays;

public class User
{
    private String name;
    private int age;
    private double monthlyIncome;
    private String[] expenseCategories;
    private double[] expenseAmounts;
    private double currentSavings;
    private double totalDebt;
    private double monthlyDebtPayments;
    private String financialGoal; // Stores the string representation of the goal
    private int investmentTimeframe; // 1: Short, 2: Medium, 3: Long
    private int volatilityTolerance; // 1: Sell, 2: Wait, 3: Buy
    private int investmentKnowledge; // 1: Beginner, 2: Intermediate, 3: Advanced

    /*
    Initializes a User with name, age, income, and default values for other fields.
    User sets the provided name, age, and income, and initializes all other fields like expenses, savings, and risk profile values with default values (mostly zeros and empty arrays).
    */
    public User(String name, int age, double monthlyIncome)
    {
        this.name = name;
        this.age = age;
        this.monthlyIncome = monthlyIncome;
        this.expenseCategories = new String[0]; // Initialize with empty arrays
        this.expenseAmounts = new double[0];    // Initialize with empty arrays
        this.currentSavings = 0;
        this.totalDebt = 0;
        this.monthlyDebtPayments = 0;
        this.financialGoal = "";
        this.investmentTimeframe = 0;
        this.volatilityTolerance = 0;
        this.investmentKnowledge = 0;
    }

    //Getters
    //Returns the user's name. Simply returns the value of the private name field.
    public String getName() 
    {
        return name;
    }
    //Returns the user's age. Directly accesses and returns the age variable.
    public int getAge() 
    {
        return age;
    }
    //Returns the user's monthly income. Returns the value stored in monthlyIncome.
    public double getMonthlyIncome() 
    {
        return monthlyIncome;
    }
    //Returns the user's expense categories. Gives access to the array expenseCategories.
    public String[] getExpenseCategories() 
    {
        return expenseCategories;
    }
    //Returns the array that holds expense values for each category.
    public double[] getExpenseAmounts() 
    {
        return expenseAmounts;
    }
    //Returns the currentSavings
    public double getCurrentSavings() 
    {
        return currentSavings;
    }
    //Returns the total debt
    public double getTotalDebt()
    {
        return totalDebt;
    }
    //returns the monthly debt payments
    public double getMonthlyDebtPayments() 
    {
        return monthlyDebtPayments;
    }
    //returns the financial goals
    public String getFinancialGoal() 
    {
        return financialGoal;
    }
    //returns the investment timeframe
    public int getInvestmentTimeframe() 
    {
        return investmentTimeframe;
    }
    //returns the volatility tolerance of the user
    public int getVolatilityTolerance() 
    {
        return volatilityTolerance;
    }
    //returns the user's investment knowledge
    public int getInvestmentKnowledge() 
    {
        return investmentKnowledge;
    }  
    
    // Setters
    //sets the user's name based on a parameter
    public void setName(String name) {
        this.name = name;
    }
    //sets the age of the user based on a parameter
    public void setAge(int age) {
        this.age = age;
    }
    //sets the monthly income of the user based on a parameter
    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
    //sets the current savings of the user based on a parameter
    public void setCurrentSavings(double currentSavings) {
        this.currentSavings = currentSavings;
    }
    //sets the user's expenses based on an array of expense category names and an array of corresponding expense amounts
    public void setExpenses(String[] categories, double[] amounts)
    {
        this.expenseCategories = categories;
        this.expenseAmonuts = amounts;
    }
    //sets the user's debt information based on the total amount of debt and the monthly debt payment
    public void setDebtInfo(double totalDebt, double monthlyDebtPayments)
    {
        this.totalDebt = totalDebt;
        this.monthlyDebtPayments = monthlyDebtPayments;
    }
    //sets the user's financial goal based on a choice
    public void setFinancialGoal(int goalChoice)
    {
        //if parameter is 1 set priority to build an emergency fund
        if (goalChoice == 1)
        {
            this.financialGoal = "Build emergency fund";
        }
        //2: pay off debt
        else if (goalChoice == 2)
        {
            this.financialGoal = "Pay off debt";
        }
        //3: save for large purchase
        else if (goalChoice == 3)
        {
            this.financialGoal = "Save for large purchase";
        }
        //4: invest for retirement
        else if (goalChoice == 4)
        {
            this.financialGoal = "Invest for retirement";
        }
        //5: other
        else if (goalChoice == 5)
        {
            this.financialGoal = "Other";
        }
        //base case: not specified
        else
        {
            this.financialGoal = "Not specified";
        }
    }
    //sets the user's risk profile
    public void setRiskProfile(int timeframe, int volatilityResponse, int knowledgeLevel)
    {   
        this.investmentTimeframe = timeframe;
        this.volatilityTolerance = volatilityResponse;
        this.investmentKnowledge = knowledgeLevel;
    }
    //calculates the total monthly expenses
    public double calculateTotalExpenses() 
    {
        double total = 0;
        for (int i = 0; i < expenseAmounts.length; i++) {
            total += expenseAmounts[i];
        }
        return total;
    }
    //calculates the disposable income (monthly income - total monthly expenses)
    public double calculateDisposableIncome()
    {
        return monthlyIncome - calculateTotalExpenses();
    }
    //calculates the savings rate as a percentage ((disposable income / monthly income) * 100)
    public double calculateSavingsRate()
    {
        if (monthlyIncome == 0)
        {
            return 0;
        }
        return (calculateDisposableIncome() / monthlyIncome) * 100;
    }
    //Calculates the debt-to-income ratio as a percentage ((monthly debt payments / monthly income) * 100)
    public double calculateDebtToIncomeRatio()
    {
        if (monthlyIncome == 0)
        {
            return 0;
        }
        return (monthlyDebtPayments / monthlyIncome) * 100;
    }
    //calculates how many months of expenses the current savings can cover
    public int calculateEmergencyFundMonths()
    {
        double monthlyExpenses = calculateTotalExpenses();
        if (monthlyExpenses == 0)
        {
            return 0;
        }
        return (int) (currentSavings / monthlyExpenses);
    }
    //determines the risk profile description based on the risk score
    public String getRiskProfileDescription()
    {
        int riskScore = calculateRiskScore();
        //Example thresholds: 1 + 1 + 1 = 3
        if (riskScore <= 3)
        {
            return "Conservative"
        }
        //Example thresholds: 2 + 2 + 2 = 6
        else if (riskScore <= 6)
        {
            return "Moderate";
        }
        //Example thresholds: 3 + 3 + 3 = 9
        else
        {
            return "Aggressive";
        }
    }
    //calculates a simple risk score based on timeframe, volatility tolerance, and knowledge
    private int calculateRiskScore()
    {
        // Ensure that the inputs are at least 1, if they were set using the 1-3 scale.
        // If they were not set (default to 0), this will still work but result in a lower score.
        int timeframeScore = Math.max(0, this.investmentTimeframe);
        int volatilityScore = Math.max(0, this.volatilityTolerance);
        int knowledgeScore = Math.max(0, this.investmentKnowledge);
        return timeframeScore + volatilityScore + knowledgeScore;
    }
    //Final setters, only for DIRECT use
    //Setter for totalDebt
    public void setTotalDebt(double totalDebt)
    {
        this.totalDebt = totalDebt;
    }
    //Setter for monthlyDebtPayments
    public void setMonthlyDebtPayments(double monthlyDebtPayments)
    {
        this.monthlyDebtPayments = monthlyDebtPayments;
    }
}

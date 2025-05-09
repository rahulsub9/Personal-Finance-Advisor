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
    You set the provided name, age, and income, and initialized all other fields like expenses, savings, and risk profile values with default values (mostly zeros and empty arrays).
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

    public double getTotalDebt()
    {
        return totalDebt;
    }

    public double getMonthlyDebtPayments() 
    {
        return monthlyDebtPayments;
    }

    public String getFinancialGoal() 
    {
        return financialGoal;
    }

    public int getInvestmentTimeframe() 
    {
        return investmentTimeframe;
    }

    public int getVolatilityTolerance() 
    {
        return volatilityTolerance;
    }

    public int getInvestmentKnowledge() 
    {
        return investmentKnowledge;
    }  

}

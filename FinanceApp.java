import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//RUNNER CLASS
public class FinanceApp
{
    //Scanner for user input
    private static Scanner console = new Scanner(System.in);

    //Main entry point of the app
    public static void main(String[] args)
    {
        //Create a blank User for the user to create a profile in
        User currentUser = null;
        //Show a welcome message
        displayWelcomMessage();
        //initialize the currentUser User variable if the user wants to create a profile
        if (userWantsToCreateProfile())
        {
            currentUser = createUserProfile();
            if (currentUser != null)
            {
                displayMainMenu(currentUser);
            }
        }
        //Display an exit message if the User doesn't want to create a profile
        else
        {
            displayExitMessage();
        }
        //Close scanner when done
        scanner.close();
    }

    //Displays a welcome message
    public static void displayWelcomeMessage()
    {
        System.out.println("Welcome to Personal Finance Advisor");
        System.out.println("This application whill help you manage your finances and provide personalized advice.");
        System.out.println("-----------------------------------------------------------------------------------");
    }

    //Asks the user if they want to create a financial profile
    public static boolean userWantsToCreateProfile()
    {
        System.out.print("Would you like to create a financial profile? (yes/y or no/n): ");
        String response = console.nextLine();
        //Return whether or not the user wants to create a profile
        return response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y");
    }

    //Guides the user through creating their financial profile
    public User createUserProfile()
    {
        System.out.println("\nLet's create your financial profile.");

        System.out.print("Enter your name: ");
        String name = console.nextLine();

        int age = getIntInput(Enter your age: ", 18, 100);
        if (age == -1)
        {
            return null;
        } 

        double income = getDoubleInput("Enter your monthly income: $", 0.01, Double.MAX_VALUE);
        if (income == -1)
        {
            return null;
        }

        User user = new User(name, age, income);

        collectExpenseDate(user);
        collectFinancialData(user);
        assessRiskTolerance(user);

        System.out.println("\nProfile successfully for " + user.getName() + "!");
        return user;
    }

    //Collects monthly expense data from the user.
    public static void collectExpenseData(User user)
    {
        String[] categories = {"Housing", "Utilities", "Food", "Transportation", "Entertainment", "Other};
        double[] amounts new double[categories.length];

        System.out.println("\nLet's collect your monthly expenses by category:");
        for (int i = 0; i < categories.length; i++)
        {
            amounts[i] = getDoubleInput(categories[i] + ": $", 0.0, Double.MAX_VALUE);
            // Handle potential validation failure during input
            if (amounts[i] == -1)
            {
                System.out.println("Expense collection aborted due to invalid input.");
                // Optionally, set default empty expenses or handle
                user.setExpenses(new String[0], new double[0]);
                return;
            }
        }
        user.setExpenses(categories, amounts)l
    }

    //Collects other financial data like savings and debt from the user
    public static void collectFinancialData(User user)
    {
        System.out.println("\nLet's collect some other financial information:");
        double savings = getDoubleInput("Enter your current savings: $", 0.0, Double.MAX_VALUE);
        //Abort if validation failed
        if (savings == -1)
        {
            return;
        }    
        user.setCurrentSavings(savings);

        double totalDebt = getDoubleInput("Enter your total debt: $", 0.0, Double.MAX_VALUE);
        //Abort if validation failed
        if (totalDebt == -1)
        {
            return;
        }

        double monthlyDebtPayments = 0;
        if (totalDebt > 0)
        {
            monthlyDebtPayments = getDoubleInput("Enter your total monthly debt payments: $", 0.0, Double.MAX_VALUE); 
            if (monthlyDebtPayments == -1)
            {
                return;
            } 
        }
        user.setDebtInfo(totalDebt, monthlyDebtPayments);

        System.out.println("\nSelect your primary financial goal (1-5):");
        System.out.println("1. Build emergency fund");
        System.out.println("2. Pay off debt");
        System.out.println("3. Save for large purchase");
        System.out.println("4. Invest for retirement");
        System.out.println("5. Other");
        int goalChoice = getIntInput("Enter your choice (1-5): ", 1, 5);
        if (goalChoice == -1)
        {
            return;
        }
        user.setFinancialGoal(goalChoice);
    }

    //Assesses the user's investment risk tolerance through a series of questions
    public static void assessRiskTolerance(User user)
    {
        System.out.println("\nLet's assess your investment risk tolerance.");

        System.out.println("\nWhat is your investment timeframe? (1-3)");
        System.out.println("1. Short-term (< 2 years)");
        System.out.println("2. Medium-term (2-5 years)");
        System.out.println("3. Long-term (> 5 years)");
        int timeframe = getIntInput("Enter your choice (1-3): ", 1, 3);
        if (timeframe == -1)
        {
            return;
        }

        System.out.println("\nHow would you react to a sudden 20% drop in your investments? (1-3)");
        System.out.println("1. Sell immediately to prevent further losses");
        System.out.println("2. Wait and see before making any decisions");
        System.out.println("3. Buy more while prices are low");
        int volatilityResponse = getIntInput("Enter your choice (1-3): ", 1, 3);
        if (volatilityResponse == -1)
        {
            return;
        }

        System.out.println("\nHow would you rate your investment knowledge? (1-3)");
        System.out.println("1. Beginner");
        System.out.println("2. Intermediate");
        System.out.println("3. Advanced");
        int knowledgeLevel = getIntInput("Enter your choice (1-3): ", 1, 3);
        if (knowledgeLevel == -1)
        {
            return;
        } 
        
        user.setRiskProfile(timeframe, volatilityResponse, knowledgeLevel);
    }

    //Displays the main menu and handles user choices.
    public static void displayMainMenu(User user)
    {
        boolean exitProgram = false;
        FinancialAdvisor advisor = new FinancialAdvisor();
        while(exitProgram == false)
        {
            System.out.println("\n========== Main Menu ==========");
            System.out.println("1. Check Financial Summary");
            System.out.println("2. Get Savings Advice");
            System.out.println("3. Get Investment Recommendations");
            System.out.println("4. Update Financial Information");
            System.out.println("5. Exit");
            System.out.println("==============================");
            int choice = getIntInput("Select an option: ", 1, 5);

            if (choice == 1)
            {
                displayFinancialSummary(user);
            }
            else if (choice == 2)
            {
                displaySavingsAdvice(user, advisor);
            }
            else if (choice == 3)
            {
                displayInvestmentAdvice(user, advisor);
            }
            else if (choice == 4)
            {
                updateFinancialInfo(user);
            }
            else if (choice == 5)
            {
                exitProgram = true;
                displayExitMessage();
            }
            else 
            {
                System.out.println("Invalid input type. Please enter a number.");
            }
        }
    }

    //Displays a comprehensive financial summary for the user
    public static void displayFinancialSummary(User user)
    {
        System.out.println("\n===== FINANCIAL SUMMARY =====");
        System.out.println("Name: " + user.getName());
        System.out.println("Age: " + user.getAge());
        System.out.printf("Monthly Income: $%.2f%n", user.getMonthlyIncome());

        System.out.println("\n--- EXPENSE BREAKDOWN ---");
        String[] categories = user.getExpenseAmounts();
        double[] amounts user.getExpenseAmounts();
        double totalExpenses = user.calculateTotalExpenses();

        if (categories.length == 0)
        {
            System.out.println("No expense data entered.");
        }
        else
        {
            for (int i = 0; i < categories.length; i++)
            {
                System.out.printf("%s: $%.2f%n", categories[i], amounts[i]);
            }
        }
        
    }
}

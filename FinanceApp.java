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
        console.close();
    }

    //Displays a welcome message
    public static void displayWelcomeMessage()
    {
        //Welcome the user to the app
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
        //Ask for their name and save it into a variable using the scanner
        System.out.print("Enter your name: ");
        String name = console.nextLine();
        //Use the helper method to get an age between 18 and 100
        int age = getIntInput(Enter your age: ", 18, 100);
        //Make sure it's in range
        if (age == -1)
        {
            return null;
        } 
        //Use the helper method to get a double income between 0.01 and the maximum double value
        double income = getDoubleInput("Enter your monthly income: $", 0.01, Double.MAX_VALUE);
        //Check if it's in range
        if (income == -1)
        {
            return null;
        }
        //Create a new user object
        User user = new User(name, age, income);
        //Use the three data methods to collect information for the profile
        collectExpenseData(user);
        collectFinancialData(user);
        assessRiskTolerance(user);
        //Confirmation that the profile was made 
        System.out.println("\nProfile successfully for " + user.getName() + "!");
        //return the user object
        return user;
    }

    //Collects monthly expense data from the user.
    public static void collectExpenseData(User user)
    {
        //Create an array of the different categories
        String[] categories = {"Housing", "Utilities", "Food", "Transportation", "Entertainment", "Other};
        double[] amounts new double[categories.length];

        System.out.println("\nLet's collect your monthly expenses by category:");
        //Loop through the categories
        for (int i = 0; i < categories.length; i++)
        {
            //Ask the user for how much they spend per month on the following categories
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
        //Set the user's expenses based on the two arrays of data we collected
        user.setExpenses(categories, amounts);
    }

    //Collects other financial data like savings and debt from the user
    public static void collectFinancialData(User user)
    {
        System.out.println("\nLet's collect some other financial information:");
        //Prompt the user for their savings amount
        double savings = getDoubleInput("Enter your current savings: $", 0.0, Double.MAX_VALUE);
        //Abort if validation failed
        if (savings == -1)
        {
            return;
        }    
        //Set the user's savings to the user object
        user.setCurrentSavings(savings);
        //Ask the user how much debt they have
        double totalDebt = getDoubleInput("Enter your total debt: $", 0.0, Double.MAX_VALUE);
        //Abort if validation failed
        if (totalDebt == -1)
        {
            return;
        }

        double monthlyDebtPayments = 0;
        //If the user has more than 0 debt ask them how much they pay per month
        if (totalDebt > 0)
        {
            monthlyDebtPayments = getDoubleInput("Enter your total monthly debt payments: $", 0.0, Double.MAX_VALUE); 
            //Make sure the debt amount is valid
            if (monthlyDebtPayments == -1)
            {
                return;
            } 
        }
        //Set the user's debt information based on the two variables
        user.setDebtInfo(totalDebt, monthlyDebtPayments);

        System.out.println("\nSelect your primary financial goal (1-5):");
        System.out.println("1. Build emergency fund");
        System.out.println("2. Pay off debt");
        System.out.println("3. Save for large purchase");
        System.out.println("4. Invest for retirement");
        System.out.println("5. Other");
        //Ask the user what their choice is
        int goalChoice = getIntInput("Enter your choice (1-5): ", 1, 5);
        if (goalChoice == -1)
        {
            return;
        }
        //Use the setFinancialGoal to enact the cooresponding action based on the user's choice
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
        //Ask the user what their choice is 
        int timeframe = getIntInput("Enter your choice (1-3): ", 1, 3);
        //Check if the input is valid
        if (timeframe == -1)
        {
            return;
        }
        System.out.println("\nHow would you react to a sudden 20% drop in your investments? (1-3)");
        System.out.println("1. Sell immediately to prevent further losses");
        System.out.println("2. Wait and see before making any decisions");
        System.out.println("3. Buy more while prices are low");
        //Ask the user for their response
        int volatilityResponse = getIntInput("Enter your choice (1-3): ", 1, 3);
        //Make sure their input is valid
        if (volatilityResponse == -1)
        {
            return;
        }

        System.out.println("\nHow would you rate your investment knowledge? (1-3)");
        System.out.println("1. Beginner");
        System.out.println("2. Intermediate");
        System.out.println("3. Advanced");
        //Ask the user what their answer to the question is 
        int knowledgeLevel = getIntInput("Enter your choice (1-3): ", 1, 3);
        //Make sure it is valid
        if (knowledgeLevel == -1)
        {
            return;
        } 
        //Set the user's risk profile based on their answers to the 3 questions
        user.setRiskProfile(timeframe, volatilityResponse, knowledgeLevel);
    }

    //Displays the main menu and handles user choices.
    public static void displayMainMenu(User user)
    {
        //Create a boolean that acts as an exit variable
        boolean exitProgram = false;
        FinancialAdvisor advisor = new FinancialAdvisor();
        //While the user wants to be in the program, keep it running
        while(exitProgram == false)
        {
            System.out.println("\n========== Main Menu ==========");
            System.out.println("1. Check Financial Summary");
            System.out.println("2. Get Savings Advice");
            System.out.println("3. Get Investment Recommendations");
            System.out.println("4. Update Financial Information");
            System.out.println("5. Exit");
            System.out.println("==============================");
            //Ask the user what they want to do
            int choice = getIntInput("Select an option: ", 1, 5);
            //If they want a financial summary, run the financial summary method
            if (choice == 1)
            {
                displayFinancialSummary(user);
            }
            //If they want savings advice, run the savings advice method in the Finance Advisor class
            else if (choice == 2)
            {
                displaySavingsAdvice(user, advisor);
            }
            //If they want investment advice, run the investment advice method in the Finance Advisor class
            else if (choice == 3)
            {
                displayInvestmentAdvice(user, advisor);
            }
            //If they want financial information, run the financial info method
            else if (choice == 4)
            {
                updateFinancialInfo(user);
            }
            //if they want to exit, set the boolean to true so the while loop stops
            else if (choice == 5)
            {
                exitProgram = true;
                //display the exit message
                displayExitMessage();
            }
            //if the user inputted a wrong number tell them
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
        //Use the getter methods to populate the needed variables in this method
        System.out.println("\n--- EXPENSE BREAKDOWN ---");
        String[] categories = user.getExpenseAmounts();
        double[] amounts user.getExpenseAmounts();
        double totalExpenses = user.calculateTotalExpenses();
        //if the user has no category expense information
        if (categories.length == 0)
        {
            System.out.println("No expense data entered.");
        }
        //If they do, print out how much they pay each month for each category
        else
        {
            for (int i = 0; i < categories.length; i++)
            {
                System.out.printf("%s: $%.2f%n", categories[i], amounts[i]);
            }
        }
        //Print out all of the user's private instance variables
        System.out.printf("Total Monthly Expenses: $%.2f%n", totalExpenses);

        System.out.printf("\nDisposable Income: $%.2f%n", user.calculateDisposableIncome());
        System.out.printf("Savings Rate: %.2f%%%n", user.calculateSavingsRate());
        System.out.printf("Debt-to-Income Ratio: %.2f%%%n", user.calculateDebtToIncomeRatio());

        System.out.printf("\nCurrent Savings: $%.2f%n", user.getCurrentSavings());
        System.out.printf("Total Debt: $%.2f%n", user.getTotalDebt());
        //If the user has debt, print out their debt
        if (user.getTotalDebt() > 0)
        {
            System.out.printf("Monthly Debt Payments: $%.2f%n", user.getMonthlyDebtPayments());
        }
        //Print out more user specific methods
        System.out.println("Emergency Fund: %d months of expenses%n", user.calculateEmergencyFundMonths());
        System.out.println("\nFinancial Goal: " + user.getFinancialGoal());
        System.out.println("Risk Profile: " + user.getRiskProfileDescription());
        System.out.println("===========================");
    }

    //Displays savings advice generated by the FinancialAdvisor.
    public static void displaySavingsAdvice(User user, FinancialAdvisor advisor)
    {
        System.out.println("\n===== SAVINGS ADVICE =====");
        //Create an ArrayList for savings advice
        ArrayList<String> savingsAdvice = advisor.generateSavingsAdvice(user);
        //Iterate through this ArrayList and print out each element
        for (int i = 0; i < savingsAdvice.size(); i++)
        {
            System.out.println("- " + savingsAdvice.get(i));
        }
    }

    //Displays investment advice generated by the Financial Advisor
    public static void displayInvestmentAdvice(User user, FinancialAdvisor advisor)
    {
        System.out.println("\n===== INVESTMENT RECOMMENDATIONS =====");
        //Create an ArrayList for savings advice
        ArrayList<String> investmentAdvice = advisor.generateInvestmentAdvice(user);
        //Iterate through this ArrayList and print out each element
        for (int i = 0; i < investmentAdvice.size(); i++)
        {
            System.out.println("- " + investmentAdvice.get(i));
        }
    }

    //Allows the user to update parts of their financial information.
    public static void updateFinancialInfo(User user)
    {
        //Create a boolean that acts as an escape variable for the while loop
        boolean returnToMenu = false;
        //While the escape variable is false
        while(returnToMenu == false)
        {
            System.out.println("\n===== UPDATE FINANCIAL INFORMATION =====");
            System.out.println("What would you like to update?");
            System.out.println("1. Monthly Income");
            System.out.println("2. Monthly Expenses");
            System.out.println("3. Current Savings");
            System.out.println("4. Debt Information");
            System.out.println("5. Financial Goal");
            System.out.println("6. Risk Profile");
            System.out.println("7. Return to Main Menu");
            System.out.println("======================================");
            //Prompt the user for their choice
            int choice = getIntInput("Select an option: ", 1, 7);
            //Create a boolean that tells the program if the user has updated their information
            boolean updated = false;
            //If the user wants to update their monthly income
            if (choice == 1)
            {
                //Prompt the user for their new monthly income
                double newIncome = getDoubleInput("Enter a new monthly income: $", 0.01, Double.MAX_VALUE);
                if (newIncome != -1)
                {
                    user.setMonthlyIncome(newIncome);
                    //set updated to true
                    updated = true;
                }
            }
            //If the user wants to update their monthly expenses by category
            else if (choice == 2)
            {
                //run the helper method that does this
                collectExpenseData(user);
                //set updated to true
                updated = true;
            }
            //If the user wants to update their current savings
            else if (choice == 3)
            {
                //Prompt the user for their new savings
                double newSavings = getDoubleInput("Enter new current savings: $", 0.0, Double.MAX_VALUE);
                if (newSavings != -1)
                {
                    //Run the method that sets the savings to the user object
                    user.setCurrentSavings(newSavings);
                    //set updated to true
                    updated = true;
                }
            }
            //If the user wants to update their debt information
            else if (choice == 4)
            {
                System.out.println("\nUpdating Debt Information:");
                //Prompt the user for their new total debt
                double newTotalDebt = getDoubleInput("Enter new total debt: $", 0.0, Double.MAX_VALUE);
                if (newTotalDebt == -1)
                {
                    break;
                }

                double newMonthlyDebtPayments = 0;
                if (newTotalDebt > 0)
                {
                    //Ask the user for their new monthly debt payments if they do have debt
                    newMonthlyDebtPayments = getDoubleInput("Enter new monthly debt payments: $", 0.0, Double.MAX_VALUE);
                    if (newTotalDebt == -1)
                    {
                        break;
                    }
                }
                //Set the user object's debt information
                user.setDebtInfo(newTotalDebt, newMonthlyDebtPayments);
                //set updated to true
                updated = true;
            }
            //If the user wants to update their financial goal
            else if (choice == 5)
            {
                System.out.println("\nSelect your new primary financial goal (1-5):");
                System.out.println("1. Build emergency fund");
                System.out.println("2. Pay off debt");
                System.out.println("3. Save for large purchase");
                System.out.println("4. Invest for retirement");
                System.out.println("5. Other");
                //Get the user's choice
                int newGoalChoice = getIntInput("Enter your choice (1-5): ", 1, 5);
                if (newGoalChoice != -1)
                {
                    //Set the user's choice
                    user.setFinancialGoal(newGoalChoice);
                    //set updated to true
                    updated = true;
                }
            }
            //If the user wants to update their risk tolerance
            else if (choice == 6)
            {
                //Run the risk tolerance method
                assessRiskTolerance(user);
                //set updated to true
                updated = true;
            }
            //If the user wants to exit to the main menu
            else if (choice == 7)
            {
                //Change the escape variable to true
                returnToMenu = true;
            }
            //If the user does not input a valid number
            else
            {
                //Print an error message
                System.out.println("Invalid input type. Please enter a number.");
            }

            //if updated is true then print a confirmation message
            if (updated)
            {
                System.out.println("Information updated successfully!");
            }
            //If anything is wrong display an error message and tell the user to try again
            if (returnToMenu == false && choice != -1 && (choice < 1 || choice > 7)
            {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    //Displays an exit message to the user
    public static void displayExitMessage()
    {
        System.out.println("\nThank you for using Personal Finance Advisor. Goodbye!");
    }

    //Helper methods
    
    //Helper to get an integer input from the user within a specified range
    private static int getIntInput(String text, int min, int max)
    {
        // Variable to store the successfully validated input.
        int validatedInput = 0;
        // Flag to control the input loop.
        boolean validInputReceived = false;
        // Loop continues as long as validInputReceived is false.
        while (validInputeReceived == false)
        {
            // Display the prompt to the user.
            System.out.print(text);
            // Check if the next token in the input stream is a valid integer.
            if (console.hasNextInt())
            {
                // Read the integer.
                int currentUserInput = console.nextInt();
                // Consume the rest of the line (including the newline character) to  prevent issues with subsequent nextLine() calls if any.
                console.nextLine();
                // Check if the entered integer is within the specified range [min, max].
                if (currentUserInput >= min && currentUserInput <= max)
                {
                    // Set the flag to true to exit the loop and set the value
                    validatedInput = currentUserInput;
                    validatedInputReceived = true;
                }
                else
                {
                    // Input is an integer but outside the valid range.
                   System.out.printf("Invalid input. Please enter a number between %d and %d.%n", min, max);
                    
                }
            }
            else
            {
                //Input is not an integer
                System.out.println("Invalid input. Please enter a whole number.");
                //Do this to make sure the scanner sees any more Strings after searching for an int
                console.nextLine();
            }
        }
        //Finally return the validated int value
        return validatedInput;
    }

    //Helper to get a double input from the user within a specified range
    private static double getDoubleInput(String text, double min, double max)
    {
        // Variable to store the successfully validated input.
        double validatedInput = 0;
        // Flag to control the input loop.
        boolean validInputReceived = false;
        // Loop continues as long as validInputReceived is false.
        while (validInputeReceived == false)
        {
            // Display the prompt to the user.
            System.out.print(text);
            // Check if the next token in the input stream is a valid double.
            if (console.hasNextDouble())
            {
                // Read the double.
                double currentUserInput = console.nextInt();
                // Consume the rest of the line (including the newline character) to  prevent issues with subsequent nextLine() calls if any.
                console.nextLine();
                // Check if the entered double is within the specified range [min, max].
                if (currentUserInput >= min && currentUserInput <= max)
                {
                    // Set the flag to true to exit the loop and set the value
                    validatedInput = currentUserInput;
                    validatedInputReceived = true;
                }
                else
                {
                    // Input is an double but outside the valid range.
                   System.out.printf("Invalid input. Please enter a number between %f and %f.%n", min, max);
                    
                }
            }
            else
            {
                //Input is not an double
                System.out.println("Invalid input. Please enter a double.");
                //Do this to make sure the scanner sees any more Strings after searching for an double
                console.nextLine();
            }
        }
        //Finally return the validated double value
        return validatedInput;
    }
}

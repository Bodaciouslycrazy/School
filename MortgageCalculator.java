import java.util.Scanner;

public class MortgageCalculator {
	
	public static void main( String[] args ){
		
		//Creating the loan terms and rates.
		int[] loanTerms = { 7, 15, 30};
		double[] interestRates = new double[3];
		
		//get the interest rates
		System.out.println("\nWelcome to Mortgage Calculator Program.");
		getCurrentRates( loanTerms, interestRates);
		
		//Get the users choice of loan term and rate.
		int index = getTermInformation( loanTerms, interestRates);
		
		
		//Read the user's input for priciple value.
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter Principle Amount: $");
		double principle = input.nextDouble();
		
		displayLoanSummary(principle, loanTerms[index] , interestRates[index]);
		
		//call table method
		displayAmortTable(principle, loanTerms[index], interestRates[index]);
		
	}
	
	
	
	/*
	 * This lets the user define the rates he wants for his terms.
	 * Arguments:
	 *       int[] terms
	 *       double[] rates
	 */
	private static void getCurrentRates( int[] terms, double[] rates ){
		//set up input from user
		Scanner input = new Scanner(System.in);
		
		
		//for loop through rates, getting user input for each.
		for( int i = 0; i < terms.length; i++){
			System.out.print("Enter current rate of "+ terms[i] +" years> ");
			rates[i] = input.nextDouble();
		}
		
		//no return neccesary. The arguments were references to array slots, which were changed.
	}
	
	
	
	/*
	 * Displays all the 3 choices and asks the user to select an option from the 3 choices.
	 * returns the index of the option.
	 */
	private static int getTermInformation(int[] terms, double[] rates){
		//index of user's choice will be stored here.
		int choice;
		
		//Keep looping until user selects a valid choice.
		while(true){
			System.out.println("\nWelcome to XYZ Bank!");
			
			//Displays terms and rates.
			for(int i = 0; i < terms.length; i++){
				System.out.println("1) "+ terms[i] +" year term at a rate of "+ rates[i]);
			}
			
			//Set up user input
			Scanner input = new Scanner(System.in);
			System.out.print("Select (1, 2, or 3) an option for the loan> ");
			choice = input.nextInt();
			
			//check for invalid input. break otherwise.
			if( choice < 1 || choice > 3){
				System.out.println("That is not a valid choice");
				continue;
			}
			else
				break;
			
		}
		
		//return index
		return choice - 1;
	}
	
	
	
	/*
	 * Displays the sammary of the loan including principle amount, interest rate, and term.
	 */
	private static void displayLoanSummary(double principle, int term, double rate){
		System.out.println();
		System.out.printf("Principle amount: $%.2f\n", principle);
		System.out.printf("Int Rate: %.2f%%\n", rate);
		System.out.printf("Term of Loan (Months): %d", term* 12);
	}
	
	
	
	/*
	 * displays a table to show monthly activity of loan
	 */
	private static void displayAmortTable( double principle, int term, double rate){
		//variables to be used
		int month;
		double monthlyInterestRate, monthlyMortgage, totalMortgage;
		double totalInterest, totalDue, interestPaid, principlePaid;
		
		
		//calculations.
		month = term * 12;
		monthlyInterestRate = rate / 1200.0;
		
		monthlyMortgage = principle * ( monthlyInterestRate / ( 1 - Math.pow((1 + monthlyInterestRate), -month )));
		
		totalMortgage = monthlyMortgage * term;
		totalInterest = totalMortgage - principle;
		
		//set up for "for loop"
		totalDue = principle;
		
		//start of table.
		System.out.printf("\n%-10s%-16s%-16s%-16s\n", "Payment", "Principle", "Interest", "Balance");
		
		//Print out table.
		for( int i = 0; i < month; i++){
			interestPaid = totalDue * monthlyInterestRate;
			
			if(interestPaid <= 0)
				interestPaid = 0;
			
			principlePaid = monthlyMortgage - interestPaid;
			totalDue = totalDue - principlePaid;
			
			if(totalDue < 0)
				totalDue = 0;
			
			
			System.out.printf("%-10d$%-15.2f$%-15.2f$%-15.2f\n", i, principlePaid, interestPaid, totalDue);
		}
		
	}
	
}

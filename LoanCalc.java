// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double currentBalance = loan;
		double monthlyRate = rate / 100.0;
		for(int i=0; i<n; i++){
			currentBalance *= (1.0 + monthlyRate);
			currentBalance -= payment;
		}
		return currentBalance;		
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;
		double guess = 0.0001;
		double balanceForCurrentGuess = endBalance(loan, rate, n, guess);
		while (balanceForCurrentGuess > 0) {
			guess += epsilon;
			balanceForCurrentGuess = endBalance(loan, rate, n, guess);
			iterationCounter++;
		}
		return guess;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;  // calculate the "Side effects"
		double monthlyRate = rate / 100.0;
        double lowerBound = loan / (double) n;
		double upperBound = loan * Math.pow(1.0 + monthlyRate, n);
		double guess = (lowerBound + upperBound) / 2.0; // midpoint of the current range
		double balanceForCurrentGuess = endBalance(loan, rate, n, guess);
		while (Math.abs(balanceForCurrentGuess)>= epsilon) {
			if(balanceForCurrentGuess > 0){ //if the guess was too high
				lowerBound = guess;
			} else {  //if the guess was too low
				upperBound = guess;
			}
			guess = (upperBound + lowerBound) / 2.0;
			balanceForCurrentGuess = endBalance(loan, rate, n, guess);
			iterationCounter++;			
		}
		return guess;
    }
}
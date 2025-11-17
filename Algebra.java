// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    // System.out.println(plus(2,3));   // 2 + 3 =5
		// System.out.println(plus(-2,-3)); // =-5
	    //  System.out.println(minus(7,2));  // 7 - 2 =5
   		//  System.out.println(minus(2,7));  // 2 - 7 =-5
		//  System.out.println(minus(2,-7)); //=9
 		//System.out.println(times(-3,4));  // 3 * 4 =12
   		//System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2 =10
   		// System.out.println(pow(5,3));      // 5^3 =125
   		// System.out.println(pow(3,5));      // 3^5 =243
   		 System.out.println(div(12,3));   // 12 / 3 =4   
   		 System.out.println(div(5,5));    // 5 / 5  =1
   		 System.out.println(div(25,-7));   // 25 / 7 =3
   		// System.out.println(mod(25,7));   // 25 % 7 =4
   		// System.out.println(mod(120,6));  // 120 % 6 =0   
   		// System.out.println(sqrt(36)); //=6
		// System.out.println(sqrt(263169)); //=513
   		// System.out.println(sqrt(76123)); //=275
	}  

	// Returns x1 + x2
	public static int plus(int x1, int x2) {
		if (x2 == 0){
			return x1;
		}
		if (x2 > 0){
			for(int i=0; i<x2; i++){
			x1++;
		}	
		} else {
		for(int i=0; i>x2; i--){
			x1--;
		}	
		}
		return x1;
	}

	// Returns x1 - x2
	public static int minus(int x1, int x2) {
		if (x2 == 0){
			return x1;
		}
		if (x2 > 0) {
			for(int i=0; i<x2; i++){
			x1--;
		}
		} else {
		    for(int i=0; i<-x2; i++){
			x1++;
		}	
		}	
		return x1;
	}

	// Returns x1 * x2
	public static int times(int x1, int x2) {
		if (x2 == 0 || x1 == 0){
			return 0;
		}
		boolean resultIsNegative =((x1 < 0) ^ (x2 < 0));
		int a = x1;
		if(a < 0){
			a = -a;
		}
		int b = x2;
		if(b < 0){
			b = -b;
		}
		if(a > b){
			int temp = a;
			a = b;
			b = temp;
		}
		int result = 0;
		for(int i=0; i<a; i++){
			result += b;
		}
		if (resultIsNegative) {
			return -result;
		} 
		return result;
	
	}

	// Returns x^n (for n >= 0)
	public static int pow(int x, int n) {
		if (n == 0){
			return 1;
		}
		if (n == 1){
			return x;
		}
		int constant = x;
		for(int i=1; i<n; i++){
			x = times(x, constant);
		}
		return x;
	}

	// Returns the integer part of x1 / x2 
	public static int div(int x1, int x2) {
		if (x2==0) {
			return -1;
		}
		boolean resultIsNegative = (x1<0)^(x2<0);
		int a = x1;
		if(a < 0){
			a = -a;
		}
		int b = x2;
		if(b < 0){
			b = -b;
		}
		int counter = 0;
		while (a - b >= 0) {
			a = a - b;
			counter++;			
		}
		if(resultIsNegative){
			return -counter;
		}
		return counter;
	}

	// Returns x1 % x2
	public static int mod(int x1, int x2) {
		if (x2==0) {
			return -1;
		}
		int num = div(x1, x2);
		num = times(num, x2);
		return x1 - num;
	}	

	// Returns the integer part of sqrt(x) 
	public static int sqrt(int x) {
		if (x==0) {
			return 0;	// The square root of 0 is 0		
		}
		int root = 1;
		int sqrt = pow(root,2);
		while ( x >= sqrt) {
			root++;	
			sqrt = pow(root,2);	
		}
		return --root;
	}	  	  
}
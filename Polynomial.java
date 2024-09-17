public class Polynomial{
	
	double[] poly;
	
	public zero_poly() {
		poly = [0]; 
	}
	
	public create_poly(double[] coeff){
		poly = [coeff.length()]
		for(int i = 0; i < coeff.length(); i ++) {
			poly[i] = coeff[i]; 
		}
	}
	
	static double[] add(Polynomial poly1) {
		double[] arr = poly1.poly; 
		
		if(arr.length() > poly.length()){
			for(int i = 0; i < poly.length(); i ++) {
				
				arr[i] = arr[i] + poly[i]; 
			}
			
			return arr; 
			
		}
		else {
			for(int i = 0; i < arr.length(); i ++) {
				
				poly[i] = arr[i] + poly[i]; 
			}
			
			return poly;
		}
	}
	
	static double evaluate(double x) {
		double sum = 0; 
		
		for(int i = 0; i < poly.length(), i ++){
			sum  = sum + poly[i]*(x**i); 
		}
		
		return sum; 
	}
	
	static boolean hasRoot(double x) {
		
		value = evaluate(x); 
		
		if(value == 0) {
			return true ; 
		}
		return false; 
	}
	
	
}
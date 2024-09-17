public class Polynomial{
	
	private double[] poly;
	
	public Polynomial() {
		poly = new double[]{0}; 
	}
	
	public Polynomial(double[] coeff){
		this.poly = coeff.clone();
	}
	
	public Polynomial add(Polynomial poly1) {
		int maxLength = Math.max(this.poly.length, poly1.poly.length);
        double[] result = new double[maxLength];

        // Add corresponding coefficients from both polynomials
        for (int i = 0; i < maxLength; i++) {
            double firstCoeff;
            double secondCoeff;
            if(i < this.poly.length) {
            	firstCoeff  = poly[i]; 
            }
            else {
            	firstCoeff = 0; 
            }
            if (i < poly1.poly.length) {
            	secondCoeff = poly1.poly[i];
            }
            else {
            	secondCoeff = 0;
            }
            result[i] = firstCoeff + secondCoeff;
        }

        return new Polynomial(result);
	}
	
	public double evaluate(double x) {
		double sum = 0; 
		
		for(int i = 0; i < poly.length; i ++){
			sum  = sum + poly[i]*Math.pow(x, i); 
		}
		
		return sum; 
	}
	
	public boolean hasRoot(double x) {
		
		double value = evaluate(x); 
		
		if(value == 0) {
			return true ; 
		}
		return false; 
	}
	
	
	
	
}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Polynomial{
	
	double[] coeff;
	int[] expo; 
	
	public Polynomial() {
		coeff = new double[]{0}; 
		expo = new int[]{0}; 
	}
	
	public Polynomial(double[] polynomials, int[] exponents){
        coeff = new double[polynomials.length]; 
        expo = new int[exponents.length]; 
        for(int i = 0; i < coeff.length; i ++){
            coeff[i] = polynomials[i]; 
            expo[i] = exponents[i];
        }
    }
	
	public Polynomial(File file1) {
		try {
			Scanner scanner = new Scanner(file1);
			
			List<Double> coeffList = new ArrayList<>();
	        List<Integer> expList = new ArrayList<>();
			
			String my_poly = scanner.nextLine(); 
			
			String[] my_arr = my_poly.split("\\+");
			
			for (String sub_str : my_arr) {
	            String[] subTerms= sub_str.split("(?=-)");
	
	            for (String subTerm : subTerms) {
	            	 double result_coeff;
	                 int result_expo;
	
	                 if (subTerm.contains("x")) {
	                     String[] parts = subTerm.split("x");
	                     
	                     if (parts[0].equals("-")) {
	                    	 result_coeff = -1;
	                     } 
	                     else {
	                         result_coeff = Double.parseDouble(parts[0]);
	                     }
	                     
	                     if(parts.length > 0){
	                    	 result_expo = Integer.parseInt(parts[1]); 
	                     }
	                     else {
	                    	 result_expo = 0; 
	                     }
	                 }
	                 else {
	                	 result_coeff = Double.parseDouble(subTerm);
	                	 result_expo = 0; 
	                 }
	                 
	                 coeffList.add(result_coeff);
	                 expList.add(result_expo);
	            }
	       }
			
	       for (int i = 0; i < coeffList.size(); i++) {
	            coeff[i] = coeffList.get(i);
	            expo[i] = expList.get(i);
	       }
	       scanner.close();
		}
		catch (FileNotFoundException exp) {
            System.out.println("File not found");
        }
	}
	
	
	public int num_diff(int[] exponents1, int[] exponents2) {
		int diff = 0; 
		for(int i = 0; i < exponents1.length; i ++ ){
			int similar = 0; 
			for(int j = 0; j < exponents2.length; j ++){
				if(exponents1[i] == exponents2[j]){
					similar ++; 
				}
			}
			if(similar == 0){
				diff ++; 
			}
		}
		
		return diff; 
	}
	
	public Polynomial add(Polynomial poly1) {

		int num_similar = 0;  

		for (int i = 0; i < this.expo.length; i++) {
            for (int j = 0; j < poly1.expo.length; j++) {
                if (this.expo[i] == poly1.expo[j]) {
                    num_similar++;
                    break;
                }
            }
        }
		
		int diff = num_diff(this.expo, poly1.expo) + num_diff(poly1.expo, this.expo); 

		int totalsize = diff + num_similar; 

		double[] new_coeff = new double[totalsize]; 
		int[] new_expo = new int[totalsize]; 

		
		int index = 0;

        for (int i = 0; i < this.expo.length; i++) {
            for (int j = 0; j < poly1.expo.length; j++) {
                if (this.expo[i] == poly1.expo[j]) {
                    new_coeff[index] = this.coeff[i] + poly1.coeff[j];
                    new_expo[index] = this.expo[i];
                    index++;
                    break;
                }
            }
        }

        for (int i = 0; i < this.expo.length; i++) {
        	boolean similar = false; 
            for (int j = 0; j < poly1.expo.length; j++) {
                if (this.expo[i] == poly1.expo[j]) {
                    similar = true; 
                    break;
                }
            }
            if (!similar) {
                new_expo[index] = this.expo[i];
                new_coeff[index] = this.coeff[i];
                index++;
            }
        }

        for (int i = 0; i < poly1.expo.length; i++) {
            boolean similar = false;
            for (int j = 0; j < this.expo.length; j++) {
                if (poly1.expo[i] == this.expo[j]) {
                    similar = true;
                    break;
                }
            }
            if (!similar) {
                new_expo[index] = poly1.expo[i];
                new_coeff[index] = poly1.coeff[i];
                index++;
            }
        }
        
        Polynomial poly = new Polynomial(new_coeff, new_expo); 

        return poly;
	}
	
	public double evaluate(double x) {
		double sum = 0; 
		
		for(int i = 0; i < expo.length; i ++){
			sum  = sum + coeff[i]*Math.pow(x, expo[i]); 
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
	
	public Polynomial multiply(Polynomial poly1){
		int totalsize = this.coeff.length * poly1.coeff.length;
        double[] resultCoeff = new double[totalsize];
        int[] resultExpo = new int[totalsize];
        
        int index = 0;
        for (int i = 0; i < this.coeff.length; i++) {
            for (int j = 0; j < poly1.coeff.length; j++) {
                resultCoeff[index] = this.coeff[i] * poly1.coeff[j];
                resultExpo[index] = this.expo[i] + poly1.expo[j];
                index++;
            }
        }
        
        for (int i = 0; i < resultCoeff.length; i++) {
            for (int j = i + 1; j < resultCoeff.length; j++) {
                if (resultExpo[i] == resultExpo[j]) {
                    resultCoeff[i] += resultCoeff[j];
                    resultCoeff[j] = 0;
                    resultExpo[j] = -1000; 
                }
            }
        }
        
        int diff = 0;
        for (int i = 0; i < resultExpo.length; i++) {
            if (resultExpo[i] != -1000) {
                diff++;
            }
        }
        
        double[] finalCoeff = new double[diff];
        int[] finalExpo = new int[diff];
        
        index = 0;
        for (int i = 0; i < resultCoeff.length; i++) {
            if (resultExpo[i] != -1000) {
                finalCoeff[index] = resultCoeff[i];
                finalExpo[index] = resultExpo[i];
                index++;
            }
        }
        
        Polynomial poly = new Polynomial(finalCoeff, finalExpo);
        
        return poly;
        
	}
	
	
}
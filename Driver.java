public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));

		double[] c1 = {1.0, 2.0,3.0,4.0};
		int[] e1 = {0,1,2,3};

		Polynomial p1 = new Polynomial(c1, e1);

		double[] c2 = { 5.0, 6.0, 7.0, 8.0};
		int[] e2 = {0,1,2,3};
		Polynomial p2 = new Polynomial(c2, e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
		Polynomial poly1 = new Polynomial(new double[]{0, 4, 2, 3}, new int[]{0, 0, 0, 0});
        Polynomial poly2 = new Polynomial(new double[]{7, 1, 2, 5}, new int[]{0, 0, 0, 0}); 

		Polynomial result1 = poly1.multiply(poly2);
		System.out.println("s(0.1) = " + result1.evaluate(1));
	}
}
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private final int n;
	private final int trials;
	private final double[] v;
	private final double CONFIDENCE_95 = 1.96;
	private double mean;
	private double stddev;
	
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if (n <= 0 || trials <= 0)
    		throw new IllegalArgumentException();
    	this.n = n;
    	this.trials = trials;
    	this.v = new double[trials];
    	Percolation p;
    	for (int i = 0; i != this.trials; i++) {
    		p = new Percolation(this.n);
    		
    		while (!p.percolates()) {
    			p.open(StdRandom.uniform(1,n+1),StdRandom.uniform(1,n+1));
    		}
    		this.v[i] = (1.0) * p.numberOfOpenSites()/(this.n*this.n);	
    	}
    }

    // sample mean of percolation threshold
    public double mean() {
    	this.mean = StdStats.mean(this.v);
    	return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	this.stddev = StdStats.stddev(this.v);
    	return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	double confidenceLo = (this.mean - ((CONFIDENCE_95 * this.stddev)/Math.sqrt(this.trials)));
    	return confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	double confidenceHi = (this.mean + ((CONFIDENCE_95 * this.stddev)/Math.sqrt(this.trials)));
    	return confidenceHi;
    }
    
  /*  @Override
    public String toString() {
    	String str = "mean = " + mean() + System.lineSeparator() + "stddev = " + stddev() + System.lineSeparator() + "95% confidence interval = [" + confidenceLo() + ", " + confidenceHi() + "]";
    	return str;
    }    */

   // test client (see below)
   public static void main(String[] args) {
       PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
       
       System.out.println("mean = " + p.mean());
       System.out.println("stddev = " + p.stddev());
       System.out.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
       return;
   }

}

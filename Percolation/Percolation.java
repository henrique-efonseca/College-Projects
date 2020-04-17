import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int n;
    private final WeightedQuickUnionUF vector;
    private final WeightedQuickUnionUF full;
    private final boolean[] color;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        this.n = n;
        this.vector = new WeightedQuickUnionUF((n * n) + 2);
        this.color = new boolean[(n * n) + 2];
        color[0] = true;
        color[(n * n) + 1] = true;
        this.full =  new WeightedQuickUnionUF((n * n) + 1);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	if (!validate(row, col))
            throw new IllegalArgumentException();

    	int index = toIndex(row, col);

        if (!isOpen(row, col)) {

            color[index] = true;

            if (index <= this.n) {
            	this.vector.union(0, index);
            	this.full.union(0, index);
            }
            if (index > (this.n*this.n)-this.n) 
            	this.vector.union((n * n) + 1, index);
            
            if (index + 1 <= (n * n) && col != this.n && this.color[index + 1])
                        if (this.full.find(index + 1) != this.full.find(index)) {
                            this.vector.union(index + 1, index);
                            this.full.union(index + 1, index);
                        }
            if (index - 1 > 0 && col != 1 && this.color[index - 1])
                        if (this.full.find(index-1) != this.full.find(index)) {
                            this.vector.union(index - 1, index);
                            this.full.union(index - 1, index);
                        }
            if (index + this.n <= (n * n) && this.color[index + this.n])
                    if (this.full.find(index + this.n) != this.full.find(index)) {
                        this.vector.union(index + this.n, index);
                        this.full.union(index + this.n, index);
                    }
            if (index - this.n > 0 && this.color[index - this.n])
                    if (this.full.find(index - this.n) != this.full.find(index)) {
                        this.vector.union(index - this.n, index);
                        this.full.union(index - this.n, index);
                    }
        }
    }
    
    private boolean validate(int row, int col) {
    	return !(row <= 0 || row > this.n || col <= 0 || col > this.n);  	 
    }
    
    //2D indice to 1D indice
    private int toIndex(int row, int col){
    	return (this.n * (row - 1) + col);
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!validate(row, col))
            throw new IllegalArgumentException();

        int index = toIndex(row, col);
        
        return this.color[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if (!validate(row, col))
            throw new IllegalArgumentException();

    	int index = toIndex(row, col);
        
        return this.full.find(index) == this.full.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int c = 0;
        for (int i = 0; i != this.color.length; i++)
            if (this.color[i])
                c++;
        return c - 2;
    }

    // does the system percolate?
    public boolean percolates() {
        return (this.vector.find(0) == this.vector.find(n * n + 1));
    }
}

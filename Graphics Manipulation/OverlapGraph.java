/**
 * Class of objects to overlay graphics.
 * 
 * The Graphic objects of this class can be created in two forms:
 *    i) Giving the Image with colors;
 *    ii) Giving an ObjectsGraph object;
 *    iii) Giving an ObjectsGraph vector object;
 *    iv) Giving a vector with Images with colors.
 *    
 * For better functioning must also have the following classes: "ColorImage", 
 * "Color" & "Image Utils".
 * 
 * For more advanced use of graphics manipulation you should have the following classes: 
 * "ObjectsGraph" & "OverlapGraph".
 * 
 * 
 * @author Henrique Fonseca
 * 
 */

class OverlapGraph {

	private static final int LENGTH = 30;	

	public ObjectsGraph[] stack;
	public ObjectsGraph img;
	public int index;


	
	OverlapGraph() {
		this.stack = new ObjectsGraph[LENGTH];
		this.index = 0;
	}

	OverlapGraph(ObjectsGraph a) {
		this.stack = new ObjectsGraph[LENGTH];
		this.index = 0;

		this.stack[index] = a;
		this.index++;
	}

	OverlapGraph(ColorImage a) {
		ObjectsGraph img = new ObjectsGraph(a);

		this.stack = new ObjectsGraph[LENGTH];
		this.index = 0;

		this.stack[index] = img;
		this.index++;
	}

	OverlapGraph(ObjectsGraph[] a) {

		if (a.length > LENGTH)
			throw new IllegalStateException("The Stack" + a + "is too big.");

		this.stack = new ObjectsGraph[LENGTH];

		for (int i = 0, j = 0; i<a.length; i++) {
			if (a[i] != null) {
				this.stack[j] = a[i];
				j++;
			}
		}

		for (int i = 0; i<LENGTH; i++) {

			if (this.stack[i] != null)
				this.index = i;
		}

		this.index++;

	}

	OverlapGraph(ColorImage[] a) {

		if (a.length > LENGTH)
			throw new IllegalStateException("The Stack" + a + "is too big.");

		this.stack = new ObjectsGraph[LENGTH];

		for (int i = 0, j = 0; i<a.length; i++) {
			if (a[i] != null) {
				ObjectsGraph b = new ObjectsGraph(a[i]);
				this.stack[j] = b;
				j++;
			}
		}

		for (int i = 0; i<LENGTH; i++) {

			if (stack[i] != null)
				this.index = i;
		}

		this.index++;

	}

	void add(ObjectsGraph img) {
		if (this.isFull() == true) {
			throw new IllegalStateException("No space for more photos");
		} else {
			this.stack[index] = img;
			this.index++;
		}
	}

	void add(ColorImage img) {
		if (this.isFull() == true) {
			throw new IllegalStateException("No space for more photos");
		} else {
			ObjectsGraph newImg = new ObjectsGraph(img);
			this.stack[index] = newImg;
			this.index++;
		}
	}

	void addToIndex(ObjectsGraph img, int index) {
		if (this.isFull() == true)
			throw new IllegalStateException("No space for more photos");
		if (index > this.index)
			throw new IllegalStateException("Invalide index. The stack can't have null intermediate postions.");

		if (index == this.index) {
			this.stack[index] = img;
		} else {

			for (int i = index; i<this.stack.length - 1; i++) {
				this.stack[i + 1] = stack[i];
				this.index++;
			}
			this.stack[index] = img;
		}

	}

	void removeFromTop() {
		this.stack[index - 1] = null;
		index--;
	}

	public ObjectsGraph getTopGraphic() {

		return this.stack[this.index - 1];
	}

	ObjectsGraph getIndexGraphic(int index) {
		return this.stack[index];
	}

	void tradeGraphics(int indexA, int indexB) {
		if (indexA > stack.length || indexB > stack.length)
			throw new IllegalStateException("Index isn't valide.");
		if (indexA == indexB) {
			throw new IllegalArgumentException("Both index are equal.");
		}

		ObjectsGraph A = getIndexGraphic(indexA);

		this.stack[indexA] = this.stack[indexB];
		this.stack[indexB] = A;

	}

	OverlapGraph graphicsWithoutTitle() {

		ObjectsGraph[] StackNoTitle = new ObjectsGraph[LENGTH];

		for (int i = 0, j = 0; i<this.index; i++) {
			if (this.stack[i].getTitle() == "") {
				StackNoTitle[j] = this.stack[i];
				j++;
			}
		}

		this.stack = StackNoTitle;
		
		for (int i = 0; i < LENGTH; i++) {

			if (this.stack[i] != null)
				this.index = i;
		}

		this.index++;
		
		return this;
	}

	void alphabethicalGraphic() {
		ObjectsGraph[] aux = new ObjectsGraph[LENGTH];
		
		int count = 0;
		
		for (int i = 0; i<this.index; i++){
			if (this.stack[i].hasTitle() == false)
				aux[i] = this.stack[i];
				count ++;
		}
		
		for (int i = 0; i<this.index; i++){
			for (int j = 1; j<this.index - 1; j++) {

				if (((this.stack[i].hasTitle() && this.stack[j + i].hasTitle() != false)) && (this.stack[i].getTitle().compareTo(this.stack[i + j].getTitle()) > 0))
					this.stack[i] = this.stack[j + i];
			}
		}
		for(int i = this.index  - count; i < this.index; i ++){
			this.stack[i] = aux[i];
		}
		
	

	}

	

	
	ObjectsGraph overlap() {
		int maxWidth = 0;
		int maxHeight = 0;

		final Color Black = new Color(0, 0, 0);

		for (int i = 0; i<this.index; i++) {
			if (this.stack[i].getHeight() > maxHeight)
				maxHeight = this.stack[i].getHeight();
			if (this.stack[i].getWidth() > maxWidth) {

				maxWidth = this.stack[i].getWidth();
			}
		}

		ColorImage img = new ColorImage(maxWidth, maxHeight);
		ObjectsGraph overlapedImage = new ObjectsGraph(img);

		for (int i = 0; i < this.index; i++) {
			for (int x = 0; x < this.stack[i].graphic.getWidth() ; x++){
				for (int y = 0; y < this.stack[i].graphic.getHeight(); y++) {
					if (this.stack[i].graphic.getColor(x, y) != Black){
						overlapedImage.graphic.setColor(x, y, (this.stack[i].graphic.getColor(x, y)));
				}
				}
			}
		}

		
		return overlapedImage;

	}

	ObjectsGraph rotateOverlap() {
		return (this.overlap()).rotate();
	}

	private boolean isFull() {
	 return this.index >= this.stack.length;
			
	}

}
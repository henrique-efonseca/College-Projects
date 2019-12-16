/**
 * Class of objects that represents the concept of Graphics. 
 * 
 * The Graphic objects of this class can be created in two forms:
 *    i) Giving the Image with colors, the Graphic's Title, the Horizontal Axis' Title, 
 *    and the Vertical Axis' Title;
 *    ii) Only giving the Image with colors.
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

class ObjectsGraph {

	ColorImage graphic;
	String title;
	String horizontalAxis;
	String verticalAxis;

	

	ObjectsGraph(ColorImage graphic, String title, String horizontalAxis, String verticalAxi) {
		this.graphic = graphic;
		this.title = title;
		this.horizontalAxis = horizontalAxis;
		this.verticalAxis = verticalAxi;

	}

	ObjectsGraph(ColorImage graphic) {
		this.graphic = graphic;
		this.title = "";
		this.horizontalAxis = "";
		this.verticalAxis = "";

	}

	// Adds a Title to the Graphic
	public void setTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("The title can't be null.");

		this.title = title;
	}

	// Sets the Graphic Horizontal Axis Title
	public void setHorizontalAxis(String horizontalAxis) {
		if (horizontalAxis == null)
			throw new IllegalArgumentException("The Horizontal Axis can't be null.");

		this.horizontalAxis = horizontalAxis;
	}

	// Sets the Graphic Vertical Axis Title
	public void setVerticalAxis(String verticalAxis) {
		if (verticalAxis == null)
			throw new IllegalArgumentException("The Vertical Axis can't be null.");

		this.verticalAxis = verticalAxis;
	}

	// Sets the Graphic to Transparent
	public void setTransparent() {
		if (this.graphic == null)
			throw new NullPointerException("The image is null.");
		this.graphic = transparent(this.graphic);
	}

	// 
	public ObjectsGraph rotate() {
		ObjectsGraph rotated = new ObjectsGraph(StaticGraph.rotate(this.graphic));

		return rotated;

	}

	public int getHeight() {
		return this.graphic.getHeight();
	}

	public int getWidth() {
		return this.graphic.getWidth();
	}

	public ColorImage getImg() {
		if (this.graphic == null) {
			System.out.println("The image is null \n");
			return null;
		}

		return this.graphic;
	}

	public String getTitle() {
		return (this.title);
	}

	public String getHorizontalAxis() {
		if (this.horizontalAxis == null) {
			return ("The Horizontal Axis is null");
		} else {
			return (this.horizontalAxis);
		}
	}

	public String getVerticalAxis() {
		if (this.verticalAxis == null) {
			return ("The Vertical Axis is null");
		} else {
			return this.verticalAxis;
		}

	}

	public String getInfo() {
		String info = "\n" + "Title: " + "'" + getTitle() + "'" + "\n" +
			"Vertical Axis: " + "'" + getVerticalAxis() + "'" + "\n" +
			"Horizontal Axis: " + "'" + getHorizontalAxis() + "'" + "\n";
		return info;
	}

	public boolean hasTitle() {
		if (this.title == null || this.title == "")
			return false;
		else
			return true;
	}

	static ColorImage transparent(ColorImage img) {
		final Color Black = new Color(0, 0, 0);

		if (img == null)
			throw new IllegalArgumentException("The Graphic is null");

		for (int i = 0; i<img.getWidth(); i++)
			for (int j = 0; j<img.getHeight(); j++)
				if (((i % 2 == 0 || i == 0) && (j % 2 != 0)) || ((i % 2 != 0) && (j % 2 == 0 || j == 0)))
					img.setColor(i, j, Black);

		return img;
	}

}
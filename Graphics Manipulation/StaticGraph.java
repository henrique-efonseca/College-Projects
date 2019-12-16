/**
 * Static Class with procedures and functions for creating and manipulating 
 * color graphics (ColorImage) representative of graphics.
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

public class StaticGraph {

	

	// Returns the max value of an integer vector. 	
	private static int max(int[] vector) {

		if (vector == null)
			throw new IllegalStateException("The vector has no maximum.");

		for (int i = 0; i<vector.length; i++) {
			if (vector[i]<0)
				throw new IllegalStateException("The vector can't have negative values.");
		}

		int maximum = 0;

		for (int i = 0; i<vector.length; i++) {
			if (vector[i] > maximum)
				maximum = vector[i];
		}

		return maximum;

	}

	// Creates a 2D Column.
	private static ColorImage createColumn(int length, int height, int space, ColorImage column, Color color) {

		for (int i = column.getHeight() - 1; i > column.getHeight() - height - 1; i--)
			for (int j = space; j<length + space; j++)
				column.setColor(j, i, color);

		return column;

	}

	// Creates a 2D Graphic (with columns) using the previous function.
	static ColorImage createGraphic(int[] vector, int space, int length, Color color) {
		if (vector == null)
			throw new NullPointerException("The vector can't be null.");
		if (color == null)
			throw new NullPointerException("The color can't be null.");

		if (length<0)
			throw new IllegalArgumentException("The column's length can´t be negative.");
		if (space<0)
			throw new IllegalArgumentException("The space between column's can´t be negative.");

		for (int i = 0; i<vector.length; i++) {
			if (vector[i]<0)
				throw new IllegalStateException("The vector can't have negative values.");
		}

		int width = (space * (vector.length + 1) + (vector.length * length));
		int height = max(vector) + space;

		ColorImage graphic = new ColorImage(width, height);

		for (int i = space, j = 0; i<= (graphic.getWidth() - length - space) & j<vector.length; i = i + length + space, j++)
			graphic = createColumn(length, vector[j], i, graphic, color);

		return graphic;
	}

	// Auxiliary function to apply a gradient color to a 2D Column Graphic.
	private static Color gradient(Color color, int pixels, int level) {
		if (color == null)
			throw new NullPointerException("The color can't be null.");

		int R = color.getR() / (pixels + 1);
		int G = color.getG() / (pixels + 1);
		int B = color.getB() / (pixels + 1);

		Color gradient = new Color(R * level, G * level, B * level);
		return gradient;
	}

	// Creates a 2D Column with the gradient applied (uses the previous function.
	private static ColorImage columnGradient(int length, int height, ColorImage column, int startX, Color colour, int pixels) {

		for (int h = pixels, f = 0, g = 1; h >= 0; h--, f++, g++)
			for (int i = column.getHeight() - 1; i > column.getHeight() - height + f; i--)
				for (int j = startX + f; j<length + startX - f; j++)
					column.setColor(j, i, gradient(colour, pixels, g));

		return column;

	}
	
	//Creates a 2D Column Graphic (using the previous function).
	static ColorImage gradientGraphic(int[] vector, int space, int length, Color color, int pixels) {
		if (color == null) {
			throw new NullPointerException("The color can't be null.");
		}
		if (pixels == 0) {
			System.out.println("It wasn't apllied any gradient to the graphic.");
			return createGraphic(vector, space, length, color);
		}

		if (vector == null) {
			throw new NullPointerException("The color can't be null.");
		}

		for (int i = 0; i<vector.length; i++) {
			if (vector[i]<0) {
				throw new IllegalStateException("The vector can't have negative values.");
			}
			if (pixels >= vector[i]) {
				throw new IllegalArgumentException("The gradient size must be smaller than every vector's element.");
			}
		}

		if (pixels > length) {
			throw new IllegalStateException("The gradient size can't be bigger than the column's length.");
		}
		if (length<0) {
			throw new IllegalArgumentException("The column's length can´t be negative.");
		}
		if (space<= 0) {
			throw new IllegalArgumentException("The space between columns can´t be negative or equal to 0.");
		} else {
			int width = (space * (vector.length + 1) + (vector.length * length));
			int height = max(vector) + space;

			ColorImage graphic = new ColorImage(width, height);

			for (int i = space, j = 0; i<= (graphic.getWidth() - length - space) & j<vector.length; i = i + length + space, j++)
				graphic = columnGradient(length, vector[j], graphic, i, color, pixels);

			return graphic;

		}

	}

	// Creates a 2D Circle.
	private static ColorImage plot(int a, int b, int radius, ColorImage plot, Color colour) {

		for (int x = a - radius; x<= a + radius; x++)
			for (int y = b - radius; y<= b + radius; y++)
				if (((x - a) * (x - a)) + ((y - b) * (y - b))<= ((radius) * (radius)))
					plot.setColor(x, y, colour);

		return plot;

	}
	

	// Creates a 2D Scatter Plot Graphic using the previous function.
	static ColorImage scatterPlot(int[] vector, int space, int radius, Color color) {
		if (vector == null)
			throw new NullPointerException("The vector can't be null.");

		if (color == null)
			throw new NullPointerException("The color can't be null.");

		for (int i = 0; i<vector.length; i++) {
			if (vector[i]<0)
				throw new IllegalStateException("The vector can't have negative values.");
			if (radius >= vector[i])
				throw new IllegalArgumentException("The radius can't be bigger than any value of the vector.");
		}

		if (radius<= 0)
			throw new IllegalArgumentException("The radius of the circle can't be negative or equal to 0.");
		if (space<= 0)
			throw new IllegalArgumentException("The space between columns can´t be negative or equal to 0.");

		int width = (space * (vector.length + 1) + (vector.length * (2 * radius)));
		int height = max(vector) + (radius) + space;

		ColorImage graphic = new ColorImage(width, height);

		for (int i = space + radius, j = 0; i<= (graphic.getWidth() + space) - (2 * radius) + space - 1 & j<vector.length; i = i + (2 * radius) + space, j++)
			graphic = plot(i, (graphic.getHeight() - vector[j]), radius, graphic, color);

		return graphic;
	}

	//Rotates an image.
	static ColorImage rotate(ColorImage img) {

		if (img == null)
			throw new NullPointerException("The image can't be null.");

		ColorImage newImg = new ColorImage(img.getHeight(), img.getWidth());

		for (int i = 0; i<img.getWidth(); i++)
			for (int j = 0; j<img.getHeight(); j++) {

				newImg.setColor(img.getHeight() - j - 1, i, (img.getColor(i, j)));
			}

		return newImg;
	}

}
# Graphics Manipulation

This is a an individual ___Java Project___ that I had to do to my "**Introduction to Progamming**" Class.
It was given to every student the same [Project Statement](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/enunciadoProjeto1920.pdf) and we had to do what asked in that statement. It was also given three "default" classes: [ColorImage](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/ColorImage.java), [Color](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/Color.java) and [ImageUtil](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/ImageUtil.java) .


Briefly the project has 3 Parts and I had to create java classes to create and edit/manipulate 2D Graphics.

**Grade: A** (scale A-D)

<br>

## Part 1

**Objective**: Develop a [Static Class](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/StaticGraph.java) with procedures and functions for creating and manipulating color images (ColorImage) representative of graphics.

Function of the class:

```java
public class StaticGraph {
    
    // Creates a 2D Graphic (with columns) using the previous function.
    public static ColorImage createGraphic(int[] vector, int space, int length, Color color) 
    
    //Creates a 2D Column Graphic (using the previous function).
    public static ColorImage gradientGraphic(int[] vector, int space, int length, Color color, int pixels) 
    
    // Creates a 2D Circle.
    public static ColorImage plot(int a, int b, int radius, ColorImage plot, Color colour) 
   
    //Creates a 2D Scatter Plot Graphic using the previous function.
    public static ColorImage scatterPlot(int[] vector, int space, int radius, Color color) 
    
    //Rotates an image.
    public static ColorImage rotate(ColorImage img) 
    
}
```

<br>

## Part 2

**Objective**: Develop a Class of Objects that represents the concept of graphic.

After the creation of an object of [this class](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/ObjectsGraph.java) it should be possible to: add/modify the Graphic's Title; add/modify the Graphic's Horizontal Axis; add/modify the Graphic's Vertical Axis; set the graphic to transparent on Even-column and odd-line pixels or Odd column and even line; get all the information associated to the graphic.

Function of the class:

```java
public class ObjectsGraph {
    
    // Constructor.
    public ObjectsGraph(ColorImage graphic, String title, String horizontalAxis, String verticalAxi) {
    
   // Adds a Title to the Graphic
	public void setTitle(String title)
    
    // Sets the Graphic Horizontal Axis Title
	public void setHorizontalAxis(String horizontalAxis) 
    
    // Sets the Graphic Vertical Axis Title
	public void setVerticalAxis(String verticalAxis) {
    
    // Sets the Graphic to Transparent
	public void setTransparent() 
    
    // Rotates the Graphic
	public ObjectsGraph rotate() 
    
    // Returns the Graphic Height
    public int getHeight() 
    
    // Returns the Graphic Width
    public int getWidth() {
    
    // Returns the Graphic image (instanceof ColorImage)
    public ColorImage getImg() {
    
    // Returns the Graphic Title
    public String getTitle() {
    
    // Returns the Graphic Vertical Axis Name
    public String getVerticalAxis() {
    
    // Returns the Graphic Horizontal Axis Name
    public String getHorizontalAxis() 
    
    // Returns a String with the Graphic's Title, Horizontal Axis and Vertical Axis
    public String getInfo() {
    
    // Boolean to check if the Graphic has a title
    public boolean hasTitle() {
    
    // Set the Graphic image transparent
    static ColorImage transparent(ColorImage img) {
    
}
```

## Part 3

**Objective**: Develop a class of objects that allow overlapping graphics.

After the creation of an object of [this class](https://github.com/henrique-efonseca/College-Projects/blob/master/Graphics%20Manipulation/OverlapGraph.java) it should be possible to: store a stack of graphics; add a graphic to the top of the stack; remove a graphic from the top of the stack; add a graphic to an index of the stack; given two indexs swap position two graphics of the stack; get every graphic without title; order alphabetically every graphic of the stack; get the resulting image from the overlap of every graphic from the stack; rotate 90º degrees the previous image.








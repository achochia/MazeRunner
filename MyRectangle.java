public class MyRectangle
{
    protected int height;
    protected int width;
    protected int x;
    protected int y;

    public MyRectangle(int xValue, int yValue, int w, int h)
    /**
     * Pre: N/A
     * Purpose: Constructor for the class
     * Params: integers: xValue, yValue, width, height
     * Post: N/A
     */
    {
        x = xValue;
        y = yValue;
        width = w;
        height = h;
    }

    public double getArea ()
    /**
     * Pre: N/A
     * Purpose: Returns the area of the rectangle
     * Post: Return (double) area
     */
    {
        return height * width;
    }

    public boolean containsPoint (int xValue, int yValue)
    /**
     * Pre: N/A
     * Params: Position of the point
     * Purpose: To calculate if the point is inside the rectangle or not
     * Post: Returns (boolean) if point is inside or not
     */
    {
        return xValue >= x && xValue <= x + width && yValue >= y
                && yValue <= y + height;
    }

    public boolean containsRectangle (MyRectangle rect)
    /**
     * Pre: N/A
     * Params: A rectangle
     * Purpose: To detect if the rectangles are overlapping
     * Post: Returns (boolean) if overlapping or not
     */
    {
        if (x - 25 <= rect.x + rect.width && x + width + 25 >= rect.x
                && y - 25 <= rect.y + rect.height && y + height + 25 >= rect.y)
            return true;
        return false;
    }
}

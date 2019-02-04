package academits.mainShapes.shapes.classes;

import java.lang.RuntimeException;

public class Rectangle implements Shape {
    private double sideLength;
    private double sideWidth;

    public Rectangle(double sideLength, double sideWidth) {
        this.sideLength = sideLength;
        this.sideWidth = sideWidth;
    }


    public double getWidth() {
        return sideWidth;
    }

    public double getHeight() {
        return sideLength;
    }

    public double getArea() {
        try {
            return getWidth() * getHeight();
        } catch ( NullPointerException e ) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    public double getPerimeter() {
        try {
            return (sideLength + sideWidth) * 2;
        } catch ( NullPointerException e ) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("class: Rectangle; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int hash = 1;
        hash = prime * hash + Double.hashCode(sideLength);
        hash = prime * hash + Double.hashCode(sideWidth);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        try {
            if (object == this) {
                return true;
            }

            if (object.getClass() != this.getClass()) {
                return false;
            }
            Rectangle rectangle = (Rectangle) object;

            return (this.sideLength == rectangle.sideLength && this.sideWidth == rectangle.sideWidth);

        } catch ( RuntimeException e ) {
            e.getMessage();
            return false;
        }
    }
}


package academit.shapes.classes;

import java.lang.RuntimeException;

public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }

    public double getArea() {
        try {
            return Math.PI * radius * radius;
        } catch ( NullPointerException e ) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    public double getPerimeter() {
        try {
            return Math.PI * radius * 2;
        } catch ( NullPointerException e ) {
            System.out.println("Множество фигур не должно быть пустым! ");
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("class: Circle; Width: %.2f, Height: %.2f, Area: %.2f, Perimeter: %.2f, %n", getWidth(), getHeight(), getArea(), getPerimeter());
    }

    @Override
    public int hashCode() {
        final int prime = 7;
        int hash = 1;
        hash = prime * hash + Double.hashCode(radius);
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
            Circle circle = (Circle) object;

            return (this.radius == circle.radius);

        } catch ( RuntimeException e ) {
            e.getMessage();
            return false;
        }
    }
}



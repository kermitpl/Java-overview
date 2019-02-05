package com.amazurek.figurecalculator;

public class Circle extends Figure implements IPrint {
    private double radius;

    public Circle(double radius) throws Exception {
        if(radiusCorrectness(radius)){
            this.radius = radius;
        } else throw new Exception("Incorrect radius value");
    }

    public void changeRadius(double newRadius) {
        this.radius = newRadius;
    }

    @Override
    double calculatePerimeter() {
        return Math.PI*2*radius;
    }

    @Override
    double calculateArea() {
        return Math.PI*radius*radius;
    }

    @Override
    public void print() {
        System.out.println("Circle radius: "+radius+"; Area: "+calculateArea()+"; Perimeter: "+calculatePerimeter());
    }

    public boolean radiusCorrectness(double radius){
        if (radius>0) {
            return true;
        } else return false;
    }
}
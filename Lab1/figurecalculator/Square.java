package com.amazurek.figurecalculator;

public class Square extends Figure implements IPrint {
    private double side;

    public Square(double side) throws Exception {
        if(SideCorrectness(side)){
            this.side = side;
        } else throw new Exception("Incorrect side value");
    }

    public void changeSide(double newSide) {
        this.side = newSide;
    }

    @Override
    double calculatePerimeter() {
        return side*4;
    }

    @Override
    double calculateArea() {
        return side*side;
    }

    @Override
    public void print() {
        System.out.println("Square side: "+side+"; Area: "+calculateArea()+"; Perimeter: "+calculatePerimeter());
    }

    public boolean SideCorrectness(double side){
        if (side>0) {
            return true;
        } else return false;
    }

}


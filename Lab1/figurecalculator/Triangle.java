package com.amazurek.figurecalculator;

import java.util.Arrays;

public class Triangle extends Figure implements IPrint {
    private double[] sides;

    public Triangle(double... sides) throws Exception {
        if(SidesCorrectness(sides)){
            this.sides = sides;
        } else throw new Exception("Incorrect sides values");
    }

    public void changeSides(double... newSides) {
        this.sides = newSides;
    }

    @Override
    double calculatePerimeter() {
        return sides[0]+sides[1]+sides[2];
    }

    @Override
    double calculateArea() {
        double x = calculatePerimeter()/2;
        return Math.sqrt(x*(x-sides[0])*(x-sides[1])*(x-sides[2]));
    }

    @Override
    public void print() {
        System.out.println("Triangle sides: "+ Arrays.toString(sides)+"; Area: "+calculateArea()+"; Perimeter: "+calculatePerimeter());
    }

    public boolean SidesCorrectness(double... sides){
        if(sides.length==3){
            for(int i=0; i<3; i++){
                if(!(sides[i]<sides[(i+1)%3]+sides[(i+2)%3])) return false;
            }
            for(int j=0; j<3; j++) {
                if(sides[j]<0) return false;
            }
            return true;
        }
        return false;
    }
}

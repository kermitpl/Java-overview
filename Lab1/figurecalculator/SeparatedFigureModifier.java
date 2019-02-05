package com.amazurek.figurecalculator;

import java.util.Scanner;

public class SeparatedFigureModifier {
    public static Circle createNewCircle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide radius value:");

        while(true) {
            try {
                return new Circle(scanner.nextDouble());
            } catch (Exception e) {
                System.out.println("Incorrect radius value! Provide correct one: ");
            }
        }
    }

    public static Triangle createNewTriangle(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide 3 sides values (separated by 'space' or 'enter'):");

        while(true) {
            try {
                return new Triangle(scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
            } catch (Exception e) {
                System.out.println("Incorrect sides values! Provide correct ones: ");
            }
        }
    }

    public static Square createNewSquare(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide side value:");

        while(true) {
            try {
                return new Square(scanner.nextDouble());
            } catch (Exception e) {
                System.out.println("Incorrect side value! Provide correct one: ");
            }
        }
    }

    public static void changeFigureData(IPrint figure){
        Scanner scanner = new Scanner(System.in);

        if (figure instanceof Triangle){
            System.out.println("Provide 3 changed sides values: ");
            double a=scanner.nextDouble(), b=scanner.nextDouble(), c=scanner.nextDouble();

            if (((Triangle) figure).SidesCorrectness(a,b,c)) ((Triangle) figure).changeSides(a,b,c);
            else System.out.println("Incorrect sides values! Returning to main menu. ");
        } else if (figure instanceof Circle) {
            System.out.println("Provide changed radius value: ");
            double d=scanner.nextDouble();

            if (((Circle) figure).radiusCorrectness(d)) ((Circle) figure).changeRadius(d);
            else System.out.println("Incorrect radius value! Returning to main menu. ");
        } else if (figure instanceof Square) {
            System.out.println("Provide changed side value: ");
            double e=scanner.nextDouble();

            if (((Square) figure).SideCorrectness(e)) ((Square) figure).changeSide(e);
            else System.out.println("Incorrect side value! Returning to main menu. ");
        }

    }
}

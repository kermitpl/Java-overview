package com.amazurek.figurecalculator;

import java.util.Scanner;

public class Menu {
    private static IPrint figure;

    private static void printMenuOptions(){
        if(figure!=null){
            System.out.println("Available options: ");
            System.out.println(" |1| - change figure");
            System.out.println(" |2| - change data");
            System.out.println(" |3| - print figure");
            System.out.println(" |4| - quit");
        } else {
            System.out.println("Available options: ");
            System.out.println(" |1| - choose figure");
            System.out.println(" |4| - quit");
        }
    }

    public static void showMenu(){
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while(quit==false){
            printMenuOptions();

            switch (scanner.nextInt()){
                case 1:
                    chooseFigure();
                    break;
                case 2:
                    changeData();
                    break;
                case 3:
                    printFigure();
                    break;
                case 4:
                    quit=true;
                    break;
                default:
                    System.out.println("Wrong choice! No corresponding function available!");
            }
        }
    }

    private static void printFigure() {
        if(figure==null) System.out.println("You need to create figure first!");
        else figure.print();
    }

    private static void changeData() {
        if(figure==null) System.out.println("You need to create figure first!");
        else SeparatedFigureModifier.changeFigureData(figure);
    }

    private static void chooseFigure() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a figure: ");
        System.out.println(" |1| - Square");
        System.out.println(" |2| - Triangle");
        System.out.println(" |3| - Circle");
        System.out.println(" |Other number| - Quit this function");

        switch (scanner.nextInt()){
            case 1:
                figure = SeparatedFigureModifier.createNewSquare();
                break;
            case 2:
                figure = SeparatedFigureModifier.createNewTriangle();
                break;
            case 3:
                figure = SeparatedFigureModifier.createNewCircle();
                break;
            default:
                System.out.println("Quitting this function!");
        }
    }

}

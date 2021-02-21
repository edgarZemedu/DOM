package throwsException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LibreriaDAM {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        ArrayList<String> misOpciones = new ArrayList<String>() {
            {
                add("Opción 1");
                add("Opción 2");
                add("Opción 3");
            }
        };
        Menu menu = new Menu(misOpciones);
        do{
            menu.printMenu();
            menu.rango(sc);
        }while(!menu.rango(sc));

        System.out.println("Todo bien ******");




    }
}
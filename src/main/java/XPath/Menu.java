package XPath;

import dom_Alumnos.ControlData;

import java.util.Scanner;

public class Menu {

    public static int menu(Scanner sc){
        int opcion;
        boolean correcto = true;
        do{
            System.out.println("\n********* MENU PRINCIPAL XPATH **********" +
                    "\n Del fichero seleionado que quiere hacer:" +
                    "\n 1.- Mostrar el contenido del Fichero" +
                    "\n 2.- Ejectar consultas Xpath y contruir el DOM" +
                    "\n 3.- Mostrar el resultado en formato XML" +
                    "\n 4.- Exit");
            opcion = ControlData.lerInt(sc);
            if (opcion>4 && opcion<1){
                correcto = false;
                System.out.println("Ese valor no exite en el menú **********");
            }
        }while (!correcto);

        return opcion;
    }

}

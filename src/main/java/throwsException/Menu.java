package throwsException;

import dom_Alumnos.ControlData;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<String> opciones = new ArrayList<String>();
    private int numOpciones;

    public Menu(ArrayList opciones) {
        this.opciones = opciones;
        this.numOpciones = opciones.size();
    }

    /**
     * Imprime o menú na pantalla
     */
    public void printMenu() {
        for (int i = 1; i <= opciones.size(); i++) {
            System.out.println(i + ".- " + opciones.get(i - 1));
        }
    }

    /**
     * Lee un n´º por teclado ata que se introduce un nº correcto para o
     * rango establecido polas opcións do menú
     * @param sc Scanner - Instancia de Scanner para ler
     */
    public boolean rango(Scanner sc) {
        boolean enrango = true;
        byte op = ControlData.lerByte(sc);

            if (op < 1 || op > numOpciones) {
                enrango = false;

                try {
                    throw new MisExcepciones(1,numOpciones);
                }
                catch (MisExcepciones e) {
                    System.out.println("-> "+e.getMessage());
                    e.printStackTrace();
                }
                /*
                System.out.println("\tERRO: debe introducir un valor dentro do rango de números posibles. "
                        + "\n\t\tVolva a introducir un número: \n");
                 */
            }

        return enrango;
    }

}

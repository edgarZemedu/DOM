package dom_Alumnos;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;

public class ManejoFicheros {
    static ArrayList<Alumno> listAlumnos = new ArrayList<>();
    static Alumno a = null;

    //    añadir datos para empezar el programa
    static void añadirDatos() {

        Alumno a = new Alumno("DAM", "Rocio", "Gracia Márquez", 31);
        listAlumnos.add(0,a);
        a = new Alumno("DAM", "Carlos", "Juanito Paco", 28);
        listAlumnos.add(1,a);
        a = new Alumno("DAW", "Antonio", "Garcia", 35);
        listAlumnos.add(2,a);
        a = new Alumno("ASIR", "Rocio", "Marcos", 35);
        listAlumnos.add(3,a);
        a = new Alumno("DAM", "Cris", "Marcote", 33);
        listAlumnos.add(4,a);
        a = new Alumno("DAW", "Jose Luis", "Juanito", 37);
        listAlumnos.add(5,a);
        a = new Alumno("ASIR", "Ana", "Juanito", 45);
        listAlumnos.add(6,a);

       for (int i=0; i < listAlumnos.size() ; i++){
           //listAlumnos.add(a);

//            System.out.println(listAlumnos.get(i).getCurso()+" "+listAlumnos.get(i).getNombre()+""+
//                    " "+listAlumnos.get(i).getApellidos()+" "+listAlumnos.get(i).getEdad());
       }
    }

    /**
     * Crear un fichero serializable con objetos de tipo Alumno y devuelve
     * una instancia File del mismo
     */
    public static File crearFS(String nombreFichero) {

        //Se non existe, créao. Se existe sobreescribe o seu contido previo.
        File fichero = new File(nombreFichero);
        if (fichero.exists()){
            System.out.println("Ya existe el fichero!!!!");
        }else {
            //Creamos un fluxo de saída: aplicación -> ficheiro
            try (FileOutputStream miFOS = new FileOutputStream(fichero);
                 ObjectOutputStream miOOS = new ObjectOutputStream(miFOS)) {
                añadirDatos();
                miOOS.writeObject(listAlumnos);
                miOOS.flush();
                System.out.println("------> Fichero creado");
            } catch (IOException e) {
                System.out.println("Se ha producido un error 1 " + e.toString());
            }
        }
        return fichero;
    }

    /**
     * Modificar un fichero serializable con objetos de tipo alumno
     */
    static void sobrescribirFS(String nombreFichero) {

        //Se non existe, créao. Se existe sobreescribe o seu contido previo.
        File ficheiro = new File(nombreFichero);
        //Creamos un fluxo de saída: aplicación -> ficheiro
        try (FileOutputStream miFOS = new FileOutputStream(ficheiro, true);
             MiObjetoOutputStream miOOS = new MiObjetoOutputStream(miFOS)) {
            //Almaceno os obxectos Persoa no ficheiro
                añadirDatos();
                miOOS.writeObject(listAlumnos);

        } catch (IOException e) {
            System.out.println("Se ha producido un error 2" + e.toString());
        }

    }

    /**
     * Mostrar por pantalla un fichero serializable con objetos de tipo Alumno
     */
    static void leerFS(String nombreFichero) {
        //Declaramos o ficheiro.Se non existe, créao. Se existe sobreescribe.
        String path = nombreFichero+".dat"; //"C:\\Users\\I B M\\Documents\\zemedu\\Dom_01\\"
        File ficheiro = new File(path);
        if (ficheiro.exists()) {
            System.out.println("Fichero existe");

            FileInputStream miFIS = null;
            ObjectInputStream miOIS = null;
            try { miFIS = new FileInputStream(ficheiro);
                  miOIS = new ObjectInputStream(miFIS);
                //Bucle para ler do ficheiro ata que se produza unha excepcion EOFException
                while (true) {
                    a = (Alumno) miOIS.readObject();
                    //System.out.println(miOIS.readObject().getClass().getCanonicalName());
                    //Visualizamos contido do ficheiro
                    System.out.println("Nombre: " + a.getNombre() + " Apellidos: " + a.getApellidos() + " Edad: " + a.getEdad() + " Curso: " + a.getCurso());
                }
            } catch (EOFException e) {
                System.out.println("Fin de fichero 2* " + e.toString());
            } catch (ClassNotFoundException | FileNotFoundException e) {
                System.out.println("Se ha producido un error 3 " + e.toString());
            } catch (IOException e) {
                System.out.println("Se ha producido un error 4 " + e.toString());
            } finally {
                try {
                    if (miFIS != null) {
                        miOIS.close();
                    }
                    if (miOIS != null) {
                        miOIS.close();
                    }
                } catch (IOException e) {
                    System.out.println("Se ha producido un error 4 "+e.getMessage());
                }
            }
        }else{
            System.out.println("Fichero con ese nombre no encontrado ******");
        }
    }
}

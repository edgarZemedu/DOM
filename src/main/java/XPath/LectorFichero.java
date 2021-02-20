package XPath;

import java.io.File;
import java.io.FilenameFilter;

public class LectorFichero {

    static File[] listarFicheros(String path, String...extension){

        File[] listaFicheros = new File[0];

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                boolean filtrado = false;
                int contador = 0;
                while(contador < extension.length && !filtrado){
                    if (name.toLowerCase().endsWith(extension[contador]))
                        filtrado = true;
                    contador++;
                }
                return filtrado;
            }
        };
        File fichero = new File(path);
        if (fichero.exists()) {
            listaFicheros = fichero.listFiles(filter);
        }

        return listaFicheros;
    }

}

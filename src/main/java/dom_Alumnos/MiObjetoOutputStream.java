package dom_Alumnos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjetoOutputStream  extends ObjectOutputStream {

    /**
     * Constructores
     */

    public MiObjetoOutputStream() throws IOException {

    }

    public MiObjetoOutputStream(OutputStream out) throws IOException, SecurityException{
        super(out);
    }

    /**
     * Redefinición del método de escribir la cabecera para que no haga nada.
     */
    @Override
    protected void writeStreamHeader()throws IOException{
        //No se hace naca
    }

}

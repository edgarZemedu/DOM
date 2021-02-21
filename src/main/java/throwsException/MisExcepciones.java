package throwsException;

public class MisExcepciones extends Exception {

    public MisExcepciones(int valorInicial, int valorFinal){
        super("\nEl número está fuera de rango. " +
            "\nIntroduce un valor que esté entre "+valorInicial+" y "+valorFinal+": ");
    }
}

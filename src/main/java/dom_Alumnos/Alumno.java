package dom_Alumnos;

import java.io.Serializable;
import java.lang.invoke.StringConcatFactory;

public class Alumno implements Serializable {
    //private static final long serialVersionUID = -9223365651070458532L;
    //private static final long serialVersionUID = 6529685098267757690L;
    private String nombre,apellidos,curso;
    private int edad;

    public Alumno() {
    }
    public Alumno(String curso,String nombre,String apellidos,int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curso = curso;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}

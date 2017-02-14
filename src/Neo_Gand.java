/**
 * Created by Gand on 14/02/17.
 */
class Direccion{
    private String calle;
    private int numero;
    private String localidad;
    public Direccion (String calle, int numero, String localidad){
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
    }

    public String toString(){
        return calle+", "+numero+", "+localidad;
    }
}//Fin Direccion

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

class Persona {
    private String nombre;
    private String apellidos;
    private int edad;
    private Direccion dir;
    public Persona(String nombre, String apellidos, int edad){
        this.nombre = nombre;
        this.apellidos = apellidos;
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
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Direccion getDir() {
        return dir;
    }
    public void setDir(Direccion dir) {
        this.dir = dir;
    }
}//Fin Persona

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public class Neo_Gand {



}//Fin Neo_Gand

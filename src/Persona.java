/**
 * Created by Gand on 14/02/17.
 */
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

    public Persona(String nombre, String apellidos, int edad, Direccion dir) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.dir = dir;
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

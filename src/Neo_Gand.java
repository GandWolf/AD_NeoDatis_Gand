import java.math.BigInteger;
import java.util.*;

import org.neodatis.odb.*;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Neo_Gand {
    static ArrayList<Direccion> direcciones = new ArrayList<>();
    static ArrayList<Persona> personas = new ArrayList<>();

    static Scanner teclado;
    static ODB odb;

    public static void main(String[] args) {
        System.out.println("---NEODATIS GAND---");

        odb = ODBFactory.open("BDPersonas");
        menu();
        odb.close();
    }

    private static void menu() {
        teclado = new Scanner(System.in);

        System.out.println("\n" +
                "Selecciona una opcion:\n" +
                "1) Insertar datos en las dos tablas\n" +
                "2) Modificar la direccion de la persona con OID 4\n" +
                "3) Obtener las personas cuya edad es menor a 14 y nombre empiece por “A”\n" +
                "4) Comprobar si el atributo apellidos es nulo\n" +
                "5) Numero de personas registradas en la base de datos\n" +
                "6) Cuantas personas hay de la edad de 18 años\n" +
                "7) Datos de las persona de mayor edad\n" +
                "8) Media de edad de las personas\n" +
                "9) Datos de las personas que viven en la localidad de Almeria\n" +
                "10) Datos de las personas que viven en cada dirección\n" +
                "11) Cuántas personas viven en cada calle. \n" +
                "12) Cuántas personas no tienen calle o localidad.\n" +
                "0) Salir.");

        int e = teclado.nextInt();

        switch (e){
            case 0:
                System.out.println("-- FINALIZANDO NEO-GAND --");
                break;
            case 1: ejer1();
                menu();
                break;
            case 2: ejer2();
                menu();
                break;
            case 3: ejer3();
                menu();
                break;
            case 4: ejer4();
                menu();
                break;
            case 5: ejer5();
                menu();
                break;
            case 6: ejer6();
                menu();
                break;
            case 7: ejer7();
                menu();
                break;
            case 8: ejer8();
                menu();
                break;
            case 9: ejer9();
                menu();
                break;
            case 10: ejer10();
                menu();
                break;
            case 11: ejer11();
                menu();
                break;
            case 12: ejer12();
                menu();
                break;
            default:
                System.out.println("Debe seleccionar un ejercicio del 1 al 12 o 0 para salir.");
        }
    }

    static void ejer1(){
        //Insertar datos
        System.out.println("Insertando datos...");
        direcciones.add(new Direccion("Abeto", 1, "Almeria"));
        direcciones.add(new Direccion("Boldo", 2, "Granada"));
        direcciones.add(new Direccion("Castaño", 3, "Cordoba"));
        direcciones.add(new Direccion("Duraznero", 4, "Malaga"));
        direcciones.add(new Direccion("Eucalipto", 5, "Cadiz"));

        personas.add(new Persona("Ana", "Alvarez", 11, direcciones.get(0)));
        personas.add(new Persona("Beatriz", "Buendía", 12, direcciones.get(0)));
        personas.add(new Persona("Carlos", "Cruz", 22, direcciones.get(1)));
        personas.add(new Persona("David", "Díaz", 23, direcciones.get(1)));
        personas.add(new Persona("Ernesto", "Esteban", 33, direcciones.get(2)));
        personas.add(new Persona("Fernando", "Fernandez", 34, direcciones.get(2)));
        personas.add(new Persona("Gustavo", "Gutierrez", 44, direcciones.get(3)));
        personas.add(new Persona("Juan", "Jimeno", 45, direcciones.get(3)));
        personas.add(new Persona("Luis", "Lopez", 55, direcciones.get(4)));
        personas.add(new Persona("Maria", "Marquez", 56, direcciones.get(4)));

        for (Direccion d : direcciones){
            odb.store(d);
        }
        for (Persona p: personas){
            odb.store(p);
        }

        Objects<Persona> objects = odb.getObjects(Persona.class);
        System.out.println("\n" + objects.size() + " Personas: ");
        int i = 1;
        while (objects.hasNext()){
            Persona p = objects.next();
            System.out.println((i++) + "\t: " + p.getNombre() + " " + p.getApellidos() + ", " + p.getEdad()
            + ", " + p.getDir().toString());
        }

        Objects<Direccion> objectsD = odb.getObjects(Direccion.class);
        System.out.println("\n"+ objectsD.size() + " Direcciones: ");
        int j = 1;
        while (objectsD.hasNext()){
            Direccion d = objectsD.next();
            System.out.println((j++) + "\t: " + d.getCalle() + " " + d.getNumero() + ", " + d.getLocalidad());
        }
    }//1

    private static void ejer2() {
        //Modificar direccion ID 4
        Objects<Persona> objects = odb.getObjects(Persona.class);

        for (Persona p : objects){
            Persona person = objects.next();
            OID oid = odb.getObjectId(p);
            if (oid.getObjectId() == 18 ){
                System.out.println("Modificando direccion...");
                Direccion dir = person.getDir();
                dir.setCalle("Calle Falsa");
                dir.setNumero(123);
                dir.setLocalidad("Lepe");
                odb.store(dir);
                odb.commit();
            }
        }
        System.out.println("Direccion actualizada correctamente.");
    }//2

    private static void ejer3() {
        //Datos personas < 14 años y nombre por A

        IQuery query = new CriteriaQuery(Persona.class, Where.and().add(Where.like("nombre", "A%")).add(Where.lt("edad", 14)));
        Objects<Persona> objects = odb.getObjects(query);
        System.out.println(objects.size() + "Personas: ");
        int i = 1;
        while (objects.hasNext()){
            Persona p = objects.next();
            System.out.println((i++) + "\t: " + p.getNombre() + " " + p.getApellidos() + ", " + p.getEdad()
                    + ", " + p.getDir().toString());
        }

    }//3

    private static void ejer4() {
        //Comprobar apellido nulo
        ICriterion criteio = Where.isNull("apellido");
        Objects<Persona> objects = odb.getObjects(new CriteriaQuery(Persona.class, criteio));

        if (objects.size() == 0 ){
            System.out.println("No hay campos nulos");
        }else {
            System.out.println("Hay " + objects.size() + " apellidos nulos");
        }
    }//4

    private static void ejer5() {
        //Número de personas registradas en la base de datos
        IQuery query = new CriteriaQuery(Persona.class);
        BigInteger numP = odb.count((CriteriaQuery) query);
        System.out.println("Número de personas: " + numP);
    }//5

    private static void ejer6() {
        //Personas de 16 años
        IQuery query = new CriteriaQuery(Persona.class, Where.equal("edad", 16));
        BigInteger numP = odb.count((CriteriaQuery) query);
        System.out.println("Hay " + numP + " peronas con 16 años");
    }//6

    private static void ejer7() {
        //Datos de las personas de mayor edad
        Values valores = odb.getValues(new ValuesCriteriaQuery(Persona.class).max("edad"));
        ObjectValues objectValues = valores.getFirst();
        BigInteger edadMax = (BigInteger) objectValues.getByAlias("edad");

        IQuery query = new CriteriaQuery(Persona.class, Where.equal("edad", edadMax));
        Objects<Persona> objects = odb.getObjects(query);
        System.out.println(objects.size() + "Personas: ");
        int i = 1;
        while (objects.hasNext()){
            Persona p = objects.next();
            System.out.println((i++) + "\t: " + p.getNombre() + " " + p.getApellidos() + ", " + p.getEdad()
                    + ", " + p.getDir().toString());
        }
    }//7

    private static void ejer8() {
        //Media de edad
        Values valores = odb.getValues(new ValuesCriteriaQuery(Persona.class).avg("edad"));
        ObjectValues media = valores.nextValues();
        BigInteger eMedia = (BigInteger) media.getByIndex(0);

        System.out.println("Edad media de las Personas: " + eMedia);
    }//8

    private static void ejer9() {
        //Personas que viven en Almeria
        Values valores = odb.getValues(new ValuesCriteriaQuery(Persona.class, Where.equal("dir.localidad", "Almeria"))
                .field("nombre").field("apellidos").field("dir.localidad", "Almeria"));
        System.out.println("\nPersonas que vivien en Almeria:");
        while (valores.hasNext()){
            ObjectValues ov = valores.next();
            System.out.println(ov.getByIndex(0) + " " + ov.getByIndex(1) + ", " + ov.getByIndex(2));
        }
    }//9

    private static void ejer10() {
        //Personas que viven en cada direccion
        Values valores = odb.getValues(new ValuesCriteriaQuery(Persona.class).field("nombre")
                .field("apellidos").groupBy("dir.localidad"));
        System.out.println("Personas que viven en cada localidad: ");
        while (valores.hasNext()){
            ObjectValues ov = valores.next();
            System.out.println(ov.getByIndex(0) + " " + ov.getByIndex(1) + ", " + ov.getByIndex(2));
        }
    }//10

    private static void ejer11() {
        //Número de personas en cada calle
        Values valores = odb.getValues(new ValuesCriteriaQuery(Persona.class).field("nombre")
                .field("apellidos").count("nombre").groupBy("dir.calle"));
        System.out.println("Personas que viven en cada localidad: ");
        while (valores.hasNext()){
            ObjectValues ov = valores.next();
            System.out.println(ov.getByIndex(0) + " " + ov.getByIndex(1) + ", " + ov.getByIndex(2));
        }
    }//11

    private static void ejer12() {
        //Cuántas personas no tienen calle o localidad.
        ICriterion criteio = Where.and().add(Where.isNull("dir.calle")).add(Where.isNull("dir.localidad"));
        Objects<Persona> objects = odb.getObjects(new CriteriaQuery(Persona.class, criteio));

        if (objects.size() == 0 ){
            System.out.println("No hay campos nulos");
        }else {
            System.out.println("Hay " + objects.size() + " calle o localidad nulos");
        }
    }//12

}//Fin Neo_Gand

































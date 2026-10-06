package es.daw.simpson.servicio;

import es.daw.simpson.clasico.ComparadorPorEdad;
import es.daw.simpson.model.Personaje;
import es.daw.simpson.model.PersonajeTradicional;
import es.daw.simpson.repository.PersonajeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Aquí vive TODA la lógica de los streams
 * El servlet no filtra ni ordena
 */
public class PersonajeServicio {

    // En Spring aprenderemos a usar inyección de dependencia y no usar new...
    private final PersonajeRepository repositorio = new PersonajeRepository();

    public List<Personaje> buscar(String lugar, //pendiente
                                  Integer edadMax,
                                  String ordenarPor, //pendiente
                                  boolean descendente, //pendiente
                                  Integer limite,
                                  String ocupacion,
                                  boolean principal
                                  ) {
        //------------------REPASO DE PRIMERO-----------------
        //------------Trasteando con ordenaciones de un ArrayList-----------
//        List<Personaje> personajes = repositorio.findAll();
//        List<PersonajeTradicional> personajesTrad = new ArrayList<>();
//        personajesTrad.add(new PersonajeTradicional("Homer",    "Simpson",    39, "Inspector de seguridad", "Central Nuclear",   true));
//        personajesTrad.add(new PersonajeTradicional("Marge",    "Simpson",    36, "Ama de casa",            "Casa Simpson",      true));
//        personajesTrad.add(new PersonajeTradicional("Bart",     "Simpson",    10, "Estudiante",             "Escuela Primaria",  true));
//
//        Collections.sort(personajesTrad);
//        //personajesTrad.sort(Comparator.naturalOrder());
//        System.out.println("*** personajesTrad CON ORDENACION NATURAL: " + personajesTrad);
//
//        personajesTrad.sort(new ComparadorPorEdad());
//        System.out.println("*** personajesTrad CON EDAD: " + personajesTrad);
        //--------------------------------------------------------------------
        return repositorio.findAll().stream()
                //filter() deja pasar solo los que cumplan la condicion
                .filter( p -> lugar == null || lugar.isBlank() || p.lugar().equalsIgnoreCase(lugar))
                .filter( p -> edadMax == null || p.edad() <= edadMax)
                .filter( p -> ocupacion == null || ocupacion.isBlank() || p.ocupacion().equalsIgnoreCase(ocupacion))
                .filter( p -> !principal || p.principal())
                //.sorted( (p1, p2) -> p1.nombre() .compareTo(p2.nombre()) ) / .sorted(Comparator.comparing(Personaje::nombre))
                .sorted(crearComparador(ordenarPor,descendente))
                .limit(limite == null? Integer.MAX_VALUE: limite)
                .toList();
    }

    public List<String> lugaresDisponibles(){
        //Forma1: funcional
        return repositorio.findAll().stream()
                //.map( p -> p.lugar()) es lo mismo que .map(Personaje::lugar)
                .map(Personaje::lugar)
                .distinct()
                .sorted()
                .toList();
    }
    public List<String> ocupacionesDisponibles(){
        //Forma1: funcional
        return repositorio.findAll().stream()
                //.map( p -> p.ocupacion()) es lo mismo que .map(Personaje::ocupacion)
                .map(Personaje::ocupacion)
                .distinct()
                .sorted()
                .toList();
    }

    private Comparator<Personaje>  crearComparador(String ordenarPor,boolean descendente){
        Comparator<Personaje> comparador = switch (ordenarPor == null ? "" : ordenarPor){
            //case "edad" -> Comparator.comparingInt(p -> p.edad()) con lambda
            case "edad" -> Comparator.comparingInt(Personaje::edad).thenComparing(Personaje::edad); //con referencia a método

            //por apellido y, si no coinciden, por nombre
            case "apellido" -> Comparator.comparing(Personaje::apellido).thenComparing(Personaje::apellido);

            case "lugar" -> Comparator.comparing(Personaje::lugar).thenComparing(Personaje::lugar);

            default -> Comparator.comparing(Personaje::nombre); //por descarte, ha seleccionado nombre
        };
        return descendente ? comparador.reversed() : comparador;
    }
}

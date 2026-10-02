package es.daw.simpson.servicio;

import es.daw.simpson.model.Personaje;
import es.daw.simpson.repository.PersonajeRepository;

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
                                  Integer limite) {

        return repositorio.findAll().stream()
                .filter( p -> lugar == null || lugar.isBlank() || p.lugar().equalsIgnoreCase(lugar))
                .filter( p -> edadMax == null || p.edad() <= edadMax)
                .sorted( (p1, p2) -> p1.nombre() .compareTo(p2.nombre()) )
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
        //
    }
}

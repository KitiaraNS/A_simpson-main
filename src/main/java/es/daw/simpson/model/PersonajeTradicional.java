package es.daw.simpson.model;

import java.util.Objects;

public class PersonajeTradicional implements Comparable<PersonajeTradicional>{
    //1. Atributos
    private String nombre;
    private String apellido;
    private int edad;
    private String ocupacion;
    private String lugar; // sitio donde se suel ver al personaje... casa, escuela
    private boolean principal; // ¿es de la familia protagista

    //2. Constructores
    //si no pongo constructor, automaticamente la clase tiene un constructor vacio.
    public PersonajeTradicional() {
    }

    public PersonajeTradicional(String nombre, String apellido, int edad, String ocupacion, String lugar, boolean principal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.lugar = lugar;
        this.principal = principal;
    }

    //3.Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    //4. Métodos de comportamiento
    public String nombreCompleto() {
        return apellido.isBlank() ? nombre : nombre +" "+apellido;
    }
    public boolean esMenor(){
        return edad < 18;
    }

    //5. Sobrescrritura de la clase padre o implementacion de metodos de interfaz
    @Override
    public int compareTo(PersonajeTradicional o) {
        //si devuelve -1 o negativo es ascendente
        //si devuelve 1 o positivo es descendente
        //si devuelve 0 son iguales

        //la ordenación natural es por nombre completo.
        return this.nombreCompleto().compareTo(o.nombreCompleto());
    }

    //6. Sobreescritura de metodos de Object
    @Override
    public String toString() {
        return "PersonajeTradicional{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", ocupacion='" + ocupacion + '\'' +
                ", lugar='" + lugar + '\'' +
                ", principal=" + principal +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonajeTradicional that = (PersonajeTradicional) o;
        return edad == that.edad && principal == that.principal && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(ocupacion, that.ocupacion) && Objects.equals(lugar, that.lugar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellido, edad, ocupacion, lugar, principal);
    }


}

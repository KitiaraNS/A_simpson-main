package es.daw.simpson.util;

public class Utils {

    /**
     * Convierte un texto a un Integer
     * @param nombreCampo nombre del campo
     * @param valor
     * @return un Integer
     * @throws Exception
     */
    public static Integer leerEntero(String nombreCampo, String valor) throws Exception{
        //1. Comprobamos si valor es null y vacío
        if (valor == null || valor.isEmpty()){
            return null;
        }

        //2. Comprobamos que el texto se puede convertir a un entero
        Integer num;

        try {
            num = Integer.valueOf(valor); //lanza NumberFormatException
        }catch (NumberFormatException e){
            //Crear y propagar un exception checked generica
            throw new Exception("El campo " +nombreCampo+ " debe ser un numero entero");
        }

        //3. Comprobamos que no admite negativos
        if ( num < 0){
            throw new Exception("El campo " +nombreCampo+ " debe ser un numero positivo");
        }
        return num;
    }
}

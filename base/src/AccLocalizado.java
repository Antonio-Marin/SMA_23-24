import java.util.Date;

/**
 * class AccLocalizado : Clase que se encarga de guardar la información de los nuevos agentes Localizados
 * @author Daniel Espinosa Perez (2022-2023)
 * @author Jose Antonio Garcia Castro (2022-2023)
 * @author Miguel Paños Gonzalez (2022-2023)
 * @author Jose Angel Serrano Pardo (2022-2023)
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 *      Ajustes para el curso 2023-2024 (MAFG)
 * @version: 2023-2024-01
 * @observaciones:
 */
public class AccLocalizado {
    // Los siguientes son los datos del agente localizado
    protected String ID; // Identificador único
    protected String IP;
    protected int puerto;
    protected long fecha_encontrado;

    /**
     * Constructor de la clase
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @param ID del agente localido
     * @param IP del agente localido
     * @param puerto del agente localido
     * @observaciones:
     */
    AccLocalizado(String ID, String IP, int puerto, long fecha_encontrado){
        this.ID = ID;
        this.IP = IP;
        this.puerto = puerto;
        this.fecha_encontrado = fecha_encontrado;
    }

    @Override
    public String toString() {
        String puerto_str = String.valueOf(this.puerto);
        String fecha_encontrado_str = String.valueOf(this.fecha_encontrado);
        return "Datos Agente: \t ID: " + this.ID + "\tIP: " + this.IP + "\tPuerto:" + puerto_str + "\t Fecha localización: " +fecha_encontrado_str + "\n" ;
        //return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AccLocalizado))
            return false;

        return this.ID.equals(((AccLocalizado) obj).ID) && this.IP.equals(((AccLocalizado) obj).IP) && this.puerto == (((AccLocalizado) obj).puerto);
    }
}

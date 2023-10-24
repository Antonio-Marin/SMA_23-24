
/**
 * class Mensaje : Clase que almacena la información asociada a un mensaje
 * @author MAFG y Varios alumnos 2022-2023
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 *      Ajustes para el curso 2023-2024 (MAFG)
 * @version: 2023-2024-01
 * @observaciones:
 */
public class Mensaje {

    protected String ID_mensaje; // IP del agente que envía el mensaje
    protected String IP_origen; // IP del agente que envía el mensaje
    protected int puerto_origen; // Puerto del agente que envía el mensaje
    protected String id_origen; // Identificador único del agente que envía el mensaje

    protected String IP_destino; // IP del agente destino de este mensaje
    protected int puerto_destino; // Puerto del agente destino de este mensaje.
                                    // OJO, el "puerto_destino" es el puerto asociado al agente destinatarios (esto es su "Puerto_Propio")
                                    // al realizar el envio, la calse "Enviar" se encarga de direccionarlo hacia "Puerto_Propio_TCP" o "Puerto_Propio_UDP"
                                    // segun proceda atendiendo al valor de la propiedad "protocolo" de este objeto
    protected String id_destino; // Identificador único del agente destino de este mensaje
    protected String protocolo;  // Se admiten protocolos TCP y UDP
    protected String cuerpo_del_mensaje;  // Contenido del mensaje a enviar. s el contenido de este campo, lo que viajara al destino

//CONSTRUCTOR DE MENSAJE
    public Mensaje(String ID_mensaje,
                    String IP_origen,
                    int puerto_origen,
                    String id_origen,
                    String IP_destino,
                    int puerto_destino,
                    String id_destino,
                    String protocolo,
                    String cuerpo_del_mensaje)
    {
        this.ID_mensaje = ID_mensaje;
        this.IP_origen = IP_origen;
        this.puerto_origen = puerto_origen;
        this.id_origen = id_origen;
        this.IP_destino = IP_destino;
        this.puerto_destino = puerto_destino;
        this.id_destino = id_destino;
        this.protocolo = protocolo;
        this.cuerpo_del_mensaje = cuerpo_del_mensaje;
    }

    //GETTERS
    public String getCuerpo_del_mensaje() {
        return cuerpo_del_mensaje;
    }
    public String getProtocolo() {
        return protocolo;
    }
}

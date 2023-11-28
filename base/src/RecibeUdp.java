import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedList;

/**
 * Esta clase se ha creado con el motivo de separar la lógica de recepción de mensajes UDP
 */
/**
 * Esta clase recibe los envíos que llegan mediante el protocolo DPP y los almacena como objetos de la clase mensaje en "contenedor_de_mensajes_a_enviar"
 * @author MAFG y Varios alumnos 2022-2023
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 * @version: 2023-2024-01
 * @observaciones:
 *      - Es necesario convertir el envío recibido en un objeto de clase "Mensaje"
 */
public class RecibeUdp extends Thread {

    protected Acc agente;  // Para tener acceso a los datos de este agente
    DatagramSocket servidor_UDP;

    /**
     * Constructor de la clase
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @param este_agente : Es el objeto agente, donde este objeto puede encontrar toda la informacion y recursos que necesita
     * @observaciones:
     *      - Inicializa datos
     *      - Arranca el hilo encargado de recibir mediante UDP
     */
    RecibeUdp(Acc este_agente) {
        // Inicializamos
        super();
        this.agente = este_agente;
        servidor_UDP = agente.servidor_UDP;


        // Arrancamos el hilo
        new Thread(this, "RecibeUdp").start();
    }

    /**
     * public void run() : Define el proceso que ejecuta este hilo
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Abre el ServerSocket "servidor" y lo deja a la espera de recibir
     *      - Cuando recibe un envío, lo transforma en objeto de la clase "Mensaje" y lo guarda en "contenedor_de_mensajes_recibidos"
     *      - Cuando el agente se destruye, cierra los sockets abiertos
     */
    public void run() {
        System.out.println( "\n ==> El agente : "+ this.agente.ID_propio+
                " - desde la ip : "+ this.agente.Ip_Propia+
                " Arranca el hilo  : RecibeUdp");

        try {
            // El socket de servicio UDP, ya se genero al buscar el nido "Acc => buscaNido()"
            byte[] bufer = new byte[1000];

            while(true)
            {
                // El servidor espera a que el cliente se conecte y devuelve un socket nuevo
                // Obtiene el flujo de entrada y lee el objeto del stream
//                DatagramPacket datos_recibido_UDP = new DatagramPacket(new byte[1024], 1024);
                DatagramPacket paquete_recibido_UDP = new DatagramPacket(bufer, bufer.length);

                System.out.println("\n ==> ********************************** Desde  RecibeUdp ESPERANDO paquete UDP en el agente con id  : "+agente.ID_propio +
                        " - con ip : "+agente.Ip_Propia+
                        " - y Puerto_Propio_UDP : "+agente.Puerto_Propio_UDP);

                // Recibimos el DatagramPacket
                servidor_UDP.receive(paquete_recibido_UDP);

                String paquete_recibido = new String(paquete_recibido_UDP.getData());

                   // Convertimos el envío recibido en objeto de la clase "Mensaje"
                String IP_or = String.valueOf(paquete_recibido_UDP.getAddress());
                int puerto_or = paquete_recibido_UDP.getPort();
                String id_or = "id_or por determinar";
                String IP_dest = agente.Ip_Propia;
                int puerto_dest =  agente.Puerto_Propio_UDP;
                String id_dest = agente.ID_propio;
                String protocolo = "UDP";
                String cuerpo_mens = paquete_recibido;
                String momento_actual = String.valueOf(System.currentTimeMillis());

                Mensaje mensaje_recibido_UDP = new Mensaje("1",
                        "El ID_mensaje viene en el cuerpo del mensaje", "mensaje_recibido_UDP", "Envio informacón", protocolo,
                        id_or, IP_or, Integer.toString(puerto_or), Integer.toString(puerto_dest), momento_actual,
                        id_dest, IP_dest, Integer.toString(puerto_dest), Integer.toString(puerto_dest-1), momento_actual);
                mensaje_recibido_UDP.setBodyInfo(cuerpo_mens);


                String num_men_por_recibidos_str = String.valueOf(agente.num_elem_lita_recibidos());
                System.out.println("\n ==> Mensaje UDP RECIBIDO desde el agente con id  : "+agente.ID_propio +
                        " - en la ip "+agente.Ip_Propia+
                        " - en la ip : "+agente.Ip_Propia+
                        " - en Puerto_Propio : "+agente.Puerto_Propio_TCP+
                        " - mensaje en cola de envio : "+num_men_por_recibidos_str+
                        " - total mensajes enviados : "+agente.num_elem_lita_enviar()+
                        "\n Destinatario id_destino : "+mensaje_recibido_UDP.destinationId+
                        " - en la ip : "+mensaje_recibido_UDP.destinationIp+
                        " - puerto destino : "+mensaje_recibido_UDP.destinationPortUDP+
                        " - protocolo : "+mensaje_recibido_UDP.comunicationProtocol+
                        "\n - mensaje : "+mensaje_recibido_UDP.bodyInfo);

                //TODO hacer bien mensaje
                // Llevamos el mensaje al contenedor de recibidos
                System.out.println("1");
                agente.pon_en_lita_recibidos(mensaje_recibido_UDP);
                    System.out.println("\n ==> Desde RecibeUdp, hemos recibido el mensage : " + mensaje_recibido_UDP.bodyInfo+
                            " - en contenedor tenemos : "+String.valueOf(agente.num_elem_lita_recibidos())+
                            " - total recibidos : "+agente.num_elem_lita_recibidos());
            } // FIn de - while (true) {
        }
        catch (Exception e) {
                //Si llegamos a un error, imprimimos la exception correspondiente
                System.out.println("\n ==> ERROR: RecibeUdp. Con Exception : " + e.getMessage());
                e.printStackTrace();
        }
    } // Fin de - public void run() {
} // FIn de - public class RecibeUdp extends Thread {


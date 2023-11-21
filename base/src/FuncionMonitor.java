import java.util.Random;

/**
 * class FuncionMonitor : Esta clase configura el hilo que ejecuta los procesos específicos del agente monitor
 * @author MAFG y Varios alumnos 2023-2024
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 * @version: 2023-2024-01
 * @observaciones:
 *      - Loas tareas que realizan son las siguientes :
 *          - Gestiona el tiempo de vida del agente eliminandolo cuando su tiempo de vida ha terminado
 *          - Recibe los TODOS los mensajes de TODOS los agentes de del SMA y los imprime en la consola
 *          - PENDIENTE MAFG 2023-10-11, Cuando tenga tiempo y esten operativos habria que
 *              - Analizar los mensajes aferentes para identificar su contenido
 *              - Almacenar la informacion de mensajes y estado referente al SMA en BBDD
 *              - Generar una aplicacion web que acceda a la BBDD y monitorice el estado del SMA
 */
public class FuncionMonitor implements Runnable {

    // Para pruebas
    protected int num_men_recibidos_monitor; // Para identificar los mensajes recibidos por este agente y poder identificarlos de forma unívoca
    protected Acc agente; // Para poder acceder a los datos generales de este agente

    /**
     * public Acc : Contructor de la calse FuncionMonitor
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @param  este_agente : Este agente, para poder acceder a sus datos
     * @observaciones:
     *      - Inicializa datos
     *      - Arranca el hilo asociado a este objeto
     */
    FuncionMonitor(Acc este_agente) {
        num_men_recibidos_monitor = 0;
        this.agente = este_agente;

        // arrancamos el hilo
        new Thread(this, "FuncionMonitor").start();
    }

    /**
     * public void run() : Define el proceso que ejecuta este hilo
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - ESTE METODO ESTA ROTOTIPADO. Debera programarse para que realice las funciones especificas del agente monitor (MAFG 2023-10-04)
     *      - Por ahora, solo anota los mensajes recibidos por consola (ver descripción de esta clase)
     */
    @Override
    public void run() {
        System.out.println( "\n ==> El MONITOR : "+ this.agente.ID_propio+
                " - desde la ip : "+ this.agente.Ip_Propia+
                " Arranca el hilo  : FuncionMonitor");

        while(true){
            // //////////////////////////////////////
            // Obtenemos los mensajes recibidos
                // Miramos si hay algun mensaje recibido y si lo hay lo recogemos
            //System.out.println("no hay mensaje");
            if(this.agente.num_elem_lita_recibidos() > 0) {
                System.out.println("He llegado aqui");
            recogeMensajeRecibido();
            }
        } // Fin de while(true){
    } // Fin de - public void run() {

    /**
     * void recogeMensajeRecibido() : Toma un mensaje de "contenedor_de_mensajes_recibidos" y lo notifica
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Este método debe reprogramarse cuando se desarrolle el sistema de comunicaciones. Por ahora esta tan solo PROTOTIPADO (MAFG 2023-10-04)
     *      - Si el mensaje recibido lo es de Notificación de nacimiento, debe programarse para que el monitor envie :
     * 			    - Número de cromos totales del albun
     * 			    - Lista de cromos poseidos
     * 			    - Cantidad de rupias que posee
     * 			    - Objetivo a cumplir (por ahora sera solo uno, rellenar el album)
     */
    void recogeMensajeRecibido() {
        System.out.println(" => DESDE EL MONITOR. Vamos  a recogeMensajeRecibido. Con : " + agente.num_elem_lita_recibidos() + "mensajes en cola");
        // Obtenemos el mensaje
        Mensaje mensajeRecibido = agente.saca_de_lita_recibidos();
        mensajeRecibido.crearXML();

        num_men_recibidos_monitor++;
        String momento_actual_str = String.valueOf(System.currentTimeMillis());
        String puerto_origen_str = String.valueOf(mensajeRecibido.originPortUDP);

        System.out.println(" => DESDE EL MONITOR. Con num rec : " + num_men_recibidos_monitor +
                                    " - en T : " +momento_actual_str +
                                    " - El agente : "+ mensajeRecibido.originId +
                                    " - desde la ip : "+ mensajeRecibido.originIp +
                                    " - puerto : "+ puerto_origen_str +
                                    " - protocolo : "+ mensajeRecibido.comunicationProtocol +
                                    " - envio el mensaje \n  * Mensaje recibido : "+ mensajeRecibido.bodyInfo);
    } // Fin de - void recogeMensajeRecibido() {


} // FIn de - public class FuncionDeAgente implements Runnable {

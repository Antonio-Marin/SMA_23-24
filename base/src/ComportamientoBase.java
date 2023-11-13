import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * ComportamientoBase : Esta clase configura el hilo que ejecuta los procesos genéricos del agente
 * @author MAFG y Varios alumnos 2022-2023
 * @author MAFG y Varios alumnos 2023-2024
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 * @version: 2023-2024-01
 * @observaciones:
 *      - Loas tareas que realizan son las siguientes :
 *          - Gestiona el tiempo de vida del agente eliminandolo cuando su tiempo de vida ha terminado
 *          - Genera la descendencia del agente
 *          - Busca otros agentes y los va colocando en la lista "directorio_de_agentes" de la clase Acc
 */
public class ComportamientoBase implements Runnable{

    protected Acc agente;
    protected long Tiempo_de_muerte;
    Random random = new Random();

    ComportamientoBase(Acc este_agente) {
        this.agente = este_agente;
        Tiempo_de_muerte = System.currentTimeMillis() + agente.Tiempo_de_vida;
        new Thread(this, "comportamiento_base").start();
    }

    /**
     * public void run() : Define el proceso que ejecuta este hilo
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Mientras el hilo esta activo, busca otros agentes, genera su descendencia y gestiona su tiempo de vida
     *      - Cuando el agente muere, cierra los sockets para liberarlos
     *      - La busqueda de otros agentes "GestorDeDirectorio() "ESTA PROTOTIPADA (2023-20-04 MAFG)
     *      - La generación de descendencia ESTA PROTOTIPADA mno es definitiva (2023-20-04 MAFG)
     */
    @Override
    public void run() {
        System.out.println( "\n ==> El agente : "+ this.agente.ID_propio+
                " - desde la ip : "+ this.agente.Ip_Propia+
                " Arranca el hilo  : ComportamientoBase");

        int nueva_generacion = agente.Num_generacion + 1;

        // Con el while, gestionamos el tiempo de vida del agente
        while ((Tiempo_de_muerte > System.currentTimeMillis()) & (agente.Estado_Actual == Acc.Estado_del_ACC.VIVO )) {

            // ////////////////////////////////////////////////////////////////
            // ////////////////////////////////////////////////////////////////
            // Gestionamos la reproduccion
            if (agente.Frecuencia_partos >= this.random.nextDouble() &&
                    agente.Num_generacion <= agente.Num_max_de_generaciones &&
                    agente.Num_hijos_generados <= agente.Num_max_hijos_generados) {

                //  String nuev_ID_propio = agente.ID_propio + "_hijo_"+identificador_hijos+"_nivel_" + nueva_generacion;
                //  GenerarNuevoAcc(nuev_ID_propio, nueva_generacion);
                agente.Num_hijos_generados++;  // Nos sirve tambien para definir identificadores únicos en la gerarquía
                String nuev_ID_propio = agente.ID_propio + "_hijo_" + agente.Num_hijos_generados + "_nivel_" + nueva_generacion;
                String nueva_generacion_str = String.valueOf(nueva_generacion);
                String Puerto_Monitor_str = String.valueOf(agente.Puerto_Monitor);
                GenerarNuevoAcc(nuev_ID_propio, nueva_generacion_str, "CAMBIACROMOS", agente.Ip_Monitor, Puerto_Monitor_str);

                }

            // ////////////////////////////////////////////////////////////////
            // ////////////////////////////////////////////////////////////////
            // Gestionamos la busqueda de otros agentes
            if(agente.Frecuencia_rastreo_puertos >= this.random.nextDouble()) {
                // GestorDeDirectorio();
                // PARA PRUEBAS  **********************************
                // Ponemos un agente en el directorio para que se puedan enviar mensajes (PARA PRUEBAS)
//                    String ID_otro = nuev_ID_propio;
//                    String IP_otro = agente.Ip_Propia;
//                    int puerto_otro = agente.Puerto_Propio  + (4 * agente.Num_generacion);
//                    long fecha_encontrado_otro = System.currentTimeMillis();
//                    AccLocalizado otroAgente = new AccLocalizado(ID_otro, IP_otro, puerto_otro, fecha_encontrado_otro);

                // Llevamos el mensaje al contenedor de recibidos
//                    synchronized (agente.directorio_de_agentes) {
//                        agente.directorio_de_agentes.add(otroAgente);
                // FIN DE - PARA PRUEBAS  **********************************
            }

        } // FIn de - while (Tiempo_de_muerte > System.currentTimeMillis()) {

        // SI ha pasado su tiempo de vida, o l agente ha erminado su proceso, finalizamos el agente
        System.out.println("\n ==> Fin del agente : "+ agente.ID_propio + " - en la ip " + agente.Ip_Propia);
        agente.finalizaAgente();
    } // Fin de - public void run() {

    /**
     * void GenerarNuevoAcc() : Genera nuevos procesos (agente) en la misma máquina
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @param nuevo_ID_propio : Es el identificador único del agente
     * @param nuevo_generacion : Es  la generacion en la que nace el agente como descendiente del agente que se arranco manualmente en la maquina
     * @param nuevo_tipo_agente : Es el tipo de agente tipo_agente : CAMBIACROMOS o MONITOR
     * @param nuevo_Ip_Monitor : Es  la IP del monitor Ip_Monitor
     * @param  nuevo_Puerto_Monitor : Es el puerto del monitor Puerto_Monitor
     * @observaciones:
     *      - OJO, el agente MONITOR, debe generarse siempre el primero y en una sola maquina. Por lo que el parametro
     *          "nuevo_tipo_agente" debe ser siempre "CAMBIACROMOS" u otros tipos distintos de MONITOR, en un futuro
     */
    void GenerarNuevoAcc(String nuevo_ID_propio, String nuevo_generacion, String nuevo_tipo_agente, String nuevo_Ip_Monitor, String nuevo_Puerto_Monitor) {
        System.out.println("\n ==> Desde el agente con id  : "+agente.ID_propio+
                                                    " - en la ip : "+agente.Ip_Propia+
                                                    " - en el puerto : "+agente.Puerto_Propio_TCP+
                                                    " \n -> Generando Nuevo hijo con nuevo_ID_propio : "+nuevo_ID_propio+
                                                    " - con  nuevo_tipo_agente :  "+nuevo_tipo_agente+
                                                    " - con  nuevo_Ip_Monitor :  "+nuevo_Ip_Monitor+
                                                    " - con  nuevo_Puerto_Monitor :  "+nuevo_Puerto_Monitor+
                                                    " - de generacion : "+nuevo_generacion);
        try {
            // OJOOOO la linea siguiente depende de la configuracion de la máquina donde se ejecuta el agente
            ProcessBuilder nuevo_agente = new ProcessBuilder();
                // Para ejecutar el proceso java, del nuevo agente, aqui primero le decimos a al objeto "nuevo_agente" de clase "ProcessBuilder" que debe
                // realizar la ejecucion desde el directorio donde tenemos el codigo de java (cada implantacion puede tenerlo en un directorio distinto)
                // por lo que es necesaro EN CADA IMPLANTACION indicar en "agente.localizacion", el directorio donde teneis el ejecutable
            File mi_directorio = new File(agente.localizacion_codigo);
            String.valueOf(nuevo_agente.directory(mi_directorio)); // Aqui indicamos el directorio donde debe ejecutar
                // Solo para visualizar el directorio de trabajo
//                String directorio_ejecucion_str = String.valueOf(nuevo_agente.directory());
//                System.out.println("\n ==> EJECUTANDO DESDE EL directorio  : "+directorio_ejecucion_str);
            nuevo_agente.command("java", "arranca_agente", nuevo_ID_propio, nuevo_generacion, "CAMBIACROMOS", nuevo_Ip_Monitor, nuevo_Puerto_Monitor);
            nuevo_agente.start();

//        } catch (IOException e) {
        } catch (Exception e) {
            System.out.println("ERROR en GenerarNuevoAcc. Desde el agente con id  : "+agente.ID_propio+
                                                        " - en la ip "+agente.Ip_Propia+
                                                        " - en la ip "+agente.Puerto_Propio_TCP+
                                                        " -- Generando Nuevo hijo con nuevo_ID_propio : "+nuevo_ID_propio+
                                                        " - con  nuevo_tipo_agente :  "+nuevo_tipo_agente+
                                                        " - con  nuevo_Ip_Monitor :  "+nuevo_Ip_Monitor+
                                                        " - con  nuevo_Puerto_Monitor :  "+nuevo_Puerto_Monitor+
                                                        " - de generacion : "+nuevo_generacion+
                                                        " - Datos de la excepcion : "+ e);
            System.out.println(" - e es  = "+e);
            // throw new RuntimeException(e);
        }
    }

    /**
     * void GestorDeDirectorio() : Busca otros agentes para poder establecer comunicacion con ellos
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Este método esta tan solo PROTOTIPADO (MAFG 2023-10-04)
     */
    void GestorDeDirectorio() {
        // Esta funcion, en esta version esta prototipada
        String tiempo_del_sistema_str = String.valueOf(System.currentTimeMillis());
        System.out.println("Desde gestor de directorio. El agente con id  : "+agente.ID_propio+
                                                    " - en la ip "+agente.Ip_Propia+
                                                    " -  en el tiempo " + tiempo_del_sistema_str);
    }

}

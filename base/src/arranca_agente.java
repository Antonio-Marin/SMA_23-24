import java.util.ArrayList;

/**
 * Es clase desde la que se arranca el proceso de Agente
 * @author MAFG y Varios alumnos 2022-2023
 * @author MAFG y Varios alumnos 2023-2024
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 * @version: 2023-2024-01
 * @param String[] args
 *          - args[0] : "ID_propio" - sera el identificador único del agente
 *          - args[1] : "Num_generacion" - indica la profundidad de la generación en la que se genera el agente
 *          - args[2] : "tipo_agente" - indica el tipo de agente que queremos generar. Los tipos posibles son :
 *                      - "CAMBIACROMOS"
 *                      - "MONITOR"
 *          - args[3] : "Ip_Monitor" - indica, la IP donde se lolaliza el monitor
 *          - args[4] : "Puerto_Monitor" - indica, el puerto donde se lolaliza el monitor
 * @observaciones:
 *      - SI ARRANCAMOS UN AGENET "MONITOR". este generara un solo hijo "CAMBIACROMOS", quien a su vez generara descendencia segun proceda
 *      - SI DESEAMOS EXTENDER EL SMA A VARIAS MAQUINAS (con varias ips), debemos arrancar en cada una de ellas un agente "MONITOR" (que generara tambien en esta
 *          maquina una comunidad de agentes "CAMBIACROMOS" con su descendencia) y en el resto de maquinas, debemos arrancar agentes "CAMBIACROMOS"
 *          indicando la ip y puerto donde hemos hospedado el agente "MONITOR".
 *      - Los parametros siguientes (localizados en la clase Acc):
 *              -   Inicio_rango_IPs; // Para indicar el inicio del rango de IPs donde este agente podrá buscar otros agentes
 *              -   Rango_IPs; // Se suma a "Inicio_rango_IPs" para definir la ultima IP del rango donde este agente podrá buscar otros agentes
 *              -   Puerto_Inicio; // Para indicar el inicio del rango de puertos donde este agente podrá buscar otros agentes, o buscar donde anidar
 *              -   Rango_Puertos; // Se suma a "Puerto_Inicio" para definir el ultimo puerto del rango donde este agente podrá buscar otros agentes, o buscar donde anidar
 *              -   tiempo_espera_fin_env; // Es el tiempo (milisegundos) que esperaremos para enviar los mensajen pendientes en la cola de envios, antes de finalizar el agente
 *              => Se definen en el codigo (deben ser los mismos para todos los agentes del SMA)
 *      - En el parametro siguiente (localizados en la clase Acc):
 *              -   localizacion_codigo;
 *              Debe indicarse el directorio ddonde Java debe encontrar el codigo para ejecutar el Acc
 *      - El proceso de agente se arranca desde el metodo main de esta clase "arranca_agente"
 *
 *      - Los parametros siguientes (localizados en la clase Acc => "Datos para ingeniería social"):
 *      		-	Tiempo_de_vida; // Definimos aqui en milisegundos el tiempo que el proceso del agente estara activo antes de terminarse
 *       		-	Num_generacion; // Un agente que se arranca en una maquina genera procesos hijos y estos generan procesos nietos, este numero
 * 												// indica a que generación correspondeeste agente como descendiente del agente inicial
 * 		        -	Num_max_de_generaciones; // Los agentes de este nivel de generaciones, no generaran agente hijos
 * 	        	-	Num_hijos_generados; // Define el numero de descendientes que este agente ha generado (en primera generación)
 * 			    -   Num_max_hijos_generados; // Define el numero maximo de descendientes de primera generación. que este agente ùede generar
 *      		-	Frecuencia_partos;  // Para manejar la velocidad en la que el agente se reproduce
 * 		        -	Frecuencia_rastreo_puertos; // Para manejar la velocidad en la que el agente busca otros agentes
 * 		        => Se definen en el codigo con el objetivo de definir las caracteristica del SMA, segin se desee
 *
 *      - Este proyecto configura el prototipo base para el ejercicio de practicas de la asignatura de SMA (curso 2023-2024)
 *      - Es tan solo un prototipo base para que los alumnos desarrollen los metodos y clases definitivas del sistema CambiaCromos
 *          u otros proyectos de practicas
 *      - En cada clase se especifica que modulos son prototipos y necesitan por tanto un desarrollo futuro
 *
 */
public class arranca_agente {
    static Acc nuevo_agente; // Sera el objeto agente (Acc) asociado a este proyecto

    public static void main(String[] args) {

      //Prueba ejemplo fichero xml
/*        Mensaje m = new Mensaje("1", "1", "Intercambio", "Oferta", "UDP",
                "ag1_id", "1.1.1.1", "10", "10", "12/10/2023 20:45:35",
                "ag2_id", "2.2.2.2", "10", "10", "12/10/2023 20:45:35");
        AccTest a1 = new AccTest("ag1_id","1.1.1.1",10);
        AccTest a2 = new AccTest("ag2_id","2.2.2.2",20);
        AccTest a3 = new AccTest("ag3_id");
        AccTest a4 = new AccTest("ag4_id");

        ArrayList<AccTest> agentsDirectory = new ArrayList<AccTest>();
        agentsDirectory.add(a1);
        agentsDirectory.add(a2);
        ArrayList<AccTest> deadAgents = new ArrayList<AccTest>();
        deadAgents.add(a3);
        deadAgents.add(a4);

        ArrayList<String> ownedCardType = new ArrayList<String>();
        ownedCardType.add("Pikachu");
        ownedCardType.add("Chikorita");
        ArrayList<String> ownedCardQuantity= new ArrayList<String>();
        ownedCardQuantity.add("3");
        ownedCardQuantity.add("2");
        ArrayList<String> ownedCardCost= new ArrayList<String>();
        ownedCardCost.add("10");
        ownedCardCost.add("20");
        ArrayList<String> wantedCardType= new ArrayList<String>();
        wantedCardType.add("Mew");
        wantedCardType.add("Bidoof");

        ArrayList<String> offeredCardType = new ArrayList<String>();
        offeredCardType.add("Pikachu");
        offeredCardType.add("Chikorita");
        ArrayList<String> offeredCardQuantity= new ArrayList<String>();
        offeredCardQuantity.add("3");
        offeredCardQuantity.add("3");
        ArrayList<String> offeredCardCost= new ArrayList<String>();
        offeredCardCost.add("10");
        offeredCardCost.add("10");
        ArrayList<String> wishedCardType= new ArrayList<String>();
        wishedCardType.add("Mew");
        wishedCardType.add("Bidoof");
        wishedCardType.add("Torterra");

        m.setAgentsDirectory(agentsDirectory);
        m.setDeadAgents(deadAgents);
        m.setBodyInfo("Mensaje de texto");
        m.setOwnedCardType(ownedCardType);
        m.setOwnedCardQuantity(ownedCardQuantity);
        m.setOwnedCardCost(ownedCardCost);
        m.setWantedCardType(wantedCardType);
        m.setOwnedMoney("200");
        m.setCreatedChilds("20");
        m.setDeathTime("-");
        m.setPastTradeGivenCard("-");
        m.setPastTradeWantedCard("-");
        m.setTradeWantedCard("X");
        m.setTradeGivenCard("Y");
        m.setOfferedCardType(offeredCardType);
        m.setOfferedCardQuantity(offeredCardQuantity);
        m.setOfferedCardCost(offeredCardCost);
        m.setWishedCardType(wishedCardType);
        m.setTradeMoney("20");

        m.crearXML();*/
        //m.prueba();

        //Codigo ejecución agente
        nuevo_agente = new Acc(args[0], args[1], args[2], args[3], args[4]); //creado arriba

        //Ej.: para arrancar el proceso :

         //Para un ACC monitor (que generara Accs cambiacromos descendientes)
         //d:/..desde_el_directorio_coresponiente/java arranca_agente "nombre_agente_monitor_raiz" 1 "MONITOR" "ip monitor" "puerto monitor"

         //Para un ACC CAMBIACROMOS (que generara Accs cambiacromos descendientes)
         //d:/..desde_el_directorio_coresponiente/java arranca_agente "nombre_agente_raiz" 1 "CAMBIACROMOS" "ip monitor" "puerto monitor"
    }
}

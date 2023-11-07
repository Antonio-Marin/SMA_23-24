import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Esta clase genera el agente, inicializa sus valores y arranca los procesos necesarios para su funcionamient
 * @author MAFG y Varios alumnos 2022-2023
 * @author MAFG y Varios alumnos 2023-2024
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 * @version: 2023-2024-01
 * @observaciones:
 *      - Contiene la información básica asociada al agente
 *          - Datos del agente
 *          - Datos del entorno de ejecución
 *          - Datos para ingeniería social
 *          - Datos de la función del agente
 *          - Datos del sistema de comunicaciones
 */

public class Acc {

    // //////////////////////////////////////
    // //////////////////////////////////////
    // DATOS GLOBALES

    // //////////////////////////////////////
    // Datos del agente
    protected String ID_propio; // Identificador unico de este agente
    protected String Ip_Propia;  // Ip donde reside este agente
    protected int Puerto_Propio;  // Es el puerto asociado al agente (coincide con el puerto de servidor TCP del agente)
    protected int Puerto_Propio_TCP;  // Es el puerto de servidor TCP del agente (coincide con el puerto asociado al agente)
    protected int Puerto_Propio_UDP;  // Es el puerto de servidor UDP del agente (es el siguiente a "Puerto_Propio" osea - Puerto_Propio_UDP = Puerto_Propio+1)
    protected long Tiempo_de_nacimiento;  // La hora del sistema de esta maquina en la que se genera el agente
    protected tipos_de_agentes tipo_agente;  // Para indicar si es cambiacromos o monitor
    protected enum tipos_de_agentes
    {
        CAMBIACROMOS, MONITOR
    }

    // //////////////////////////////////////
    // Datos del entorno de ejecución
    protected String Ip_Monitor;  // Es la IP donde reside el monitor (es la misma para todos los agentes del SMA)
    protected int Puerto_Monitor;  // Es el puerto donde reside el monitor (es la misma para todos los agentes del SMA)
    protected int Puerto_Monitor_TCP;  // Es el puerto de servidor TCP del agente monitor (es el mismo que "Puerto_Monitor" y es la misma para todos los agentes del SMA)
    protected int Puerto_Monitor_UDP;  // Es el puerto de servidor UDP del agente monitor (es el mismo que "Puerto_Monitor+1" y es la misma para todos los agentes del SMA)
    protected String Inicio_rango_IPs; // Para indicar el inicio del rango de IPs donde este agente podrá buscar otros agentes
    protected int Rango_IPs; // Se suma a "Inicio_rango_IPs" para definir la ultima IP del rango donde este agente podrá buscar otros agentes
    protected int Puerto_Inicio; // Para indicar el inicio del rango de puertos donde este agente podrá buscar otros agentes, o buscar donde anidar
    protected int Rango_Puertos; // Se suma a "Puerto_Inicio" para definir el ultimo puerto del rango donde este agente podrá buscar otros agentes, o buscar donde anidar
    protected String localizacion_codigo;
    protected long tiempo_espera_fin_env; // Es el tiempo maximo que esperaremos para enviar los mensajen pendientes en la cola de envios, antes de finalizar el agente
                                        // si transcurrido esta cantidad de milisegundos sigue habiendo mensajes por enviar en la cola de envios, el agente se cierra y estos
                                        // quedaran sin enviar
    protected FuncionMonitor funcionMonitor;  // Sera un hlo de ejecución

    // //////////////////////////////////////
    // Datos para ingeniería social
    protected ComportamientoBase comportamientoBase ;  // Sera un hlo de ejecución
    protected enum Estado_del_ACC
    {
        VIVO, MUERTO, FINALIZADO
    }
    protected Estado_del_ACC Estado_Actual;
    protected long Tiempo_de_vida; // Definimos aqui en milisegundos el tiempo que el proceso del agente estara activo antes de terminarse
    protected int Num_generacion; // Un agente que se arranca en una maquina genera procesos hijos y estos generan procesos nietos, este numero
                                    // indica a que generación correspondeeste agente como descendiente del agente inicial
    protected int Num_max_de_generaciones; // Los agentes de este nivel de generaciones, no generaran agente hijos
    protected int Num_hijos_generados; // Define el numero de descendientes que este agente ha generado (en primera generación)
    protected int Num_max_hijos_generados; // Define el numero maximo de descendientes de primera generación. que este agente ùede generar
    protected double Frecuencia_partos;  // Para manejar la velocidad en la que el agente se reproduce
    protected double Frecuencia_rastreo_puertos; // Para manejar la velocidad en la que el agente busca otros agentes

    // //////////////////////////////////////
    // //////////////////////////////////////
    // Datos de la función del agente
    protected FuncionDeAgente funcionAgente;  // Sera un hlo de ejecución

    // //////////////////////////////////////
    // //////////////////////////////////////
    // Datos del sistema de comunicaciones
    protected Enviar enviar;  // Sera un hlo de ejecución
    protected RecibeTcp recibeTcp;  // Sera un hlo de ejecución
    protected RecibeUdp recibeUdp;  // Sera un hlo de ejecución

    protected ServerSocket servidor_TCP;  // Puerto para el servicio por TCP
    protected DatagramSocket servidor_UDP;  // Puerto para el servicio por UDP

    private LinkedList<AccLocalizado> directorio_de_agentes = new LinkedList<>(); // Contenedor para almacenar cada uno de los mensajes para enviar por un agente
    private  int num_tot_acc_loc;  // Numero total de agentes localizados
    private LinkedList<Mensaje> contenedor_de_mensajes_a_enviar = new LinkedList<>(); // Contenedor para almacenar cada uno de los mensajes para enviar por un agente
    private  int num_tot_men_env;  // Numero total de mensajes enviados por el agente
    private LinkedList<Mensaje> contenedor_de_mensajes_recibidos = new LinkedList<>(); // Contenedor para almacenar cada uno de los mensajes recibidos por un agente
    private  int num_tot_men_rec;  // Numero total de mensajes recibidos por el agente
    private  int num_id_local_men;  // Este numero, junto con el identificador del agente, generan un codigo unico de mensaje

    /**
     * public Acc : Contructor de la calse Acc
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @param ID_propio : sera el identificador único "ID_propio" del agente
     * @param este_Num_generacion_str : indica la profundidad de la generación en la que se genera e, agente "Num_generacion"
     * @observaciones:
     *      - Inicializa los datos del agente
     *      - Busca un puerto libre en esta maquina donde alojarse "buscaNido()"
     *      - Configura el estado inicial "generaConfiguracionInicial()"
     *      - Genera los hilos donde correran los procesos que se ejecutan paralelamente en el agente
     *          Hilos de comunicaciones
     *          - Enviar
     *          - RecibeTcp
     *          - RecibeUdp
     *          - ComportamientoBase : procesos básicos del agente
     *          - Dependiendo de si el agente es monitor o cambiaCromos :
     *              - En el caso del CAMBIACROMOS :
     *                  - FuncionDeAgente : Procesos especificos del agente
     *                  - Notifica su existencia al monitor "notificaNacimiento"
     *              - En el caso del MONITOR :
     *                  - FuncionMonitor : Procesos especificos del agente monitor
     */
    public Acc (String ID_propio, String este_Num_generacion_str, String este_tipo_agente, String este_Ip_Monitor, String este_Puerto_Monitor) {

        // Obtiene el PID del proceso actual para poder pararlo
        long pid = obtenerPID();

        System.out.println("\n ========================================================================================" +
                            "\n> =========================== INICIO AGENTE ==============================================" +
                            "\n> ========================================================================================" +
                            " \n ID_propio : "+ ID_propio+
                            " Num generacion : "+ este_Num_generacion_str+
                            " Tipo agente : "+ este_tipo_agente+
                            " Ip Monitor : "+ este_Ip_Monitor +
                            " Puerto Monitor : "+ este_Puerto_Monitor+
                            "\n> ========================= PID proceso : "+pid+" ======================================" +
                            "\n> ============ Para matar el proceso : taskkill /PID "+pid+" /F  ========================" +
                            "\n> ========================================================================================");

        // Definimos los datos y parametros operativos del agente. Con ellos podremos manejar sus caracteísticas
        generaConfiguracionInicial(ID_propio, este_Num_generacion_str, este_tipo_agente, este_Ip_Monitor, este_Puerto_Monitor);

        // Buscamos el puerto donde alojarlo.
        if(this.tipo_agente == tipos_de_agentes.CAMBIACROMOS)
        {
            // Para el agente CAMBIACROMOS tenemos que buscar los puertos donde albergar el agente
            buscaNido();
        }
        else if (this.tipo_agente == tipos_de_agentes.MONITOR) {
            // Para el agente MONITOR los puertos vienen fijados al generar el agente
            this.Puerto_Propio = this.Puerto_Monitor;
            this.Puerto_Propio_TCP = this.Puerto_Propio;
            this.Puerto_Propio_UDP = this.Puerto_Propio + 1;

            // Generamos los sockets de TCP y UDP en el monitor, ya que este sabe cuales son sus puerton y no usa "buscaNido()" para localizarse
            try {
                servidor_TCP = new ServerSocket(Puerto_Propio_TCP);
                servidor_UDP = new DatagramSocket(Puerto_Propio_UDP);
            } catch (Exception e) {
                System.out.println("\n ==> ERROR. Desde Acc al abrir los puertos de comunicaciones con Puerto_Propio : " + Puerto_Propio +
                        " - con Puerto_Propio_TCP : " + Puerto_Propio_TCP +
                        " - con Puerto_Propio_UDP : " + Puerto_Propio_UDP +
                        " - en el MONITOR");
            }
        } // Fin de - else if (this.tipo_agente == tipos_de_agentes.MONITOR) {
        else
        {
            System.out.println("Desde public Acc. ERROR al procesar el tipo de agente al buscar nido");
        }

        // Generamos los hilos de ejecucion que utiliza ente agente
            // Hilos de comunicaiones
        this.recibeTcp = new RecibeTcp(this);
        this.recibeUdp = new RecibeUdp(this);
        this.enviar = new Enviar(this);
            // Hilos de comportamiento
        this.comportamientoBase = new ComportamientoBase(this);
            // Dependiendo del tipo de agente
        if(this.tipo_agente == tipos_de_agentes.CAMBIACROMOS)
        {
                // Para el agente CAMBIACROMOS arrancamos su funcion del agente y notificamos al monitor su nacimiento
            this.funcionAgente = new FuncionDeAgente(this);
            notificaNacimiento();
        }
        else if (this.tipo_agente == tipos_de_agentes.MONITOR)
        {
            // Para el agente MONITOR tan solo arrancamos su funcion del agente monitor
            this.funcionMonitor = new FuncionMonitor(this);
            // EL monitor no se notifica su propio nacimiento
        }
        else
        {
            System.out.println("Desde public Acc. ERROR al procesar el tipo de agente al ir a notificar el nacimiento");
        }

        this.Estado_Actual = Estado_del_ACC.VIVO;
    }

    /**
     *  generaConfiguracionInicial() : realiza la configuracion inicial del agente
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - ESTA FUNCION SOLO ESTA PROTOTIPADA (2023-20-04)
     */
    protected void generaConfiguracionInicial(String ID_propio, String este_Num_generacion_str, String este_tipo_agente, String este_Ip_Monitor, String este_Puerto_Monitor) {

        // //////////////////////////////////////
        // Definiendo datos del agente
        this.ID_propio = ID_propio;
        try {
            this.Ip_Propia = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println("Desde public Acc. => eneraConfiguracionInicial. ERROR al adquirir la Ip_Propia, da un valor : " + Ip_Propia);
            throw new RuntimeException(e);
        }

        if (este_tipo_agente.equals("MONITOR")) {
            this.tipo_agente = tipos_de_agentes.MONITOR;
        } else if (este_tipo_agente.equals("CAMBIACROMOS")) {
            this.tipo_agente = tipos_de_agentes.CAMBIACROMOS;
        } else {
            System.out.println("Desde public Acc. ERROR definiendo tipo_agente : " + este_tipo_agente + " - no es un tipo de agente conocido");
        }

        // //////////////////////////////////////
        // Definiendo Datos del entorno de ejecución
        if (este_tipo_agente.equals("MONITOR")) {
            this.Ip_Monitor = this.Ip_Propia; // Si el agente es el monitor su ip y la del monitor son la misma evidentemente
        } else if (este_tipo_agente.equals("CAMBIACROMOS")) {
            this.Ip_Monitor = este_Ip_Monitor;
        } else {
            System.out.println("Desde public Acc. ERROR Definiendo DATOS DEL ENTORNO : " + este_tipo_agente + " - no es un tipo de agente conocido");
        }
        this.Puerto_Monitor = Integer.parseInt(este_Puerto_Monitor);  // El puerto de monitor se define como parametro de llamada al proceso
        this.Puerto_Monitor_TCP = this.Puerto_Monitor;  // Es el puerto de servidor TCP del agente monitor (es el mismo que "Puerto_Monitor" y es la misma para todos los agentes del SMA)
        this.Puerto_Monitor_UDP = this.Puerto_Monitor + 1;  // Es el puerto de servidor UDP del agente monitor (es el mismo que "Puerto_Monitor+1" y es la misma para todos los agentes del SMA)

        this.Inicio_rango_IPs = Ip_Propia;  // Solo para pruebas
        this.Rango_IPs = 0;
        this.Puerto_Inicio = 50000;
        this.Rango_Puertos = 10000;
        this.localizacion_codigo = "C:/Users/marti/IdeaProjects/SMA_23-24/base/out/production/base"; //cambia segun quien lo ejecute
        this.tiempo_espera_fin_env = 1000 * 1; // Es el tiempo (milisegundos) que esperaremos para enviar los mensajen pendientes en la cola de envios, antes de finalizar el agente

        // //////////////////////////////////////
        // Definiendo  Datos para ingeniería social
        this.Estado_Actual = Estado_del_ACC.VIVO;
        this.Tiempo_de_nacimiento = System.currentTimeMillis();
        if (este_tipo_agente.equals("MONITOR")) {
            this.Tiempo_de_vida = 1000 * 100;  // Lo es en milisegundos
        } else if (este_tipo_agente.equals("CAMBIACROMOS")) {
            this.Tiempo_de_vida = 1000 * 50;  // Lo es en milisegundos
        } else {
            System.out.println("Desde public Acc. ERROR definiendo Tiempo_de_vida : " + este_tipo_agente + " - no es un tipo de agente conocido");
        }
        int este_Num_generacion = Integer.parseInt(este_Num_generacion_str);
        this.Num_generacion = este_Num_generacion;
        this.Num_max_de_generaciones = 1;
        this.Num_hijos_generados = 0; // Por ahora el agente no ha generado ningún descendiente
        this.Num_max_hijos_generados = 1; // el agente no debe superar este numero de descendientes en primera generacion (en principio arbitrario
        this.Frecuencia_partos = 0.01;
        this.Frecuencia_rastreo_puertos = 0.00001f;

        // //////////////////////////////////////
        // Definimos datos del sistema de comunicaciones
        this.num_tot_acc_loc = 0;  // Numero total de agentes localizados
        this.num_tot_men_env = 0;  // Numero total de mensajes enviados por el agente
        this.num_tot_men_rec = 0;  // Numero total de mensajes recibidos por el agente
        this.num_id_local_men = 0;  // Cada vez que se solicita, se incrementa en uno, para generar un codigo local unico para los mensajes (ver dame_codigo_id_local_men())
    } // Fin de - protected void generaConfiguracionInicial)

    /**
     * buscaNido() : Busca en la maquina dosde se esta ejecutando un puerto libre para alojar al agente
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Recordamos que la ip del agente "Puerto_Propio" es la misma que la ip de su puerto de escucha TCP "Puerto_Propio_TCP", asi mismo
     *          el puerto se escucah UDP "Puerto_Propio_UDP" de este agente se coloca en el puerto siguiente a este.
     *      - Cada agente ocupa pues un par de puertos consegutivos, puerto par - TCP y puerto impar - UDP
     *      - Detro del rango de IPs donde debemos buscar los puertos donde anide el agente, buscamos una localizacion
     *          aleatoriamente y luego vamos buscando por puertos consecutivos hasta recorrer todo el rango de Ips
     *
     */
    protected void buscaNido() {

        Random rand = new Random();
        int puerto_ini_busqueda = Puerto_Inicio + rand.nextInt(Rango_Puertos + 1);
        puerto_ini_busqueda = (puerto_ini_busqueda / 2) * 2; // Nos aseguramos que el numero sea par
        int puerto_busqueda = puerto_ini_busqueda;
        boolean sigue_buscando = true;
            // Para evitar que el proceso se eternice
        int num_intentos = 0; // Para llevar una cuenta del numero de puertos en los que hemos intentado anidar
        int max_num_intentos = 5000; // Para llevar una cuenta del numero de puertos en los que hemos intentado anidar
        long T_ini_busqueda =  System.currentTimeMillis();  // La hora del sistema de esta maquina en la que se inicia la busqueda de nido
        long T_max_busqueda = 1000 * 10;  // El periodo maximo de tiempo que permitimos que el agente este buscando su nido (en milisegundos)
        long T_limite_busqueda = T_ini_busqueda + T_max_busqueda;  // El momento en el que el agente debe parar de buscar su nido (en milisegundos)

        while (sigue_buscando) {
            boolean TCP_ok = false;
            try {
                servidor_TCP = new ServerSocket(puerto_busqueda);
                TCP_ok = true;
                servidor_UDP = new DatagramSocket(puerto_busqueda + 1);

                // Si hemos podido ocupar los dos puertos, ya son nuestros y por tanto anotamos nuestra localizacion
                this.Puerto_Propio = puerto_busqueda;
                this.Puerto_Propio_TCP = Puerto_Propio;
                this.Puerto_Propio_UDP = Puerto_Propio +1;

                // Si los dos puertos han funcionado, ya tenemos nido y podemos para de buscar
                sigue_buscando = false;

                long T_actual = System.currentTimeMillis();
                long T_buscando = System.currentTimeMillis() - T_ini_busqueda;
                System.out.println("\n ==> Desde Acc => buscaNido ANIDADO CORRECTAMENTE con num_intentos : "+num_intentos+
                        " - con max_num_intentos : "+ max_num_intentos +
                        " - con T_ini_busqueda : "+ T_ini_busqueda +
                        " - con T_actual : "+ T_actual +
                        " - con T_limite_busqueda : "+ T_limite_busqueda +
                        " - tiempo invertido (milisegundos) : "+ T_buscando+
                        "\n - anidado en Puerto_Propio : "+ this.Puerto_Propio +
                        " - Puerto_Propio_TCP : " + this.Puerto_Propio_TCP +
                        " - Puerto_Propio_UDP : " + this.Puerto_Propio_UDP);

            } catch (Exception e) {
                // Si NO hemos podido ocupar los dos puertos, debemos seguir buscando mas adelante
                puerto_busqueda++;
                num_intentos++;
                if (puerto_busqueda > (Puerto_Inicio + Rango_Puertos)) {
                    puerto_busqueda = Puerto_Inicio;
                } // SI nos salimos del rango, volvemos al principio

                if (TCP_ok) {
                    // SI hemos llegado aqui es que hemos podido abrir "servidor_TCP", pero no "servidor_UDP", por lo que cerramos "servidor_TCP"
                    // para que quede todo como estaba
                    try {
                        servidor_TCP.close();
                    }
                    catch (IOException IO_e )
                    {
                        long T_actual = System.currentTimeMillis();
                        long T_buscando = System.currentTimeMillis() - T_ini_busqueda;
                        System.out.println("\n ==> ERROR. Desde Acc => buscaNido al intentar derrar el socket TCP con num_intentos : "+num_intentos+
                                " - con max_num_intentos : "+ max_num_intentos +
                                " - con T_ini_busqueda : "+ T_ini_busqueda +
                                " - con T_actual : "+ T_actual +
                                " - con T_limite_busqueda : "+ T_limite_busqueda +
                                " - tiempo invertido (milisegundos) : "+ T_buscando+
                                " - con IO_e : "+IO_e);
                    }
                } // Fin de - if (TCP_ok) {

                // COntrolamos si debemos detener la busqueda de nido
                long T_actual = System.currentTimeMillis();
                if((num_intentos > max_num_intentos) || (T_actual > T_limite_busqueda))
                {
                    sigue_buscando = false;
                    long T_buscando = System.currentTimeMillis() - T_ini_busqueda;
                    System.out.println("\n ==> Desde Acc => buscaNido.Detenemos la busqueda por exceso de intentos o tiempo con num_intentos : "+num_intentos+
                            " - con max_num_intentos : "+ max_num_intentos +
                            " - con T_ini_busqueda : "+ T_ini_busqueda +
                            " - con T_actual : "+ T_actual +
                            " - con T_limite_busqueda : "+ T_limite_busqueda +
                            " - tiempo invertido (milisegundos) : "+ T_buscando);
                }
            } // Fin de - try catch
        }  // FIn de - while (sigue_buscando)
    } // FIn de - protected void buscaNido()

    /**
     *  notificaNacimiento() : Notifica al monitor que este agente ha sido generado
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Este mensaje se envia por (TCP)
     *      - (PENDIENTE MAFG 2023-10-04) Falta poner el mensaje con formato XML
     */
    protected void notificaNacimiento() {
        // Por ahora solo es una función prototipo
        // Construimos el mensaje

        String ID_mensaje = dame_codigo_id_local_men();
        String momento_actual = String.valueOf(System.currentTimeMillis());
        String Puerto_Propio_str = String.valueOf(Puerto_Propio);
        String Puerto_Monitor_UDP_str = String.valueOf(Puerto_Monitor_UDP);
        String cuerpo_mens = "Esto es el MENSAJE HE NACIDO  - que el agente con ID_propio : " + ID_propio +
                " - con ip : " + Ip_Propia +
                " - con Puerto_Propio : " + Puerto_Propio_str +
                " - con ID_mensaje : " + ID_mensaje +
                " - envia al monitor con Ip_Monitor : "+Ip_Monitor+
                " - con Puerto_Monitor : "+Puerto_Monitor_UDP_str+
                " :  - en T : " + momento_actual;

        Mensaje mensaje_he_nacido = new Mensaje(ID_mensaje,
                Ip_Propia,
                Puerto_Propio,
                ID_propio,
                Ip_Monitor,
                Puerto_Monitor_UDP,
                "monitor",
                "UDP",
                cuerpo_mens);

        // Insertamos el mensaje
        pon_en_lita_enviar(mensaje_he_nacido);

        String Num_generacion_str = String.valueOf(this.Num_generacion);
        String Tiempo_de_nacimiento_str = String.valueOf(this.Tiempo_de_nacimiento);
        System.out.println("\n ==> Ha nacido un agente en la IP = "+Ip_Propia+
                                " - con ID_propio :" + this.ID_propio +
                                " - en el puerto :" + this.Puerto_Propio +
                                " - Su generación es :" + Num_generacion_str +
                                " - t de generación :" + Tiempo_de_nacimiento_str);
    } // Fin de - protected void notificaNacimiento() {

    /**
     *  notificaFin() : Notifica al monitor que este agente ha finalizado e informa del resultado de la realizacion del proceso
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Este mensaje se envia por TCP
     *      - (PENDIENTE MAFG 2023-10-04) Falta poner el mensaje con formato XML
     */
    protected void finalizaAgente() {
        Estado_Actual = Estado_del_ACC.FINALIZADO;

        // ///////////////////////////////////////////////////////
        // Notificamos al monitor que este agente ha finalizadO

        String ID_mensaje = dame_codigo_id_local_men();
        long momento_actual = System.currentTimeMillis();
        String momento_actual_str = String.valueOf(System.currentTimeMillis());
        String tiempo_vivido =  String.valueOf(System.currentTimeMillis() - Tiempo_de_nacimiento);
        String Puerto_Propio_str = String.valueOf(Puerto_Propio);
        String Puerto_Monitor_TCP_str = String.valueOf(Puerto_Monitor_TCP);
        String cuerpo_mens_fin_agente = "Esto es el MENSAJE FIN DE AGENTE  - que el agente con ID_propio : " + ID_propio +
                " - con ip : " + Ip_Propia +
                " - con Puerto_Propio : " + Puerto_Propio_str +
                " - con ID_mensaje : " + ID_mensaje +
                " - envia al monitor con Ip_Monitor : "+Ip_Monitor+
                " - con Puerto_Monitor : "+Puerto_Monitor_TCP_str+
                " - en T : " + momento_actual_str+
                " - con T de vida : " + Tiempo_de_vida +
                " - con T vivido : " + tiempo_vivido;

        Mensaje mensaje_fin_agente = new Mensaje(ID_mensaje,
                Ip_Propia,
                Puerto_Propio,
                ID_propio,
                Ip_Monitor,
                Puerto_Monitor_TCP,
                "monitor",
                "TCP",
                cuerpo_mens_fin_agente);

        // Insertamos el mensaje
        pon_en_lita_enviar(mensaje_fin_agente);

        System.out.println("\n ==> NOTIFICACION LOCAL de FIN DE AGENTE - \n "+ cuerpo_mens_fin_agente);

        // ///////////////////////////////////////////////////////
        // Nos vamos
        //  - Antes de cerrar los sockets, esperamos a que todos los mensajes esten enviados. Los
        //       recibidos, como estamos muertos no nos importan

        boolean espera_fin_envios = true;
        while (espera_fin_envios)
        {
            if ((num_elem_lita_enviar() <= 0) || ((momento_actual + tiempo_espera_fin_env) < System.currentTimeMillis())){espera_fin_envios = false;}
        }

        // Dejamos la casa como estaba
        cerrarSockets();

        System.out.println("\n ==> Finalizamos el agente con : \n - men pendientes de envio : "+ num_elem_lita_enviar() + " - Total enviados : " + num_tot_men_env +
                            "\n - men en cola recibidos : "+ num_elem_lita_recibidos() + " - Total recibidos : " + num_tot_men_rec);
        System.out.println("\n ========================================================================================" +
                "\n> ========================================================================================" +
                "\n> ==============================   FIN DE AGENTE  ========================================" +
                "\n                              ID_propio : "+ ID_propio+
                "\n> ========================================================================================" +
                "\n> ========================================================================================");

        // Terminamos el proceso
        System.exit(0);     // Parar el agente

    } // Fin de - protected void notificaNacimiento() {

    /**
     * void cerrarSockets() : Cierra los sockets del agente para liberarlos
     * @author MAFG y Varios alumnos 2022-2023
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *      - Debe llamarse al terminar el agente
     */
    void cerrarSockets() {

        //
        // Cerramos el socket TCP
        try {
            recibeTcp.servidor_TCP.close();
        } catch (Exception e) {
            System.out.println("Desde Acc - cerrarSockets : Problemas al cerrar el socket");
        }
        // Cerramos el socket UDP
        try {
            recibeUdp.servidor_UDP.close();
        } catch (Exception e) {
            System.out.println("Desde Acc - cerrarSockets : Problemas al cerrar el socket");
        }
    } // Fin de - void cerrarSockets() {

    /**
     * public static long obtenerPID(); Para obtener el id del proceso en el que se ejecuta la clase
     * @author Miguel Angel Fernandez Graciani
     * @fechaDeCreacion: 2023-10-16
     * @fechaDeUltimaModificacion:
     * @version: V_01
     * @observaciones:
     *  - Me lo dice chatGPT (2023-10-18)
     */
    public static long obtenerPID() {
        String nombreGestion = ManagementFactory.getRuntimeMXBean().getName();
        // El nombre de gestión tiene el formato "pid@hostname"
        String[] partes = nombreGestion.split("@");

        if (partes.length > 0) {
            try {
                return Long.parseLong(partes[0]);
            } catch (NumberFormatException e) {
                // Manejar la excepción si no se puede convertir a un número
                e.printStackTrace();
            }
        }

        // Si no se pudo obtener el PID, devolver un valor predeterminado
        return -1;
    }

    /**
     *  pon o saca : Funciones para el manejo de datos compartidos. Introduce o saca mensajes o agentes localizados de la lista correspondiente
     * @author MAFG y Varios alumnos 2023-2024
     * @fechaDeCreacion: 2022-xx-xx
     * @fechaDeUltimaModificacion: 2023-10-04
     * @version: 2023-2024-01
     * @observaciones:
     *  - Se hace mediante "synchronized" para evitar problemas de concurrencia
     */
    // synchronized
    protected void pon_en_lita_enviar(Mensaje este_mensaje) {contenedor_de_mensajes_a_enviar.add(este_mensaje); num_tot_men_env++; }
    protected void pon_en_lita_recibidos(Mensaje este_mensaje) {contenedor_de_mensajes_recibidos.add(este_mensaje); num_tot_men_rec++; }
    protected void pon_en_directorio_de_agentes(AccLocalizado este_accLocalizado) {directorio_de_agentes.add(este_accLocalizado); num_tot_acc_loc++; }

    protected int num_elem_lita_enviar() {int num_elem = contenedor_de_mensajes_a_enviar.size(); return num_elem;}
    protected int num_elem_lita_recibidos() {int num_elem = contenedor_de_mensajes_recibidos.size(); return num_elem;}
    protected int num_elem_directorio_de_agentes() {int num_elem = directorio_de_agentes.size(); return num_elem;}

    protected int dime_num_tot_men_env() {return num_tot_men_env;}
    protected int dime_num_tot_men_rec() {return num_tot_men_rec;}
    protected int dime_num_tot_acc_loc() {return num_tot_acc_loc;}

    protected String dame_codigo_id_local_men(){
            String codigo_id_local_men = ID_propio + "_men_" + num_id_local_men;
            num_id_local_men++;
            return codigo_id_local_men;
        }

    protected Mensaje saca_de_lita_enviar() {Mensaje este_mensaje = contenedor_de_mensajes_a_enviar.pop(); return este_mensaje; }
    protected Mensaje saca_de_lita_recibidos() {Mensaje este_mensaje = contenedor_de_mensajes_recibidos.pop(); return este_mensaje; }
    protected AccLocalizado saca_de_directorio_de_agentes() {AccLocalizado este_accLocalizado = directorio_de_agentes.pop(); return este_accLocalizado; }




}
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Esta clase se usa para realizar distintas operacionesrelacionadas con las direcciones IP
 * @author MAFG y Varios alumnos 2022-2023
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 * @version: 2023-2024-01
 * @observaciones:
 *      - Puede trabajar con IP4 e IP6
 */
public class direccion_IP {

    private String IP_str;  // Es la IP en formato string.  Ej: "192.168.22.5"
    private int[] IP_int; // Es la IP en un array de enteros
    private byte[] IP_Bytes; // Es la IP en un array de Bytes
    private int num_comp_IP = 0; // Para poder trabajar con IP4 e IP6

    /**
     * Constructor de la clase (1 de 2)
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2023-10-06
     * @fechaDeUltimaModificacion:
     * @version: 2023-2024-01
     * @param este_IP_int : Es un array en el que cada elemento del aray es una componente de la IP
     *                Ej: "192.168.22.5"
     *                    este_IP_int[0] = 192 / este_IP_int[1] = 168 / este_IP_int[2] = 22 / este_IP_int[3] = 5
     * @observaciones:
     */
    public direccion_IP(int[] este_IP_int){
        num_comp_IP = este_IP_int.length;
        IP_int = new int[num_comp_IP];

        if((num_comp_IP == 4) || (num_comp_IP == 4)) {
            IP_int = este_IP_int;
            IP_str = "";
            for (int i = 0; i < num_comp_IP; i++) {
                IP_str = IP_str + IP_int[i]; // Vamos poniendo cada componente de la IP
                if (i != (num_comp_IP - 1)) {
                    IP_str = IP_str + ".";
                } // Ponemos los puntos de entre los componentes de la IP
            }
        }
        else {System.out.println("\n ==> ERROR. La dirección IP con : "+este_IP_int.length+" componentes, no es válida.");}

    } // Fin de - public direccion_IP(int este_IP0, int este_IP1, int este_IP2, int este_IP3){

    /**
     * Constructor de la clase (2 de 2)
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2023-10-06
     * @fechaDeUltimaModificacion:
     * @version: 2023-2024-01
     * @param este_IP_str : Es la ip en formato string. Ej: "192.168.22.5"
     * @observaciones:
     *      - A partir del strig obtiene "IP_Bytes" y "IP_int"
     */
    public direccion_IP(String este_IP_str){
        IP_str = este_IP_str;

        try {
            InetAddress ipAddress = InetAddress.getByName(IP_str);
            IP_Bytes = ipAddress.getAddress();
            num_comp_IP = IP_Bytes.length;
            IP_int = new int[num_comp_IP];

            if (num_comp_IP <= 6) {
                for (int i = 0; i < num_comp_IP; i++) {
                    IP_int[i] = IP_Bytes[i] & 0xFF; // Convertir el byte sin signo a un entero.
                }
            }
            else {
                System.out.println("\n ==> La dirección IP no es válida.");
            }
        } catch (Exception e) {
            System.out.println("\n ==> La dirección IP : "+este_IP_str+" - no es válida. ERROR : " + e);
        }
    } // Fin de - public direccion_IP(String este_IP_str){


    /**
     * public String incrementa_IP (int incremento) {
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2023-10-06
     * @fechaDeUltimaModificacion:
     * @version: 2023-2024-01
     * @param incremento : Es la cantidad en enteros que en que se va a incrementar la IP
     * @observaciones:
     *      - Este metodo incrementa la ip en la cantidad qeu indica "incremento".
     *      - EL incremento se realiza en modulo 255 en cada componente de la ip, tratando cada componente
     *           de la ip como un digito de base 255
     */
    public String incrementa_IP (int incremento) {

        int[] IP_incrementada = new int[num_comp_IP];
        int[] incremento_nivel = new int[num_comp_IP];
        incremento_nivel[num_comp_IP-1] = incremento;
        String IP_incrementada_str = "";

        for(int i=num_comp_IP-1; i>=0; i--)
        {
            IP_incrementada[i] = (incremento_nivel[i] + IP_int[i]) % 255;
            if(i>0){incremento_nivel[i-1] = (incremento_nivel[i] + IP_int[i]) / 255;}
        }

        // Convertimos la IP que esta en el array "IP_incrementada" en un strig
        for (int i = 0; i < num_comp_IP; i++) {
            IP_incrementada_str = IP_incrementada_str + IP_incrementada[i]; // Vamos poniendo cada componente de la IP
            if(i != (num_comp_IP -1)){IP_incrementada_str = IP_incrementada_str + ".";} // Ponemos los puntos de entre los componentes de la IP
        }
        return IP_incrementada_str;
    }

    /**
     * public String incrementa_IP (int incremento) {
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2023-10-06
     * @fechaDeUltimaModificacion:
     * @version: 2023-2024-01
     * @param IP_ini_str : Es la IP inicial del rango de IPs en el que debe estar la IP aleatoria que generamos
     * @param IP_fin_str : Es la IP final del rango de IPs en el que debe estar la IP aleatoria que generamos
     * @param multiplo_de : Puede exigirse que la IP sea multiplo de este numero
     *                    - Ej.: multiplo_de = 2 => solo devolvera numeros pares
     *                    - Ej.: multiplo_de = 1 => devolvera cualquier IP
     * @observaciones:
     *      - Este metodo obtiene aleatoriamente una ip de entre un rango de IPs (el existente entre "IP_ini_str" y "IP_fina_str")
     *      - Puede imponerse que la IP generada sea multiplo de un numero concreto
     */
    public String dame_IP_aleatoria (String IP_ini_str, String IP_fin_str, int multiplo_de) {




        String IP_aleatoria= "";
        return IP_aleatoria;
    }

    /**
     * varias para obtener la IP en varios formatos
     * @author MAFG y Varios alumnos 2022-2023
     * @fechaDeCreacion: 2023-10-06
     * @fechaDeUltimaModificacion:
     * @version: 2023-2024-01
     * @observaciones:
     */
    public String dame_IP_string () {return IP_str;}
    public int[] dame_IP_int () {return IP_int;}

} // Fin de - public class direccion_IP {

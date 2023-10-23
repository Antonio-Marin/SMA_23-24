import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class new_agente {
    String id;
    String IP;
    int puerto_UDP;
    int puerto_TCP;
    String origin_time;

    List<new_agente> agentes_conocidos = new ArrayList<new_agente>();
    List<cromo> cromos_deseados = new ArrayList<cromo>();
    List<cromo> cromos_ofertados = new ArrayList<cromo>();
    List<new_mensaje> mensajes = new ArrayList<new_mensaje>();
    int totalMoney;
    int tradeMoney;
    ServerSocket servidor_TCP;
    DatagramSocket servidor_UDP;
}

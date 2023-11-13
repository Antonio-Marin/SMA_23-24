public class AccTest {
    protected String ID_propio; // Identificador unico de este agente
    protected String Ip_Propia;  // Ip donde reside este agente
    protected int Puerto_Propio;  // Es el puerto asociado al agente (coincide con el puerto de servidor TCP del agente)

    public AccTest(String ID_propio, String ip_Propia, int puerto_Propio) {
        this.ID_propio = ID_propio;
        Ip_Propia = ip_Propia;
        Puerto_Propio = puerto_Propio;
    }

    public AccTest(String ID_propio) {
        this.ID_propio = ID_propio;
    }
}

public class Mensaje extends Thread {
    private Agente agenteEmisor;
    private Agente agenteReceptor;
    private String contenidoMensaje;

    public Mensaje(Agente agenteEmisor, Agente agenteReceptor, String contenidoMensaje) {
        this.agenteEmisor = agenteEmisor;
        this.agenteReceptor = agenteReceptor;
        this.contenidoMensaje = contenidoMensaje;
    }

    @Override
    public void run() {
        System.out.println("Enviando mensaje desde " + agenteEmisor.getId() + " a " + agenteReceptor.getId());

        agenteEmisor.enviarMensaje(contenidoMensaje, agenteReceptor);

        String mensajeRecibido = agenteReceptor.recibirMensaje(contenidoMensaje);
        System.out.println("Mensaje recibido por " + agenteReceptor.getId() + ": " + mensajeRecibido);
    }
}



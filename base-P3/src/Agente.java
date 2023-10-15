import java.util.ArrayList;
import java.util.List;

public class Agente {
    int id;
    boolean isFather=false;
    boolean isSon=false;
    int limDesc=5;
    int pid = 0;
    List<String> mensajes = new ArrayList<>();


    public Agente(int id) {
        this.id = id;
    }
    public void nuevoDescendiente(){
        this.limDesc -=1;
    }
    public void borroDescendiente(){
        this.limDesc +=1;
    }

    public void setFather(boolean father) {
        this.isFather = father;
    }

    public void setSon(boolean son) {
        isSon = son;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getId() {
        return this.id;
    }

    public boolean isFather() {
        return isFather;
    }

    public boolean isSon() {
        return isSon;
    }

    public int getLimDesc() {
        return limDesc;
    }

    public int getPid() {
        return pid;
    }
    public void enviarMensaje(String mensaje, Agente receptor) {

        receptor.recibirMensaje(mensaje);
    }

    public String recibirMensaje(String mensaje) {
        mensajes.add(mensaje);
        return mensaje;
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        List<Agente> listaAgentes = new ArrayList<Agente>();
        boolean var = true;
        while(var){
            int op;
            System.out.println("Introduce una opción: \n 1. Crear nuevo agente\n 2. Crear Descendiente\n 3. Mandar Mensaje\n 4. Recibir mensaje\n 5. Eliminar agente\n 6. Ver lista de agentes\n 7. Salir");
            op = s.nextInt();
            if (listaAgentes.size() == 10){
                listaAgentes.remove(0);
            }
            switch(op){
                case 1:
                    System.out.println("Has elegido crear un nuevo agente. Por favor, indique el id de tu nuevo agente");
                    int x1 = 0;
                    int id = s.nextInt();
                    Agente nuevoAgente = new Agente(id);//createAgente(id);
                    for(Agente a : listaAgentes){
                        if (a.getId() == nuevoAgente.getId()){
                            x1 = x1+1;
                        }
                    }
                    if (x1 == 0){
                        System.out.println("El agente no existe. Añadiendo nuevo agente...");
                        listaAgentes.add(nuevoAgente);
                    } else{
                        System.out.println("El agente ya existe. Se cancela la operacion");
                    }

                    break;

                case 2:
                    System.out.println("Has elegido crear descendiente. Por favor, indique el id del padre");
                    int x2 = 0;
                    int x21 = 0;
                    int idPadre = s.nextInt();
                    Agente padre = new Agente(idPadre);//createAgente(idPadre);
                    for(Agente a : listaAgentes){
                        if (a.getId() == padre.getId()){
                            x2 = x2+1;
                            if(a.limDesc == 0){
                                x2 = x2+1;
                            }
                        }
                    }
                    if (x2 == 1){
                        System.out.println("El padre existe, dime el nuevo id de su hijo: ");
                        int idHijo = s.nextInt();
                        Agente hijo = new Agente(idHijo);//createAgente(idHijo);
                        for(Agente a : listaAgentes){
                            if (a.getId() == hijo.getId()){
                                x21 = x21+1;
                            }
                        }
                        if (x21 != 0){
                            System.out.println("Id utilizado, se cancela la operación");
                        } else {
                            System.out.println("Añadiendo nuevo agente...");
                            for(Agente a : listaAgentes){
                                if (a.getId() == padre.getId()){
                                    a.nuevoDescendiente();
                                    a.setFather(true);
                                    hijo.setSon(true);
                                    hijo.setPid(a.id);
                                }
                            }
                            listaAgentes.add(hijo);
                        }
                    } else if (x2 == 2){
                        System.out.println("----------------------------------------");
                        System.out.println("ERROR:El padre no puede tener mas hijos.");
                        System.out.println("----------------------------------------");
                    } else {
                        System.out.println("El padre no existe, se cancela la operación");
                    }
                    break;
                case 3:
                    System.out.println("Has elegido enviar un mensaje");
                    break;
                case 4:
                    System.out.println("Has elegido recibir un mensaje");
                    break;
                case 5:
                    System.out.println("Has elegido eliminar un agente. Elige el id del agente que deseas eliminar: ");
                    int x5 = 0;
                    int idElim = s.nextInt();
                    //Agente elim = new Agente(idElim); //createAgente(idElim);
                    for(Agente a : listaAgentes){
                        if (a.getId() == idElim){
                            x5 = x5+1;
                        }
                    }
                    if (x5 != 0){
                        System.out.println("Agente con id "+ idElim + " eliminado");
                        for (Agente a : listaAgentes){
                            if (a.getId() == idElim){
                                if (a.isSon()){ //a partir de aqui voy a tratar el padre del hijo
                                    int pid = a.getPid();
                                    for (Agente b : listaAgentes){
                                        if (b.getId() == pid){
                                            b.borroDescendiente(); //le devuelve al padre poder crear otro hijo
                                        }
                                    }
                                }
                                listaAgentes.remove(a);
                                break;
                            }
                        }
                    } else{
                        System.out.println("Ese agente no existe. Se cancela la operación.");
                    }
                    break;
                case 6:
                    System.out.println("La lista de agentes es: \n");
                    for (Agente a : listaAgentes){
                        System.out.println("Id: "+a.getId());
                        System.out.println("¿Es padre? "+a.isFather());
                        System.out.println("¿Es hijo? "+a.isSon());
                        if (a.isSon())
                            System.out.println("Id padre: " + a.getPid());
                        System.out.println("Hijos: "+(5-a.getLimDesc())+"\n");

                    }
                    break;
                case 7:
                    var = false;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
    }
}
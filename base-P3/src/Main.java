public class Main {
    import java.util.Scanner;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        while(true){
            int op;
            System.out.println("Introduce una opción: \n 1. Crear nuevo agente\n 2. Crear Descendiente\n 3. Mandar Mensaje\n 4. Recibir mensaje");
            op = s.nextInt();
            switch(op){
                case 1:
                    System.out.println("Opcion 1");
                    break;
                case 2:
                    System.out.println("Opcion 2");
                    break;
                case 3:
                    System.out.println("Opcion 2");
                    break;
                case 4:
                    System.out.println("Opcion 2");
                    break;
                default:
                    System.out.println("Opcion incorrecto");
                    break;
            }
        }
    }
}
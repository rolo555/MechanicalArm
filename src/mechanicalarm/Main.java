/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mechanicalarm;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Stack;

/**
 *
 * @author Rolo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static String mostrarMovimiento(Stack<Integer>[] bloques) {
        StringBuilder movimientoStr = new StringBuilder("");
        int indice = 0;
        for (Stack<Integer> s : bloques) {
            movimientoStr.append(String.valueOf(indice) + ": ");
            for (int i : s) {
                movimientoStr.append(String.valueOf(i) + " ");
            }
            movimientoStr.append("\n");
            indice++;
        }


        return movimientoStr.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int opcion;
        String comando;
        int numeroDePos;
        int indiceComando;
        BrazoMecanicoControlador brazoMecanico = new BrazoMecanicoControlador();
        do {
            System.out.println(" ------------------------------------------");
            System.out.println("|                                          |");
            System.out.println("|    1.- Leer comando desde un archivo     |");
            System.out.println("|    2.- Introducir comandos               |");
            System.out.println("|    3.- Salir                             |");
            System.out.println("|                                          |");
            System.out.println(" ------------------------------------------");
            System.out.print("Seleccione una opcion: ");
            opcion = in.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Por defecto se lee el archivo \"comandos.txt\" en la direccion \"C:\" ");
                    Scanner scanner = new Scanner(new FileInputStream("c:\\comandos.txt"));
                    String numeroDePosStr = scanner.nextLine();
                    numeroDePos = Integer.parseInt(numeroDePosStr);
                    brazoMecanico.ingresarNumeroPosiciones(numeroDePos);
                    while (scanner.hasNext()) {
                        comando = scanner.nextLine();
                        brazoMecanico.ingresarComando(comando);
                    }
                    System.out.println("Iniciando ejecucion:");
                    indiceComando = 0;
                    while (brazoMecanico.existeSiguienteComando()) {
                        System.out.println(brazoMecanico.retornarComando(indiceComando));
                        try {
                            brazoMecanico.ejecutarSiguienteComando();
                        } catch (Exception exp) {
                            System.out.println(exp.getMessage());
                        }
                        System.out.println(mostrarMovimiento(brazoMecanico.retornarTodosLosBloques()));
                        indiceComando++;
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el numero de bloques: ");
                    numeroDePos = in.nextInt();
                    brazoMecanico.ingresarNumeroPosiciones(numeroDePos);
                    String s;
                    System.out.println("Ingrese los comando: ");
                    do {
                        s = in.nextLine();
                        s = s.toLowerCase();
                        brazoMecanico.ingresarComando(s);
                    } while (!s.equals("salir"));
                    indiceComando = 0;
                    while (brazoMecanico.existeSiguienteComando()) {
                        System.out.println(brazoMecanico.retornarComando(indiceComando));
                        try {
                            brazoMecanico.ejecutarSiguienteComando();
                        } catch (Exception exp) {
                            System.out.println(exp.getMessage());
                        }
                        System.out.println(mostrarMovimiento(brazoMecanico.retornarTodosLosBloques()));
                        indiceComando++;
                    }
                    break;
                default:
                    System.out.println("Hasta luego!!!!!");
            }
        } while (opcion != 3);


    }
}

package mechanicalarm;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Ismael Rolando, Lopez Jhenier
 */
public class Main {

    /**
     * Funcino que muestra las posiciones del conjunto con el valor de los bloques correspondientes apilados en cada posicion.
     * @param el conjunto de bloques
     * @return un string que muestra el valor del conjunto de bloques con el formato solicitado
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

    public static void EjecutarComandos(BrazoMecanicoControlador brazoMecanico) {
        System.out.println("Iniciando ejecucion:");
        int indiceComando = 0;
        while (brazoMecanico.existeComando()) {
            System.out.println(brazoMecanico.retornarComando(indiceComando));
            try {
                brazoMecanico.ejecutarComando();
            } catch (Exception exp) {
                System.out.println(exp.getMessage());
            }
            System.out.println(mostrarMovimiento(brazoMecanico.retornarTodosLosBloques()));
            indiceComando++;
        }
    }

    public static void EjecutarOptimizacion(BrazoMecanicoControlador brazoMecanico) {
        System.out.println("Iniciando optimizacion....");
        BrazoMecanicoControlador nuevoBrazoMecanico = new BrazoMecanicoControlador();
        nuevoBrazoMecanico.ingresarNumeroPosiciones(brazoMecanico.retornarCantidadDePosiciones());
        List<Comando> comandosOptimizados = brazoMecanico.optimizarLosComandosActuales();
        System.out.println("Optimizacion terminada....");
        System.out.println("");
        System.out.println("Mostrando nuevos comandos");
        for (Comando comando : comandosOptimizados) {
            System.out.println(comando.TransformarEnString());
            nuevoBrazoMecanico.ingresarComando(comando);
        }
        nuevoBrazoMecanico.ingresarComando(new ComandoSalir());
        System.out.println("");
        System.out.println("Iniciando ejecucion de los comandos optimizados...");
        EjecutarComandos(nuevoBrazoMecanico);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int opcion;
        String comando;
        int numeroDePos;
        String numeroDePosStr;
        String ejecutarOptimizador;
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
            brazoMecanico = new BrazoMecanicoControlador();
            switch (opcion) {
                case 1:
                    System.out.println("Por defecto se lee el archivo \"comandos.txt\" en la direccion \"C:\" ");
                    Scanner scanner = new Scanner(new FileInputStream("c:\\comandos.txt"));
                    numeroDePosStr = scanner.nextLine();
                    numeroDePos = Integer.parseInt(numeroDePosStr);
                    brazoMecanico.ingresarNumeroPosiciones(numeroDePos);
                    while (scanner.hasNext()) {
                        comando = scanner.nextLine();
                        brazoMecanico.ingresarComando(comando);
                    }
                    EjecutarComandos(brazoMecanico);
                    System.out.print("Desea ejecutar el optimizador de comandos (y/n):  ");
                    ejecutarOptimizador = in.next();
                    if (ejecutarOptimizador.equals("y")) {
                        EjecutarOptimizacion(brazoMecanico);
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el numero de bloques: ");
                    numeroDePosStr = in.next();
                    try {
                        numeroDePos = Integer.valueOf(numeroDePosStr);
                        if (numeroDePos > 0 && numeroDePos <= 25) {
                            brazoMecanico.ingresarNumeroPosiciones(numeroDePos);
                            String s;
                            System.out.println("Ingrese los comando: ");
                            do {
                                s = in.nextLine();
                                s = s.toLowerCase();
                                brazoMecanico.ingresarComando(s);
                            } while (!s.equals("salir"));
                            EjecutarComandos(brazoMecanico);
                            System.out.print("Desea ejecutar el optimizador de comandos (y/n):  ");
                            ejecutarOptimizador = in.next();
                            if (ejecutarOptimizador.equals("y")) {
                                EjecutarOptimizacion(brazoMecanico);
                            }
                        } else {
                            System.out.println("Numero de bloques invalido!!!");
                        }
                    } catch (Exception e) {
                        System.out.println("Numero invalido!!!");
                    }
                    break;
                default:
                    System.out.println("Hasta luego!!!!!");
            }

        } while (opcion != 3);
    }
}

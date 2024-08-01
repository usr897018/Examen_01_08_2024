import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio_2 {
    public static void main(String[] args) {
        Scanner datosEntrada = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero origen con su ruta completa:");
        String entrada = datosEntrada.nextLine();
        System.out.println("Introduce el nombre del fichero destino con su ruta completa:");
        String salida = datosEntrada.nextLine();

        int numero = 0;
        while (true) {
            System.out.println("Introduzca un numero para el cifrado entre 1 y 255:");
            numero = datosEntrada.nextInt();
            if (numero >= 1 && numero <= 255) {
                break;
            } else {
                System.out.println("Valor no válido. introduzca un número entre 1 y 255.");
            }
        }

        try {
            codificarFichero(entrada, salida, numero);
            System.out.println("El fichero ha sido cifrado correctamente.");
        } catch (IOException e) {
            System.err.println("Ocurrió un error al cifrar el fichero: " + e.getMessage());
        }
    }

    public static void codificarFichero(String entrada, String salida, int numero) throws IOException {
        try (FileInputStream ficheroEntrada = new FileInputStream(entrada);
             FileOutputStream ficheroSalida = new FileOutputStream(salida)) {

            int leido;
            while ((leido = ficheroEntrada.read()) != -1) {
                ficheroSalida.write(leido ^ numero);
            }
            ficheroEntrada.close();
            ficheroSalida.flush();
            ficheroSalida.close();
        }
    }
}

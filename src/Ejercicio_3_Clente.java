import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Ejercicio_3_Clente {
    public static void main(String[] args) {
        Scanner datosEntrada = new Scanner(System.in);
        String fechaTransaccion;
        String numeroTarjeta;
        String fechaVencimiento;
        String CVV;
        String mensaje;
        String error = "";
        Integer codigo;
        String[] lectura = new String[3];
        System.out.println("introduzca la fecha la fecha de la transaccion con el formato AAAA/MM/DD: ");
        fechaTransaccion = datosEntrada.nextLine();
        System.out.println("introduzca el numero de tarjeta debe tener 16 digitos: ");
        numeroTarjeta = datosEntrada.nextLine();
        System.out.println("introduzca la fechad evencimiento de la tarjeta con el formato AAAA/MM/DD: ");
        fechaVencimiento = datosEntrada.nextLine();
        System.out.println("Introduzca el CVV de la tarjeta debe tener 3 digitos: ");
        CVV = datosEntrada.nextLine();
        mensaje = fechaTransaccion + ',' + numeroTarjeta + ',' + fechaVencimiento + ',' + CVV;

        try (Socket peticion = new Socket("localhost", 3000);
             PrintWriter sSalida = new PrintWriter(peticion.getOutputStream(), true);) {
             sSalida.println(mensaje);

            try (BufferedReader bufferLectura = new BufferedReader(new InputStreamReader(peticion.getInputStream()));) {
                lectura = bufferLectura.readLine().split(",");
                codigo = Integer.parseInt(lectura[1]);
                switch (codigo)
                {
                    case 0000:
                        error = "Transaccion Aceptada";
                        break;
                    case 9137:
                        error = "Error al intentar validar la tarjeta";
                        break;
                    case 9221:
                        error = "El cliente no esta introudciendo el CVV";
                        break;
                    case 9677:
                        error = "Saldo insuficiente";
                        break;
                    default:
                        error = "error desconocido";
                        break;

                }
                System.out.println(error + " en transacion con fecha " + lectura[0] +
                                    " y numero de tarjeta " + lectura[2]);

            }

        } catch (UnknownHostException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}

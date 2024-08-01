import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Ejercicio_3_Servidor {
    public static void main(String[] args) {
        BufferedReader bufferLectura;
        Random random = new Random();
        PrintWriter sRespuesta;
        int numerorandom;
        String respuesta = "";
        String[] lectura = new String[4];
        String codigo = "";
        try (ServerSocket servidor = new ServerSocket(3000);){
            while (true){
                System.out.println("En espera...");
                Socket s1 = servidor.accept();
                bufferLectura = new BufferedReader(new InputStreamReader(s1.getInputStream()));
                lectura = bufferLectura.readLine().split(",");
                numerorandom = random.nextInt(4);
                switch (numerorandom)
                {
                    case 0:
                        codigo = "0000";
                        break;
                    case 1:
                        codigo = "9137";
                        break;
                    case 2:
                        codigo = "9221";
                        break;
                    case 3:
                        codigo = "9677";
                        break;
                }
                respuesta = lectura[0] + ',' + codigo + ',' + lectura[1];
                sRespuesta = new PrintWriter(s1.getOutputStream(),true);
                sRespuesta.println(respuesta);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}

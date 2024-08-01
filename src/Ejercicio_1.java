import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ejercicio_1 {
    public static void main(String[] args) {
        Scanner datosEntrada = new Scanner(System.in);
        System.out.println("Introduce el nombre del fichero con su ruta completa:");
        String filePath = datosEntrada.nextLine();
        File file = new File(filePath);

        if (!file.exists())
        {
            System.out.println("El archivo no existe.");
            return;
        }

        System.out.println("Información del archivo:");
        System.out.println("Nombre: " + file.getName());
        System.out.println("Ruta: " + file.getAbsolutePath());
        System.out.println("¿Es un archivo?: " + file.isFile());
        System.out.println("¿Es un directorio?: " + file.isDirectory());
        System.out.println("¿Es legible?: " + file.canRead());
        System.out.println("¿Es escribible?: " + file.canWrite());
        System.out.println("¿Es ejecutable?: " + file.canExecute());
        System.out.println("Tamaño: " + file.length() + " bytes");

    }
}
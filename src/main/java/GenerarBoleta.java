import java.io.*;
import java.util.Scanner;

public class GenerarBoleta {


    //static File archivo;

    public static void main(String[] args) {
        File archivo = verificarRuta(ingresarRuta());
        try {
            mostrar(llenarArreglo(crearArreglo(archivo),archivo));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String ingresarRuta() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese la ruta del archivo CSV");
        return teclado.nextLine();
    }
    
    public static File verificarRuta(String ruta)  {
        while(ruta.equals(".")){
            ruta = ingresarRuta();
        }
        File archivo = new File(ruta);
        while (!archivo.exists()){
            System.out.println("archivo no encontrado");
            archivo = new File(ingresarRuta());
        }
        return archivo;
    }

    public static String[][] crearArreglo(File archivo)  {
        int n1 = 0;
        try {
            n1 = cantidadProductos(archivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int n2 = 4;
        return new String[n1][n2];
    }

    public static String[][] llenarArreglo(String[][] arr, File archivo) throws IOException {
        BufferedReader leer = new BufferedReader(new FileReader(archivo));
        String [] arreglo;
        int i = 0;
        String linea;
        while ((linea = leer.readLine()) != null){
            arreglo = convertirFormato(linea);
            System.arraycopy(arreglo, 0, arr[i], 0, arreglo.length);
            i++;
        }
        return arr;
    }

    public static String[] convertirFormato(String linea){
        String separador = ";";
        return linea.split(separador);
    }

    public static int cantidadProductos(File archivo) throws IOException {
        BufferedReader leer = new BufferedReader(new FileReader(archivo));
        int k=0;
        while(leer.readLine() != null){
            k++;
        }
        return k;
    }

    public static void mostrar(String [][] arr){
        for (String[] strings : arr) {
            for (String string : strings) {
                System.out.print("[" + string + "] ");
            }
            System.out.println();
        }
    }

    public static int[] totalPorProducto(String[][] arr){
        int [] total = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            total[i] = (Integer.parseInt(arr[i][2]) * Integer.parseInt(arr[i][3]));
        }
        return total;
    }

}

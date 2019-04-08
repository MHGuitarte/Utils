package Utils;

import Classes.Temperaturas;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 *
 * @author M.H. Guitarte <guimhur@gmail.com>
 */
public class Utils {

    public static String leerString() {
        boolean b = true;
        String s = "";
        while (b) {
            try {
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input);
                s = reader.readLine();
            } catch (IOException e) {
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
            if (!s.isEmpty()) {
                b = false;
            } else {
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
        }
        return s;
    }
//Comprueba si el float introducido es NaN. Si no lo es, lo devuelve. Mientras tanto lo seguirá preguntando
    public static float leerFloat() {
        boolean b = true;
        float s = 0;
        while (b) {
            try {
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input);
                s = Float.parseFloat(reader.readLine());

            } catch (IOException e) {
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }

            if (s != Float.NaN) {
                b = false;

            } else {
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
        }
        return s;
    }
/*Creamos un número aleatorio que será la clave de comprobación y le asignamos a
    la variable a devolver el mismo valor aleatorio. Si al introducir los datos
    no se han introducido correctamente, la clave y la variable a devolver
    seguirán almacenando el mismo valor y nos pedirá que lo intentemos de nuevo.*/
    public static int leerInt() {
        boolean b = true;
        Random r = new Random();
        int aux;
        int s = 0;
        while (b) {
            aux = r.nextInt();
            s = aux;

            try {
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input);
                s = Integer.parseInt(reader.readLine());

            } catch (IOException e) {
                s = aux;
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
            if (s != aux) {
                b = false;
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
        }
        return s;
    }
//Comprueba si el double introducido es NaN. Si no lo es, lo devuelve. Mientras tanto lo seguirá preguntando
    public static double leerDouble() {
        boolean b = true;
        double s = 0;
        while (b) {
            try {
                InputStreamReader input = new InputStreamReader(System.in);
                BufferedReader reader = new BufferedReader(input);
                s = Double.parseDouble(reader.readLine());

            } catch (IOException e) {
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
            if (s != Double.NaN) {
                b = false;
            } else {
                System.err.println("Error en la introducción. Inténtelo de nuevo");
            }
        }
        return s;
    }

    public static File newFile() {
        File f;
        System.out.println("Nombre del archivo (el archivo se creará en el directorio raíz de la aplicación)");
        String s = leerString();
        try {
            f = new File(s + ".dat");
            if (!f.exists()) {
                f.createNewFile();
                return f;
            } else {
                return f;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    
//Manipulación básica de ficheros binarios

    public static ObjectOutputStream abrirOOS(File f) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(out);

        return oos;
    }

    public static ObjOutStream abrirOST(File f) throws FileNotFoundException, IOException {
        FileOutputStream out = new FileOutputStream(f, true);
        ObjOutStream ost = new ObjOutStream(out);

        return ost;
    }

    public static ObjectInputStream abrirOIS(File f) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(in);
        return ois;

    }

    public static void cerrarOOS(ObjectOutputStream oos) throws IOException {
        oos.close();
    }

    public static void cerrarOST(ObjOutStream ost) throws IOException {
        ost.close();

    }

    public static void cerrarOIS(ObjectInputStream ois) throws IOException {
        ois.close();
    }
//Fin de manipulación básica de ficheros binarios

//Escritura de Usuario nuevo
    public static void guardarTemperatura(File f, ObjectOutputStream out, ObjOutStream ost, Temperaturas t) throws IOException {

        if (f.length() > 0) {

            ost.writeUnshared(t);

        } else {

            out.writeObject(t);
        }

    }
//Lectura del fichero usuarios.dat

    /*public static LinkedList<CCita> leerCitas(File f, ObjectInputStream ois) throws IOException {

        LinkedList<CCita> listaUsuarios = new LinkedList<>();

        while (true) {
            try {
                CCita u = (CCita) ois.readObject();
                listaUsuarios.add(u);
            } catch (Exception e) {
                System.out.println("Archivo leído");
                ois.close();
                break;
            }
        }

        return listaUsuarios;

    }*/
}

/*
    public static void guardar(File f, Object obj) throws FileNotFoundException, IOException {
        //ESTE MÉTODO NO ES PARA USARSE, SINO PARA COPIARSE CUANDO SE DEBA GUARDAR
        //UNA LISTA DE OBJETOS DE UN ARCHIVO.DAT
        if (!f.exists()) {
            FileOutputStream stream = new FileOutputStream(f);
            ObjectOutputStream output = null;
            try {
                output = new ObjectOutputStream(stream);
                output.writeObject(obj);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                output.close();
            }
        } else {
            FileOutputStream stream = new FileOutputStream(f);
            ObjOutStream output = null;
            try {
                output = new ObjOutStream(stream);
                output.writeUnshared(obj);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                output.close();
            }
        }

    }

    public static ArrayList<Object> abrirLeer(File f) throws FileNotFoundException, IOException {
        //ESTE MÉTODO NO ES PARA USARSE, SINO PARA COPIARSE CUANDO SE DEBA OBTENER
        //UNA LISTA DE OBJETOS DE UN ARCHIVO.DAT

        ArrayList<Object> listas = new ArrayList<>();

        FileInputStream stream = new FileInputStream(f);
        ObjectInputStream input = null;
        try {

            input = new ObjectInputStream(stream);

            Object aux = input.readObject();

            while (aux != null) {
                if (aux instanceof Object) {
                    listas.add(aux);
                }
                aux = input.readObject();
            }
            input.close();
        } catch (EOFException e) {
            System.out.println("Fin de fichero");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } finally {
            input.close();
            return listas;
        }
    }
 */

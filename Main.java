import static java.lang.System.out;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        // Scanner teclado = new Scanner(System.in);
        Identificando i = new Identificando();
      
        try{
            System.out.println("----------Record------------");
            i.record("dados.csv");
            System.out.println("------------------------------");
            System.out.println("------------CPF-----------");
            i.cpfSearch("37925457240");
            System.out.println("------------------------------");
            System.out.println("-----------String------------");
            i.stringInformada("l");
            System.out.println("------------------------------");
            System.out.println("----------------Data------------");
            i.consultaData("30/12/1940", "04/05/1999");
            System.out.println("------------------------------");
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
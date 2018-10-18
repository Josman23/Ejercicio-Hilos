package mx.sintelti.cursos.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestStock {

    public static void main(String[] args) throws IOException {

       Long inicio= System.nanoTime();

       String fileName="/Users/LAP-012/IdeaProjects/Ejercicio threads/src/main/resources/stocks/list.txt";
       List<String> list = Files.readAllLines(Paths.get(fileName));

       for (String fil: list){

           StockRetriever stockRetriever = new StockRetriever(fil);
            new Thread(stockRetriever,String.valueOf(list)).start();

       }

       Long fin = System.nanoTime();

       double totalTiempo = (fin - inicio)/1000000000.0;
       System.out.println("Tiempo Inicio: "+inicio);
       System.out.println("Tiempo Final: "+fin);
       System.out.println("Tiempo de Ejecucion: "+totalTiempo+" segundos");

    }
}

package mx.sintelti.cursos.threads;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestStock {

    private static volatile BigDecimal totalPrice = new BigDecimal(0);

    public static void main(String[] args) {

        long inicio= System.nanoTime();

        String fileName="/Users/LAP-012/IdeaProjects/Ejercicio threads/src/main/resources/stocks/list.txt";

        try{

            int core = Runtime.getRuntime().availableProcessors();
            double blockingCoefficiente = 0.9;
            int poolSize= (int) (core / (1 - blockingCoefficiente));

            System.out.println("CPU Cores: "+core);
            System.out.println("CPU Cores: "+poolSize);

            List<String> lineas= Files.readAllLines(Paths.get(fileName));
            Collection<Callable<Object>> tareas = new ArrayList<>();
            for(String linea: lineas){
                StockRetriever stockRetriever = new StockRetriever(linea.trim());
                tareas.add(Executors.callable(stockRetriever));
            }
            ExecutorService threadPool= Executors.newFixedThreadPool(poolSize);
            threadPool.invokeAll(tareas);
            threadPool.shutdown();

            long fin =System.nanoTime();
            System.out.println("Tiempo total de ejecucion: "+((fin-inicio)/1000000000.0)+" segundos.");

        }catch (IOException e){
            System.out.println("Error al manipular archivo");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Total del mercado: "+ totalPrice);

    }

    public static synchronized void addPrice(BigDecimal price){
        totalPrice=totalPrice.add(price);
    }
}

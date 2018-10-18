package mx.sintelti.cursos.threads.threadSimple;

public class Tarea implements Runnable{

    @Override
    public void run() {
        System.out.println(
                "Una tarea Muy importante se esta efectuando... - "
                + Thread.currentThread().getName()
         );
    }
}

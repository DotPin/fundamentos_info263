import java.util.*;

abstract class Logger {
   public static int ERR = 3;
   public static int NOTICE = 5;
   public static int DEBUG = 7;
   protected int mask;


   protected Logger next; // el siguiente elemento en la cadena
   
   public Logger setNext(Logger l) { 
    next = l; 
    return this; 
   }


   abstract public void message(String msg, int priority);
}


class DebugLogger extends Logger{
   public DebugLogger(int mask) { this.mask = mask; }


   public void message(String msg, int priority) {
       if (priority <= mask) System.out.println("Escribiendo en DEBUG: " + msg +" mask: "+mask);
       if (next != null) next.message(msg, priority);
   }
}


class EMailLogger extends Logger{
   public EMailLogger(int mask) { this.mask = mask; }


   public void message(String msg, int priority) {
       if (priority <= mask) System.out.println("Enviando un  e-mail: " + msg+" mask: "+mask);
       if (next != null) next.message(msg, priority);
   }
}


class StderrLogger extends Logger{
   public StderrLogger(int mask) { this.mask = mask; }


   public void message(String msg, int priority) {
       if (priority <= mask) System.out.println("Escribiendo en STDERR: " + msg+" mask: "+mask);
       if (next != null) next.message(msg, priority);
   }
}


class ej1{
   public static void main(String[] args) {
       // Construimos la cadena
       // DebugLogger(DEBUG = 7) => EMailLogger(Error = 3) => StderrLogger(Notice = 5)
       Logger l = new DebugLogger(Logger.DEBUG).setNext(new EMailLogger(Logger.ERR).setNext(new StderrLogger(Logger.NOTICE) ) );


       // Ejecutamos
       l.message("Entrando en function y.", Logger.DEBUG); // manejado por DebugLogger
       l.message("paso 1 completado.", Logger.NOTICE);     // manejado por DebugLogger y StderrLogger
       l.message("Ha ocurrido un error.", Logger.ERR);     // menejado por los tres Logger
   }
}
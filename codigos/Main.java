import java.util.*;


abstract class Manejador{
    
    protected Manejador siguienteManejador;
    
    public Manejador getSiguiente() {
        return this.siguienteManejador;
    }
    
    public void setSiguiente(Manejador m) {
        this.siguienteManejador = m;
    }
    
     public abstract void comprobar(String estado);
}


class ManejadorPendiente extends Manejador{
    public ManejadorPendiente() {
    }
    
    public void comprobar(String estado)
    {
       if( estado == null )
       {
           System.out.println("Solicitud pendiente");
       }
       else
       {
             if( this.getSiguiente() != null )
            {
                // Llamamos al método en el siguiente objeto
                 this.getSiguiente().comprobar( estado );
            }
       }
    }
}


class ManejadorDenegado extends Manejador{
    public ManejadorDenegado() {
    }
    
    
     public void comprobar(String estado)
    {
       if( (estado != null) && (estado.equalsIgnoreCase("DENEGADO") == true) )
       {
           System.out.println("Solicitud denegada");
       }
       else
       {
             if( this.getSiguiente() != null )
            {
                // Llamamos al método en el siguiente objeto
                 this.getSiguiente().comprobar( estado );
            }
       }
    }
}


class ManejadorAprobado extends Manejador{
    public ManejadorAprobado() {
    }
    
     public void comprobar(String estado)
    {
       if( (estado != null) && (estado.equalsIgnoreCase("APROBADO") == true) )
       {
           System.out.println("Solicitud aprobada");
       }
       else
       {
             if( this.getSiguiente() != null )
            {
                // Llamamos al método en el siguiente objeto
                 this.getSiguiente().comprobar( estado );
            }
       }
    }
}


class Main{
    public static void main(String[] args){
        Manejador m1 = new ManejadorAprobado();
        Manejador m2 = new ManejadorDenegado();
        Manejador m3 = new ManejadorPendiente();

        m1.setSiguiente( m2 );
        m2.setSiguiente( m3 );

        m1.comprobar("APROBADO");
        m1.comprobar("APROBADO");
        m1.comprobar("DENEGADO");
        m1.comprobar(null);
        m1.comprobar("DENEGADO");
    }
}

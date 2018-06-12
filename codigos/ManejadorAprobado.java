public class ManejadorAprobado extends Manejador
{
    public ManejadorAprobado() {
    }
    // --------------------------------------------------
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
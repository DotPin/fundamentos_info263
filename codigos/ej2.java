// NOTA: la clase Mensaje no está implemntada por simplicidad e irrelevancia 

// Mensaje tiene los métodos siguientes:

//    Mensaje(String codificacion, String text);

//    String obtenerCodificacion();

//    String desencriptar(String key);



// En primer lugar definiremos la clase abstracta ManejadorMensaje

abstract class ManejadorMensaje{

        protected ManejadorMensaje proximo;



        public void establecerProximo(ManejadorMensaje proximo){

                this.proximo = proximo;

        }



    public abstract void manejarMensaje(Mensaje msg);

}



// Seguiremos con el manejador concreto PolicíaEstándar

class PoliciaEstandar extends ManejadorMensaje{

        private String key = "0069A0";

        @Override

        public void manejarMensaje(Mensaje msg){

                if(msg.obtenerCodificacion().equals("base"))

                        System.out.println(desencriptar(key));

                else

                        this.proximo.manejarMensaje(msg);

        }

}



// Otro manejador concreto PolicíaEspecial

class PoliciaEspecial extends ManejadorMensaje{

        private String key = "0A89E4";

        @Override

        public void manejarMensaje(Mensaje msg){

                if(msg.obtenerCodificacion().equals("especial"))

                        System.out.println(desencriptar(key));

                else

                        this.proximo.manejarMensaje(msg);

        }

}



// Por ultimo crearemos PolicíaElite

class PoliciaElite extends ManejadorMensaje{

        private String master_key = "F0FEF2";

        @Override

        public void manejarMensaje(Mensaje msg){

                System.out.println(desencriptar(master_key));

        }

}



// Ahora crearemos la clase principal de nuestro cliente

// Le pasará el mensaje codificado a la cadena de policías
class ej2{
    public static void main(String [] args){

        ManejadorMensaje policia1 = new PoliciaEstandar();

        ManejadorMensaje policia2 = new PoliciaEspecial();

        ManejadorMensaje policia3 = new PoliciaElite();



        policia1.establecerProximo(policia2);

        policia2.establecerProximo(policia3);



        Mensaje mensaje = new Mensaje("especial", "Este mensaje va para la policía especial");



        policia1.manejarMensaje(mensaje);



        mensaje = new Mensaje("top-secret", "Este mensaje va para la policía de élite");

        policia1.manejarMensaje(mensaje);

    }
}
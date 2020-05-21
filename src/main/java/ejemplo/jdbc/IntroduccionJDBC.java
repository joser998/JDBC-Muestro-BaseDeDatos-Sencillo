/* Establecemos conexion con la Base de Datos que ya hallamos creado y mostramos las variables de la base de datos aqui por 
    consola...   */
package ejemplo.jdbc;
import java.sql.*;

public class IntroduccionJDBC {
    public static void main(String[] args) {
        /* 1.- Creamos nuestra cadena de Conexion a MySQL  */
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
        
            /* 2.- Creamos el objeto de conexion a la base de datos   */
            //Usamos la clase: DriverManager
            //Usamos el metodo: getConnection()
            /*Al metodo le pasamos como parametro la cadena de conexion osea url, el nombre de usuario que es root y el password
            para conectarse a la base de datos que es: admin */
            
              /* Es necesario aplicar metodo Try-Catch por cualquier error de tipo SQL que se pudiera presentar... */
        try {
            Connection conexion = DriverManager.getConnection(url,"root","admin");
            
            /* 3.- Mandamos a crear un objeto de tipo Statement (sentencia) para poder conectarnos a la base de datos y ejecutar 
                    una sentencia SQL */
            Statement instruccion = conexion.createStatement();
            
            /* 4.- Creamos la sentencia de nuestro Query */
            String sql = "select id_persona, nombre, apellido, email, telefono from persona";
            
            /* 5.- Ejecucion del Query */
            ResultSet resultado = instruccion.executeQuery(sql);
            
            /* 6.- Procesamos el resultado mediante un ciclo while para recorrer cada uno de los registros que contiene esta 
                    variable ResultSet   */
            while(resultado.next()){
                //Aqui se accede a cada una de las columnas que hemos ejecutado
                System.out.print("Id Persona: "+resultado.getInt(1));
                System.out.print(" Nombre: "+resultado.getString(2));
                System.out.print(" Apellido: "+resultado.getString(3));
                System.out.print(" Email: "+resultado.getString(4));
                System.out.println(" Telefono: "+resultado.getString(5));
            }
            
            /* 7.- Cerramos cada objeto que hemos utilizado, pues podria haber problemas en la conexion si no lo hacemos*/
                resultado.close();
                instruccion.close();
                conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out); //--En caso de cualquier error que suceda de este tipo mandamos a 
        }                                      //imprimir la excepcion a la consola mediante: System.out
    }
}
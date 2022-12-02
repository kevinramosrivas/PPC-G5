
package Connection;

import java.sql.*;
import java.util.*;
import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionPool {
    private final String DB="g5-ppc_db";
    private final String URL="jdbc:mysql://mysql-g7ppc.alwaysdata.net/"+DB+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER="g5-ppc";
    private final String PASS="g5-ppc-pass";
    
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource=null;
    
    public ConnectionPool(){
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);
        
    }
    
    public static ConnectionPool getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPool();
        } 
        return dataSource;
    }

    public Connection getConnection() throws SQLException{
        return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    
    public void closeResultSet(ResultSet resultado){
         try{
             resultado.close();
         }catch(SQLException error){
             System.out.println(error);
         }
     }
     
     public void closeStatement(PreparedStatement statement){
         try{
             statement.close();
         }catch(SQLException error){
             System.out.println(error);
         }
     }
    
    public List<Map<String, Object>> makeConsult(String sql) throws SQLException{
        ConnectionPool instancia = ConnectionPool.getInstance();
        Connection conexion = null; 
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> row = null;
        try{              
            conexion = instancia.getConnection();
            consulta = conexion.prepareStatement(sql);
            resultado = consulta.executeQuery();
            ResultSetMetaData metaData = resultado.getMetaData();
            Integer columnCount = metaData.getColumnCount();
             
            while (resultado.next()) {
                row = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), resultado.getObject(i));
                }
                resultList.add(row);
            }
            return (resultList);

        }finally{
            instancia.closeStatement(consulta);
            instancia.closeResultSet(resultado);
            instancia.closeConnection(conexion);
        }
        
    }
    public void makeUpdate(String sql) throws SQLException{
        ConnectionPool instancia = ConnectionPool.getInstance();
        Connection conexion = null; 
        PreparedStatement consulta = null;
        try{              
            conexion = instancia.getConnection();
            consulta = conexion.prepareStatement(sql);
            consulta.executeUpdate();


        }finally{
            instancia.closeStatement(consulta);
            instancia.closeConnection(conexion);
        }
        
    }
}

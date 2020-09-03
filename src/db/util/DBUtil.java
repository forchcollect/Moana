/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author STU-05
 */
public class DBUtil {
    
    //static 초기화 블럭
    static {
        //드라이버 로딩
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("드라이버 로딩 성공!");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패!");
            e.printStackTrace();
        }
    }//static
    
    
    //* Connection 객체 - 매개변수 3개
    public static Connection getConnection(String url, String user,
            String upwd) throws SQLException {
        Connection con=DriverManager.getConnection(url, user, upwd);
        System.out.println("db연결 여부 con="+con);
        return con;
    }
    
    //오버로딩 - 매개변수 2개
    public static Connection getConnection(String user, String upwd)
            throws SQLException {
        String url="jdbc:oracle:thin:@user-PC:1521:xe";
        
        Connection con=getConnection(url, user, upwd);
        return con;
    }
    
    //오버로딩 - 매개변수 0개
    public static Connection getConnection() throws SQLException {
        String url="jdbc:oracle:thin:@user-PC:1521:xe";
        String user="semipch1", upwd="semipch123";
        
        return getConnection(url, user, upwd);
    }
    
    
    //* 자원반납 - 매개변수 2개
    public static void dbClose(PreparedStatement ps, Connection con)
            throws SQLException {
        if(ps!=null) ps.close();
        if(con!=null) con.close();
    }
    
    //오버로딩 - 매개변수 3개(ResultSet 있는 경우)
    public static void dbClose(ResultSet rs, PreparedStatement ps,
            Connection con) throws SQLException {
        if(rs!=null) rs.close();
        if(ps!=null) ps.close();
        if(con!=null) con.close();
    }
}

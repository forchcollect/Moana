/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.table;

import db.util.DBUtil;
import db.view.v_cartDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class OrderbookDAO {


    public void insertData(int ordernum, ArrayList<v_cartDTO> list) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        
        try{
            con=DBUtil.getConnection();

            int cnt=0;
            for(int i=0;i<list.size();i++) {
                v_cartDTO VCdto=list.get(i);

                String ISBN13=VCdto.getISBN13();
                int qty=VCdto.getQty();

                //insert orderbook 처리
                String sql="insert into orderbook" +
                " values(?, ?, ?)";
                ps=con.prepareStatement(sql);
                ps.setInt(1, ordernum);
                ps.setInt(2, qty);
                ps.setString(3, ISBN13);
                int vCnt=ps.executeUpdate();
                cnt+=vCnt;
                
                //주문을 했으니 재고 변경
                sql="update book" +
                " set qty=10-?" +
                " where ISBN13=?";
                
                ps=con.prepareCall(sql);
                ps.setInt(1, qty);
                ps.setString(2, ISBN13);
                
                ps.executeUpdate();
            }
                System.out.println("orderbook 처리 결과: 성공!, int값: "+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public void deleteUser(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();
            
            String sql="delete from orderbook" +
                    " where ordernum in (" +
                    " select ordernum from ordersend" +
                    " where userid=?)";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);

            int cnt=ps.executeUpdate();
            System.out.println("주문도서 삭제 결과: cnt="+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }
}

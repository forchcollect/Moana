/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.table;

import db.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class OrdersendDAO {
    ArrayList<CartDTO> dto;

    public int insertData(OrdersendDTO dto) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBUtil.getConnection();
            
            String userid=dto.getUserid();
            String name=dto.getName();
            String addr=dto.getAddress();
            String tel=dto.getTel();
            
            String sql="insert into ordersend" +
                " values(seq_ordernum.nextval, ?, ?, ?, ?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, name);
            ps.setString(3, addr);
            ps.setString(4, tel);

            int cnt=ps.executeUpdate();
            System.out.println("ordersend 처리 결과: 성공!, int값: "+cnt);
            
            
            //ordernum 구하기
            sql="select seq_ordernum.currval from dual";
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            
            int ordernum=0;
            while(rs.next()) {
                ordernum=rs.getInt(1);
            }
            System.out.println("주문번호= "+ordernum);
            return ordernum;
            
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public void deleteUser(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();
            
            String sql="delete from ordersend where userid=?";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);

            int cnt=ps.executeUpdate();
            System.out.println("주분받는분 삭제 결과: cnt="+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }
    
}

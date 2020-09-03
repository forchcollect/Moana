/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.view;

import db.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class v_historyDAO {

    public ArrayList<v_historyDTO> selectAll(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
       
        
        try{
            con=DBUtil.getConnection();
            
            System.out.println("v_historyDAO에서 userid=hong");
            
            String sql="select * from v_history where userid=? order by ordernum desc";
            ps=con.prepareStatement(sql);
            ps.setString(1, userid);
            rs=ps.executeQuery();
            ArrayList<v_historyDTO> list=new ArrayList<>();
            
            while(rs.next()) {
                int ordernum=rs.getInt("ordernum");   
                String title=rs.getString("title");
                int qty=rs.getInt("qty");
                String ISBN13=rs.getString("ISBN13");
                String name=rs.getString("name");
                String addr=rs.getString("address");
                String tel=rs.getString("tel");

                v_historyDTO dto=new v_historyDTO();
                dto.setOrdernum(ordernum);
                dto.setTitle(title);
                dto.setQty(qty);
                dto.setISBN13(ISBN13);
                dto.setName(name);
                dto.setAddr(addr);
                dto.setTel(tel);
                
                list.add(dto);
            }
            System.out.println("주문내역 보기: "+list.size());
            return list;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }
;
    
    
}

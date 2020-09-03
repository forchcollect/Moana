/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.table;

import db.util.DBUtil;
import db.view.qtyTempDTO;
import db.view.v_cartDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class CartDAO {
    
    public CartDAO() {
    }
    

    public ArrayList selectAll(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<v_cartDTO> list=new ArrayList<>();
        
        try{
            con=DBUtil.getConnection();

            String sql="select * from v_cart where userid=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, userid);

            rs=ps.executeQuery();
            while(rs.next()) {
                String ISBN13=rs.getString("ISBN13");
                String title=rs.getString("title");
                int price=rs.getInt("price");
                int qty=rs.getInt("qty");

                v_cartDTO dto=new v_cartDTO();
                dto.setISBN13(ISBN13);
                dto.setTitle(title);
                dto.setPrice(price);
                dto.setQty(qty);
                
                list.add(dto);
            }
            System.out.println("장바구니 전체 보기: "+list.size());
            return list;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }
    
     public int selectCart(String userid, String ISBN13) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBUtil.getConnection();

            String sql="select * from v_cart where userid=? and ISBN13=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, ISBN13);

            rs=ps.executeQuery();
            
            boolean found = rs.next();
            if (found) {
                System.out.println("cart에 동일 데이터가 존재함");
                return 1; //동일 데이터가 이미 존재함
            }
            else{
                System.out.println("cart에 동일 데이터가 존재하지 않음");
                return 0; //동일 데이터가 존재하지 않음 = insert 가능함
            }
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }
    
    public void insertCart(String userid, String ISBN13, int qty) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();

            String sql="insert into cart(userid, ISBN13, qty)" +
                    " values(?, ?, ?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, ISBN13);
            ps.setInt(3, qty);

            int cnt=ps.executeUpdate();
            System.out.println("insertCart 처리 결과: 성공!, int값: "+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public int updateCart(String userid, String ISBN13, int qty) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();

            String sql="update cart" +
                        " set qty=?" +
                        " where userid=?" +
                        " and ISBN13=?";
            ps=con.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setString(2, userid);
            ps.setString(3, ISBN13);

            int cnt=ps.executeUpdate();
            System.out.println("장바구니 수량 변경 결과: cnt="+cnt);
            return cnt;
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public int deleteBook(String userid, String ISBN13) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        
        try{
            con=DBUtil.getConnection();

            String sql="delete from cart where ISBN13=? and userid=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, ISBN13);
            ps.setString(2, userid);

            int cnt=ps.executeUpdate();
            
            System.out.println("장바구니 삭제 결과: cnt="+cnt);
            return cnt;
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public void deleteAll(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        
        try{
            con=DBUtil.getConnection();

            String sql="delete from cart";
            ps=con.prepareStatement(sql);

            int cnt=ps.executeUpdate();
            
            System.out.println("장바구니 전체 삭제 결과: cnt="+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public ArrayList isEnoughQty(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBUtil.getConnection();

            String sql="select b.title as title, b.qty as bQty, c.qty as cQty "
                    + "from book b join v_cart c" +
                        " on b.ISBN13=c.ISBN13" +
                        " where b.qty < c.qty" +
                        " and userid=?";
            ps=con.prepareStatement(sql);
            ps.setString(1, userid);

            rs=ps.executeQuery();
            ArrayList<qtyTempDTO> list = new ArrayList<>();
            while(rs.next()) {
                String title=rs.getString("title");
                int bQty =rs.getInt("bQty");
                int cQty=rs.getInt("cQty");

                qtyTempDTO dto=new qtyTempDTO();
                dto.setTitle(title);
                dto.setbQty(bQty);
                dto.setcQty(cQty);
                
                list.add(dto);
            }
            System.out.println("주문 수량이 재고 수량보다 큰 건 수: "+list.size());
            
            return list;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }

    public void deleteUser(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();
            
            String sql="delete from cart where userid=?";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);

            int cnt=ps.executeUpdate();
            System.out.println(userid+"의 장바구니 삭제 결과: cnt="+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

}

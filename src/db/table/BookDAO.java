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
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class BookDAO {
    
    public ArrayList<BookDTO> selectKind(String selKind) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<BookDTO> list=new ArrayList<>();

        try{
            con=DBUtil.getConnection();
            String sql="";
            if(selKind.equals("전체")) {
                sql="select * from book";

                ps=con.prepareStatement(sql);
            }else{
                sql="select * from book where kind=?";

                ps=con.prepareStatement(sql);
                ps.setString(1, selKind);
            }

            rs=ps.executeQuery();

            while(rs.next()) {
                String title=rs.getString("title");
                String author=rs.getString("author");
                String publisher=rs.getString("publisher");
                int price=rs.getInt("price");
                Timestamp publisherDate=rs.getTimestamp("publisherdate");
                String ISBN=rs.getString("ISBN13");

                BookDTO dto=new BookDTO(title, author, publisher, price, publisherDate, ISBN);

                list.add(dto);
            }
            System.out.println("select kind:"+selKind+" 조회 결과: list.size="+list.size());
            return list;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }

    public ArrayList<BookDTO> selectWord(String source, String word) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ArrayList<BookDTO> list=new ArrayList<>();

        try{
            con=DBUtil.getConnection();
            String sql="";
            if(source.equals("searchAll")) {
                sql="select * from book" +
                        " where title like '%' || ? || '%'"+
                        " or author like '%' || ? || '%'" +
                        " or publisher like '%' || ? || '%'" +
                        " or ISBN13 like '%' || ? || '%'";

                ps=con.prepareStatement(sql);

                ps.setString(1, word);
                ps.setString(2, word);
                ps.setString(3, word);
                ps.setString(4, word);
            }

            if(source.equals("searchTitle")) {
                sql="select * from book" +
                        " where title like '%' || ? || '%'";

                ps=con.prepareStatement(sql);
                ps.setString(1, word);
            }

            if(source.equals("searchAuthor")) {
                sql="select * from book" +
                        " where author like '%' || ? || '%'";

                ps=con.prepareStatement(sql);
                ps.setString(1, word);
            }

            if(source.equals("searchPublisher")) {
                sql="select * from book" +
                        " where publisher like '%' || ? || '%'";

                ps=con.prepareStatement(sql);
                ps.setString(1, word);
            }

            if(source.equals("searchISBN13")) {
                sql="select * from book" +
                        " where ISBN13 like ? || '%'";

                ps=con.prepareStatement(sql);
                ps.setString(1, word);
            }

            rs=ps.executeQuery();

            while(rs.next()) {
                String title=rs.getString("title");
                String author=rs.getString("author");
                String publisher=rs.getString("publisher");
                int price=rs.getInt("price");
                Timestamp publisherDate=rs.getTimestamp("publisherdate");
                String ISBN=rs.getString("ISBN13");

                BookDTO dto=new BookDTO(title, author, publisher, price, publisherDate, ISBN);

                list.add(dto);
            }
            System.out.println(source+" 결과 조회: list.size="+list.size());
            return list;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }

    public BookDTO selectISBN(String ISBN13) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        BookDTO dto=null;
        
        try{
            con=DBUtil.getConnection();
                String sql="select * from book" +
                        " where ISBN13=?";

                ps=con.prepareStatement(sql);
                ps.setString(1, ISBN13);

            rs=ps.executeQuery();

            while(rs.next()) {
                dto=new BookDTO();
                dto.setTitle(rs.getString("title"));
                dto.setAuthor(rs.getString("author"));
                dto.setPublisher(rs.getString("publisher"));
                dto.setFullprice(rs.getInt("fullprice"));
                dto.setPrice(rs.getInt("price"));
                dto.setPublisherDate(rs.getTimestamp("publisherdate"));
                dto.setKind(rs.getString("kind"));
                dto.setISBN13(rs.getString("ISBN13"));
                dto.setQty(rs.getInt("qty"));

            }
            System.out.println("ISBN 조회 결과: ISBN13="+ISBN13);
            return dto;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }

    public int isEnoughQty(String ISBN13, int qty) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        
        try{
            con=DBUtil.getConnection();

            String sql="select * from book" +
                        " where qty<?" +
                        " and ISBN13=?";
            ps=con.prepareStatement(sql);
            ps.setInt(1, qty);
            ps.setString(2, ISBN13);

            rs=ps.executeQuery();
            
            int bQty=0;
                    
            while(rs.next()) {
                bQty=rs.getInt("qty");
            }
            
            if(rs.next()){
                System.out.println("주문 수량이 재고 수량보다 큰 상태!!!");
            }
            
            return bQty;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }
    }

}

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

/**
 *
 * @author STU-05
 */
public class MemberDAO {
    
    public static int addMember(MemberDTO dto) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();

            String sql="insert into member" +
                        " values(?, ?, ?, ?, ?, ?, ?)";
            ps=con.prepareStatement(sql);
            ps.setString(1, dto.getUserid());
            ps.setString(2, dto.getPwd());
            ps.setString(3, dto.getName());
            ps.setString(4, dto.getTel());
            ps.setString(5, dto.getEmail());
            ps.setString(6, dto.getAddress1());
            ps.setString(7, dto.getAddress2());

            int cnt=ps.executeUpdate();
            System.out.println("addMember 처리 결과: 성공!, int값: "+cnt);
            return cnt;
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public int login(String userid, String pwd) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
     
            //1, 2
            con=DBUtil.getConnection();
            
            //3.
            String sql="select pwd from member where userid=?";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);
           
            //4.
            rs=ps.executeQuery();
            int result=0;
            if(rs.next()) { //아이디가 존재할 때
                String dbPwd=rs.getString(1);
                System.out.println("dbPwd="+dbPwd);
                System.out.println("pwd="+pwd);
                if(dbPwd.equals(pwd)) { //비밀번호가 일치할 때
                    result=1;
                }else{
                    result=2; //비밀번호가 일치하지 않을 때
                }//안if
            }else{ //아이디가 존재하지 않을 때
                result=3;
            }//밖if
            System.out.println("로그인 체크 결과, result="+result+", 매개변수 userid="+userid+", pwd="+pwd);
            
            return result;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }    
    }

    public MemberDTO selectByUserid(String userid) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MemberDTO dto=new MemberDTO();
        
        try{
     
            //1, 2
            con=DBUtil.getConnection();
            
            //3.
            String sql="select * from member where userid=?";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);
           
            //4.
            rs=ps.executeQuery();
            
                
            while(rs.next()) { 
                String pwd=rs.getString("pwd");
                String name=rs.getString("name");
                String tel=rs.getString("tel");
                String email=rs.getString("email");
                String address1=rs.getString("address1");
                String address2=rs.getString("address2");
                
                dto.setAddress1(address1);
                dto.setAddress2(address2);
                dto.setEmail(email);
                dto.setName(name);
                dto.setPwd(pwd);
                dto.setTel(tel);
                dto.setUserid(userid);
                
             
            }
            return dto;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }    
    }
    
    public boolean selectByUseridReturnBool(String userid) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MemberDTO dto=new MemberDTO();
        
        try{
     
            //1, 2
            con=DBUtil.getConnection();
            
            //3.
            String sql="select * from member where userid=?";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);
           
            //4.
            rs=ps.executeQuery();
            
            boolean isUseridDup=true;
            if(rs.next()) {
                isUseridDup=false;
                System.out.println("중복되는 아이디");
            }else{
                System.out.println("사용 가능한 아이디");
            }
            return isUseridDup;
        }finally{
            DBUtil.dbClose(rs, ps, con);
        }    
    }

    public int updateMember(MemberDTO dto) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();
            
            String sql="update member" +
                        " set tel=?, email=?, address1=?, address2=?";
            ps=con.prepareStatement(sql);
            
            String tel=dto.getTel();
            String email=dto.getEmail();
            String address1=dto.getAddress1();
            String address2=dto.getAddress2();
            
            
            ps.setString(1, tel);
            ps.setString(2, email);
            ps.setString(3, address1);
            ps.setString(4, address2);

            int cnt=ps.executeUpdate();
            System.out.println("회원정보 수정 결과: cnt="+cnt);
            return cnt;
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }

    public void deleteUser(String userid) throws SQLException {
        Connection con=null;
        PreparedStatement ps=null;
        try{
            con=DBUtil.getConnection();
            
            String sql="delete from member where userid=?";
            ps=con.prepareStatement(sql);
            
            ps.setString(1, userid);

            int cnt=ps.executeUpdate();
            System.out.println("회원정보 삭제 결과: cnt="+cnt);
        }finally{
            DBUtil.dbClose(ps, con);
        }
    }
    
}

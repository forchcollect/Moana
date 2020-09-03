/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.table;

/**
 *
 * @author STU-05
 */
public class MemberDTO {
    String userid;
    String pwd;
    String name;
    String email;
    String tel;
    String address1;
    String address2;

    public MemberDTO() {
    }

    public MemberDTO(String userid) {
        this.userid = userid;
    }

    public MemberDTO(String userid, String pwd, String name, String email, String tel, String address1, String address2) {
        this.userid = userid;
        this.pwd=pwd;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.address1 = address1;
        this.address2 = address2;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

   
}

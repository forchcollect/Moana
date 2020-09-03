/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.table;

/**
 *
 * @author user
 */
public class OrdersendDTO {
    int ordernum;
    String userid;
    String name;
    String address;
    String tel;
    

    public OrdersendDTO() {
    }

    public OrdersendDTO(String name, String address, String tel) {
        this.name = name;
        this.address = address;
        this.tel = tel;
    }
  
    public int getOrdernum() {
        return ordernum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    
}

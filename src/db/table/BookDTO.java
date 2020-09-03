/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.table;

import java.sql.Timestamp;

/**
 *
 * @author user
 */
public class BookDTO {
    String kind;
    String title;
    String ISBN;
    String ISBN13;
    String ISBN5;
    String publisher;
    String author;
    int fullprice;
    int price;
    int discount;
    String discountPercent;
    Timestamp publisherDate;
    int qty;

    public BookDTO() {
    }

    public BookDTO(String title) {
        this.title = title;
    }

    public BookDTO(String title, String author, String publisher, int price, Timestamp publisherDate, String ISBN13) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.publisherDate=publisherDate;
        this.ISBN13=ISBN13;
    }

    public BookDTO(String kind, String title, String ISBN13, String publisher, String author, int fullprice, int price, int discount, String discountPercent, Timestamp publisherDate, int qty) {
        this.kind = kind;
        this.title = title;
        this.ISBN13 = ISBN13;
        this.publisher = publisher;
        this.author = author;
        this.fullprice = fullprice;
        this.price = price;
        this.discount = discount;
        this.discountPercent = discountPercent;
        this.publisherDate = publisherDate;
        this.qty = qty;
    }
    
    
    
    

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getISBN5() {
        return ISBN5;
    }

    public void setISBN5(String ISBN5) {
        this.ISBN5 = ISBN5;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFullprice() {
        return fullprice;
    }

    public void setFullprice(int fullprice) {
        this.fullprice = fullprice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Timestamp getPublisherDate() {
        return publisherDate;
    }

    public void setPublisherDate(Timestamp publisherDate) {
        this.publisherDate = publisherDate;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    
}

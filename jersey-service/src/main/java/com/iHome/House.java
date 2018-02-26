/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iHome;

import java.util.Date;

/**
 *
 * @author kskoul
 */
public class House {

    private int idHouse;
    private String adress;
    private String telephone;
    private String location;
    private String square_feet;
    private int availability;
    private int number_of_people;
    private String price_per_day;
    private int payment_credit;
    private int payment_cash;
    private String county;
    private int owner;
    private float total_rating;
    private Date from_date;
    private Date to_date;
    private String desc;


    public House(int idHouse, String adress, String telephone, String location, String square_feet, int availability, int number_of_people, String price_per_day, int payment_credit, int payment_cash, String county, int owner, float total_rating, Date from_date, Date to_date, String desc) {
        this.idHouse = idHouse;
        this.adress = adress;
        this.telephone = telephone;
        this.location = location;
        this.square_feet = square_feet;
        this.availability = availability;
        this.number_of_people = number_of_people;
        this.price_per_day = price_per_day;
        this.payment_credit = payment_credit;
        this.payment_cash = payment_cash;
        this.county = county;
        this.owner = owner;
        this.total_rating = total_rating;
        this.from_date = from_date;
        this.to_date = to_date;
        this.desc = desc;
    }

    public House(String location) {
        this.location = location;
    }

    public int getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(int idHouse) {
        this.idHouse = idHouse;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSquare_feet() {
        return square_feet;
    }

    public void setSquare_feet(String square_feet) {
        this.square_feet = square_feet;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getNumber_of_people() {
        return number_of_people;
    }

    public void setNumber_of_people(int number_of_people) {
        this.number_of_people = number_of_people;
    }

    public String getPrice_per_day() {
        return price_per_day;
    }

    public void setPrice_per_day(String price_per_day) {
        this.price_per_day = price_per_day;
    }

    public int getPayment_credit() {
        return payment_credit;
    }

    public void setPayment_credit(int payment_credit) {
        this.payment_credit = payment_credit;
    }

    public int getPayment_cash() {
        return payment_cash;
    }

    public void setPayment_cash(int payment_cash) {
        this.payment_cash = payment_cash;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public float getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(float total_rating) {
        this.total_rating = total_rating;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

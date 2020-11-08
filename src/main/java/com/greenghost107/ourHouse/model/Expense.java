package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expenseName;

    private double price;

    private String notes;

    private LocalDateTime dt_created;

    @JsonBackReference(value = "house_expense")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="house_id",referencedColumnName="id")
    private House house;

    private String expenseCreator;

    public Expense(){}

    public Expense(String expenseName, double price)
    {
        this.expenseName = expenseName;
        this.price = price;
    }

    public Expense(String expenseName, double price, String notes, String userName, House house) {
        this.expenseName = expenseName;
        this.price = price;
        this.notes = notes;
        this.expenseCreator = userName;
        this.house = house;
        this.dt_created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getDt_created() {
        return dt_created;
    }

    public void setDt_created(LocalDateTime dt_created) {
        this.dt_created = dt_created;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public String getExpenseCreator() {
        return expenseCreator;
    }

    public void setExpenseCreator(String expenseCreator) {
        this.expenseCreator = expenseCreator;
    }
}

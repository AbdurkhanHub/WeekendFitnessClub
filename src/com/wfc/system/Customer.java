package com.wfc.system;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Lesson> bookings;

    public Customer(String name) {
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lesson> getBookings() {
        return bookings;
    }

    public void setBookings(List<Lesson> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", bookings=" + bookings +
                '}';
    }
}
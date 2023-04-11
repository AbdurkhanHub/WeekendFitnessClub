package com.wfc.system;

import java.util.ArrayList;

public class Lesson {
    private String day;
    private String fitnessType;
    private ArrayList<Review> reviews;

    public Lesson(String day, String fitnessType) {
        this.day = day;
        this.fitnessType = fitnessType;
        this.reviews = new ArrayList<>();
    }

    public String getDay() {
        return day;
    }

    public String getFitnessType() {
        return fitnessType;
    }

    public void addReview(String review, int rating) {
        reviews.add(new Review(review, rating));
    }

    @Override
    public String toString() {
        return "com.wfc.system.Lesson{" +
                "day='" + day + '\'' +
                ", fitnessType='" + fitnessType + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
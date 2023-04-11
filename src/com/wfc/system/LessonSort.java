package com.wfc.system;

public class LessonSort {

    private String lessonName;
    private Double totalLessonAmount;

    public LessonSort(String lessonName, Double totalLessonAmount) {
        this.lessonName = lessonName;
        this.totalLessonAmount = totalLessonAmount;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Double getTotalLessonAmount() {
        return totalLessonAmount;
    }

    public void setTotalLessonAmount(Double totalLessonAmount) {
        this.totalLessonAmount = totalLessonAmount;
    }

    @Override
    public String toString() {
        return "com.wfc.system.Lesson" +
                "Fitness Name='" + lessonName + '\'' +
                ", Total Amount=" + totalLessonAmount +
                '}';
    }
}

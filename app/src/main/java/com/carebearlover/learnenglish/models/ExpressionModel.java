package com.carebearlover.learnenglish.models;

public class ExpressionModel {
    private String title;
    private String translate;
    private String example1;
    private String example2;
    private String example3;
    private int group;
    private long date;

    public ExpressionModel(String title, String translate,
                           String example1, String example2,
                           String example3, int group, long date) {
        this.title = title;
        this.translate = translate;
        this.example1 = example1;
        this.example2 = example2;
        this.example3 = example3;
        this.group = group;
        this.date = date;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return translate;
    }

    public void setDescription(String description) {
        this.translate = description;
    }

    public ExpressionModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

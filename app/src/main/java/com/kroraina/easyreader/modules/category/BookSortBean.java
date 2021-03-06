package com.kroraina.easyreader.modules.category;

import com.xincubate.lego.annotation.LegoBean;

import java.util.List;


@LegoBean(clazz = BookCategoryItem.class)
public class BookSortBean {
    /**
     * name : 玄幻
     * bookCount : 437252
     */
    private String name;
    private int bookCount;
    private int monthlyCount;

    public int getMonthlyCount() {
        return monthlyCount;
    }

    public void setMonthlyCount(int monthlyCount) {
        this.monthlyCount = monthlyCount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getBookCover() {
        return bookCover;
    }

    public void setBookCover(List<String> bookCover) {
        this.bookCover = bookCover;
    }

    private String icon;
    private List<String> bookCover;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }
}
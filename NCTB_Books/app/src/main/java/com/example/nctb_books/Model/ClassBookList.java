package com.example.nctb_books.Model;

public class ClassBookList {
    String ClassNo;
    String Book_Link;

    public ClassBookList() {
    }

    public ClassBookList(String classNo, String book_Link) {
        ClassNo = classNo;
        Book_Link = book_Link;
    }

    public String getClassNo() {
        return ClassNo;
    }

    public void setClassNo(String classNo) {
        ClassNo = classNo;
    }

    public String getBook_Link() {
        return Book_Link;
    }

    public void setBook_Link(String book_Link) {
        Book_Link = book_Link;
    }
}

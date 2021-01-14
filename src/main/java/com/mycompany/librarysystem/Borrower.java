package com.mycompany.librarysystem;
import java.util.*;
public class Borrower {
    private String name;
    private String emailAddress;
    private ArrayList<book> borrowedBook = new ArrayList<>();

    public Borrower(String name, String emailAddress, ArrayList<book> borrowedBook) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.borrowedBook = borrowedBook;
    }

    @Override
    public String toString() {
        return name + ", " + emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ArrayList<book> getBorrowerdBook() {
        return borrowedBook;
    }

    public void setBrorrowedBook(book borrowedBook) {
        this.borrowedBook.add(borrowedBook);
    }
    
}

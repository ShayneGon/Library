package com.mycompany.librarysystem;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileHandling {
    private static String preBooks;
    public static void setUpBooked(){
        preBooks = System.getProperty("user.dir")+"\\bookings.text"; // Intialises file if it doesn't exist
    }
    public static void addToBookList(){
        String currentLine;
        int borrowerIndex = -1;
        try {
            BufferedReader read = new BufferedReader(new FileReader(preBooks));
            while ((currentLine = read.readLine()) != null) {
                if (!currentLine.endsWith("/")){ //checks if it is reading a book or member info due to identifier
                    borrowerIndex++;
                    ArrayList<book>bookList = new ArrayList<>();
                    String[] borrowerDetails = currentLine.split(", ");
                    Borrower currentBorrower = new Borrower(borrowerDetails[0], borrowerDetails[1], bookList); //Loads members' data
                    librarySystem.borrowerList.add(currentBorrower);
                }else{
                    String[] bookDetails = currentLine.split(", "); //Each field is split by a comma and space
                    book currentBook = new book(bookDetails[0], bookDetails[1], Double.parseDouble(bookDetails[2]), bookDetails[3], bookDetails[4]); // book record created
                    librarySystem.borrowerList.get(borrowerIndex).getBorrowerdBook().add(currentBook);
                } 
                
            }
            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public static void addToBookFile(){
        if (!librarySystem.borrowerList.isEmpty()){
            try {
                FileWriter writeToFile = new FileWriter(preBooks, false);
                PrintWriter printToFile = new PrintWriter(writeToFile);
                for (int i = 0; i < librarySystem.borrowerList.size(); i++) {
                    printToFile.println(librarySystem.borrowerList.get(i).toString());
                    for (int j = 0; j < librarySystem.borrowerList.get(i).getBorrowerdBook().size(); j++) {
                       printToFile.println(librarySystem.borrowerList.get(i).getBorrowerdBook().get(j).toString()); 
                    }
                }
                printToFile.close();
                writeToFile.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

}

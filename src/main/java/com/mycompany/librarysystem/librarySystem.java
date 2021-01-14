package com.mycompany.librarysystem;
import java.util.*;
public class librarySystem {
    private static final Scanner input = new Scanner(System.in);
    public static ArrayList<Borrower> borrowerList = new ArrayList<>();
    public static void main(String[] args) {
        FileHandling.setUpBooked();
        FileHandling.addToBookList();
        mainMenu();
    }
    public static void mainMenu(){
        while (true){
            System.out.println("Welcome to my library");
            System.out.println("What would you like to do");
            System.out.println("0 - Exit");
            System.out.println("1 - Take out a book");
            System.out.println("2 - View all outstanding loans");
            System.out.println("3 - Edit a loan's informaton");
            System.out.println("4 - Return a book");
            System.out.println("5 - Set up new member account");
            int userChoice = input.nextInt();
            switch (userChoice){
                case 1:
                    newLoan();
                    break;
                case 2:
                    viewAllLoans();
                    break;
                case 3:
                    appendLoan();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    setUpNewMember();
                    break;
                case 0:
                    FileHandling.addToBookFile();
                    System.exit(0);
            }
        }
    }
    public static void newLoan(){
        int borrowerIndex = getBorrowerIndex();
        if (borrowerIndex == -1){
            System.out.println("Borrower doesn't exist, please set up an account");
        }else{
            System.out.println("Please enter in a name");
            String name = input.nextLine();
            System.out.println("Please enter in a ISBN");
            String ISBN = input.nextLine();
            System.out.println("Please enter in a price");
            double price = input.nextDouble();
            input.nextLine();
            System.out.println("Please enter in a author");
            String author = input.nextLine();
            System.out.println("Please enter in a genre");
            String genre = input.nextLine();
            book myBook = new book(name,ISBN,price,author,genre);
            borrowerList.get(borrowerIndex).getBorrowerdBook().add(myBook);
        }
    }
    public static void viewAllLoans(){
        if (borrowerList.isEmpty()) {
            System.out.println("Sorry, no books in the library");
        }else{
            for (int i = 0; i < borrowerList.size(); i++) {
                System.out.println("For borrower with name: " +borrowerList.get(i).getName()
                +" and email address: "+borrowerList.get(i).getEmailAddress());
                System.out.println("Books borrowed:");
                if (borrowerList.get(i).getBorrowerdBook().isEmpty()) {
                    System.out.println("No books borrowerd.");
                }else{
                    for (int j = 0; j < borrowerList.get(i).getBorrowerdBook().size(); j++) {
                        System.out.println("Book name: "+ borrowerList.get(i).getBorrowerdBook().get(j).getName()
                        + ", Book ISBN: "+ borrowerList.get(i).getBorrowerdBook().get(j).getISBN());
                    }
                }
            }
        }
    }
    public static void appendLoan(){
        int borrowerIndex = getBorrowerIndex();
        if (borrowerIndex == -1){
            System.out.println("No borrower found, create an account");
        }else{
            int index = getBookIndex(borrowerIndex);
            if (index == -1){
                System.out.println("Sorry Invalid book");
            }else{
                System.out.println("What would you like to edit?");
                System.out.println("1 - name");
                System.out.println("2 - ISBN");
                System.out.println("3 - price");
                System.out.println("4 - author");
                System.out.println("5 - genre");
                System.out.println("6 - none of the above");
                int userChoice = input.nextInt();
                input.nextLine();

                switch (userChoice){
                    case 1:
                        System.out.println("Please type new name");
                        String newName = input.nextLine();
                        borrowerList.get(borrowerIndex).getBorrowerdBook().get(index).setName(newName);
                        break;
                    case 2:
                        System.out.println("Please type new ISBN");
                        String newISBN = input.nextLine();
                        borrowerList.get(borrowerIndex).getBorrowerdBook().get(index).setISBN(newISBN);
                        break;
                    case 3:
                        System.out.println("Please type new price");
                        double newPrice = input.nextDouble();
                        borrowerList.get(borrowerIndex).getBorrowerdBook().get(index).setPrice(newPrice);
                        input.nextLine();
                        break;
                    case 4:
                        input.nextLine();
                        System.out.println("Please type new author");
                        String newAuthor = input.nextLine();
                        borrowerList.get(borrowerIndex).getBorrowerdBook().get(index).setAuthor(newAuthor);
                        break;
                    case 5:
                        System.out.println("Please type new genre");
                        String newGenre = input.nextLine();
                        borrowerList.get(borrowerIndex).getBorrowerdBook().get(index).setGenre(newGenre);
                        break;
                    case 6:
                        break;
                }System.out.println("Book sucessfully edited");
            }
        }
    }
    public static void returnBook(){
        int borrowerIndex = getBorrowerIndex();
        if (borrowerIndex == -1) {
            System.out.println("Borrower not found");
        }else{
            int index = getBookIndex(borrowerIndex);
            if (index == -1){
                System.out.println("Invalid book");
            }else borrowerList.get(borrowerIndex).getBorrowerdBook().remove(index);
    }   }
    public static int getBookIndex(int borrowerIndex){
        System.out.println("Please type in the book name");
        String name = input.nextLine();
        System.out.println("Please type in the ISBN");
        String ISBN = input.nextLine();
        for (int i = 0; i < borrowerList.get(borrowerIndex).getBorrowerdBook().size(); i++) {
            if (borrowerList.get(borrowerIndex).getBorrowerdBook().get(i).getName().equalsIgnoreCase(name) && borrowerList.get(borrowerIndex).getBorrowerdBook().get(i).getISBN().equalsIgnoreCase(ISBN)){
                return i;
            }
        }return -1;
    }
    
    public static void setUpNewMember(){
        input.nextLine();
        boolean valid = true;
        System.out.println("Enter name of borrower");
        String borrowerName = input.nextLine();
        System.out.println("Enter your email address");
        String emailAddress = input.nextLine();
        for (int i = 0; i < borrowerList.size(); i++) {
            if (borrowerList.get(i).getName().equals(borrowerName) && borrowerList.get(i).getEmailAddress().equals(emailAddress)) {
                System.out.println("Borrower already exists");
                valid = false;
                break;
            }
        }if (valid == true){
            ArrayList<book> bookList = new ArrayList<>();
            Borrower newBorrower = new Borrower(borrowerName, emailAddress, bookList);
            borrowerList.add(newBorrower);
        }
    }
    public static int getBorrowerIndex(){
        input.nextLine();
        System.out.println("Enter name of borrower");
        String borrowerName = input.nextLine();
        System.out.println("Enter email address of borrower");
        String emailAddress = input.nextLine();
        for (int i = 0; i < borrowerList.size(); i++) {
            if (borrowerList.get(i).getName().equals(borrowerName) && borrowerList.get(i).getEmailAddress().equals(emailAddress)){
                return i;
            }
        }return -1;
    }
}

// Book is the base class
abstract class Book {
    private String title;
    private String author;
    private double price;

    // Constructor = Encapsulation
    public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter methods : Encapsulation
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }

    // Abstract method for Polymorphism
    public abstract double calculateLateFee(int days);
}

// Inheritance: EBook and PrintedBook inherit from Book
class EBook extends Book {
    public EBook(String title, String author, double price) {
        super(title, author, price);
    }

    @Override
    public double calculateLateFee(int days) {
        return days * 0.5; // low fee for eBooks
    }
}

class PrintedBook extends Book {
    public PrintedBook(String title, String author, double price) {
        super(title, author, price);
    }

    @Override
    public double calculateLateFee(int days) {
        return days * 1.0; // higher fees or coslty for printed books
    }
}
// S - Single Responsibility: each class has only one purpose.
// O - Open/Closed: adding new payment types without changing the existing code.
// L - Liskov: both EBook and PrintedBook can replace Book safely without strange behaviour.
// I - Interface Segregation: separate small interfaces.
// D - Dependency Inversion: Library depends on Payment interface, not concrete classes or specific objects.

// Interface for payment (Abstraction)
interface Payment {
    void pay(double amount);
}

// Payment methods
class CashPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Aed" + amount + " in cash.");
    }
}

class CardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Aed" + amount + " by card.");
    }
}

// Library class follows Dependency Inversion
class Library {
    private Payment paymentMethod;

    // Dependency Injection via constructor
    public Library(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void borrowBook(Book book, int lateDays) {
        System.out.println("Borrowed: " + book.getTitle() + " by " + book.getAuthor());
        double fee = book.calculateLateFee(lateDays);
        paymentMethod.pay(fee);
    }
}

// Main Program
public class Main {
    public static void main(String[] args) {
        // Create book objects
        Book ebook = new EBook("Open Mindset", "Joseph Murphy", 100.0);
        Book printedBook = new PrintedBook("Think like a rich", "Ramis OUM", 55.0);

        // Choose payment method
        Payment cash = new CashPayment();
        Payment card = new CardPayment();

        // Library operations
        Library lib1 = new Library(cash);
        Library lib2 = new Library(card);

        lib1.borrowBook(ebook, 10);
        lib2.borrowBook(printedBook, 4);
    }
}

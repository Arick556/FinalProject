import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static class Book {

        String author;
        String title;
        int numOfPages;

        Book(String author, String title, int numOfPages) {
            this.author = author;
            this.title = title;
            this.numOfPages = numOfPages;
        }

    }

    public static class Shelf {
        ArrayList<Book> books;
        boolean isShelfFull;
        final int size = 5;

        Shelf() {
            this.books = new ArrayList<>(size);
            this.isShelfFull = false;
        }

        void addBook(Book book) {
            if (books.size() < this.size) {
                books.add(book);
                isShelfFull = (books.size() == size);
            } else {
                System.out.println("Maximum Shelf Capacity reached");
            }

        }

        void replaceBooks(int num1, int num2) {
            if (num1 < 1 || num1 > size || num2 < 1 || num2 > size) {
                System.out.println("Please enter numbers between 1 and 5.");
            }

            if (books.size() < Math.max(num1, num2)) {
                System.out.println("There is no Book to replace in those positions.");
                return;
            }

            Book tempBook1 = books.get(num1 - 1);
            Book tempBook2 = books.get(num2 - 1);

            books.set(num1 - 1, tempBook2);
            books.set(num2 - 1, tempBook1);

        }

    }

    public static class Reader {

        int id;
        String name;
        ArrayList<String> books;

        Reader(int id, String name) {
            this.id = id;
            this.name = name;
            this.books = new ArrayList<>();
        }

        public void readBook(String title) {
            books.add(title);
        }
    }

    public class Library {

        ArrayList<Shelf> shelves;
        ArrayList<Reader> readers;

        Library() {
            this.shelves = new ArrayList<>(3);
            this.readers = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                shelves.add(new Shelf());
            }
        }

        boolean isTherePlaceForNewBook() {
            boolean hasSpace = false;
            for (Shelf shelf : shelves) {
                if (shelf.isShelfFull == false) {
                    return true;
                }
            }
            return false;
        }

        void addNewBook(Book book) {
            if (isTherePlaceForNewBook() == false) {
                for (int i = 0; i < shelves.size(); i++) {
                    Shelf shelf = shelves.get(i);
                    if (shelf.isShelfFull) {
                        System.out.println("No more room in Shelf " + (i + 1));
                    }
                }
                return;
            }
            for (Shelf shelf : shelves) {
                if (shelf.isShelfFull == false) {
                    shelf.addBook(book);
                }
            }
        }


        void deleteBook(String title) {
            boolean bookDeleted = false;
            for (Shelf shelf : shelves) {
                for (int i = 0; i < shelf.books.size(); i++) {
                    if (shelf.books.get(i).title.equals(title)) {
                        shelf.books.remove(i);
                        shelf.isShelfFull = (shelf.books.size() == shelf.size);
                        System.out.println("Book Deleted");
                        bookDeleted = true;
                        break;
                    }
                }
                if (bookDeleted == true) {
                    break;
                }
            }
        }

        void registerReader(int id, String name) {
            readers.add(new Reader(id, name));
            System.out.println("Reader " + name + " was added.");
        }

        void removeReader(String name) {
            int size = readers.size();
            for (int i = 0; i < readers.size(); i++) {
                if (readers.get(i).name.equals(name)) {
                    readers.remove(i);
                    System.out.println("Reader Removed");
                }
            }
            if (readers.size() == size) {
                System.out.println("No reader was found");
            }
        }

        void searchByAuthor(String author) {
            ArrayList<String> titles = new ArrayList<>();
            for (Shelf shelf : shelves) {
                for (Book book : shelf.books) {
                    if (book.author.equals(author)) {
                        titles.add(book.title);
                    }
                }
            }
            if (titles.isEmpty()) {
                System.out.println("No books found for this author.");
            } else {
                System.out.println("Books by " + author + ":");
                for (String title : titles) {
                    System.out.println(title);
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Library library = new Main().new Library();

        Book book1 = new Book("Stephen King", "The Stand", 55);

        Book book2 = new Book("Kazuo Umezz","The Drifting Classroom", 32);


        Book book3 = new Book("Eren Jeager", "Freedom", 555);


        Book book4 = new Book("Eren Erwin Smith", "Charge", 123);


        Book book5 = new Book("Bojack Horseman", "Horsing Around", 560);


        Book book6 = new Book("Rick Grimes", "Zombies", 1112);


        library.shelves.get(0).books.add(book1);
        library.shelves.get(0).books.add(book2);
        library.shelves.get(1).books.add(book3);
        library.shelves.get(1).books.add(book4);
        library.shelves.get(2).books.add(book5);
        library.shelves.get(2).books.add(book6);


        for (Shelf shelf : library.shelves) {
            shelf.isShelfFull = (shelf.books.size() == shelf.size);
        }

        while (true) {
            System.out.println("Library Menu:");
            System.out.println("1. Add a book");
            System.out.println("2. Delete a book");
            System.out.println("3. Register a new reader");
            System.out.println("4. Remove a reader");
            System.out.println("5. Search books by author");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter the author's name: ");
                String author = scanner.nextLine();
                System.out.print("Enter the book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter the number of pages: ");
                int numOfPages = scanner.nextInt();
                scanner.nextLine();
                Book newBook = new Book(author, title, numOfPages);


                library.addNewBook(newBook);
            } else if (choice == 2) {
                System.out.print("Enter the title of the book to delete: ");
                String deleteTitle = scanner.nextLine();
                library.deleteBook(deleteTitle);

            } else if (choice == 3) {
                System.out.print("Enter the reader's ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter the reader's name: ");
                String name = scanner.nextLine();
                library.registerReader(id, name);

            } else if (choice == 4) {
                System.out.print("Enter the reader's name to remove: ");
                String removeName = scanner.nextLine();
                library.removeReader(removeName);

            } else if (choice == 5) {
                System.out.print("Enter the author's name to search: ");
                String searchAuthor = scanner.nextLine();
                library.searchByAuthor(searchAuthor);

            } else if (choice == 6) {
                System.out.println("Exiting the program...");
                scanner.close();
                break;

            } else {
                System.out.println("Please enter a number between 1 and 6.");
            }

        }

    }
}

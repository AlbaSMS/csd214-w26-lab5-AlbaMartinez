package csd214.bookstore;

import csd214.bookstore.repositories.*;
import csd214.bookstore.services.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Persistence Strategy:");
        System.out.println("1. Volatile RAM (ArrayList - Sequential)");
        System.out.println("2. Volatile RAM (HashMap - Indexed)");
        System.out.println("3. Integration Testing (H2 Database)");
        System.out.println("4. Production (MySQL Database)");
        System.out.print("Choice: ");

        int choice = sc.nextInt();
        IRepository repository;

        // WIRING PHASE
        switch (choice) {
            case 2: repository = new InMemoryMapRepository(); break;
            case 3: repository = new H2Repository(); break;
            case 4: repository = new MySqlRepository(); break;
            default: repository = new InMemoryListRepository(); break;
        }

        BookstoreService bookstoreService = new BookstoreService(repository);
        PhoneService phoneService = new PhoneService(repository);
        LaptopService laptopService = new LaptopService(repository);

        // INJECTION PHASE
        App app = new App(repository, bookstoreService, phoneService, laptopService);
        try {
            app.run();
        } finally {
            repository.close();
            sc.close();
        }
    }
}
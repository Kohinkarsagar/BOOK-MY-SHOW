package com.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.model.Movie;
import com.model.Snack;
import com.model.User;
import com.service.BookMyShow;
import com.service.BookingDesk;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookMyShow bookingDesk = new BookingDesk();

        List<Movie> availableMovies = new ArrayList<>();
        availableMovies.add(new Movie(1, "Jhon Wick", 400, 9.5f, "Action"));
        availableMovies.add(new Movie(2, "Money Heist", 500, 9.8f, "Drama"));
        availableMovies.add(new Movie(3, "Fukrey", 400, 6.0f, "Comedy"));
        availableMovies.add(new Movie(4, "Farzi", 500, 9.0f, "Comedy"));
        availableMovies.add(new Movie(5, "Jumanji", 600, 9.8f, "Adventure"));
       
        List<Snack> availableSnacks = new ArrayList<>();
        availableSnacks.add(new Snack(1, "Popcorn", "Butter popcorn", 150));
        availableSnacks.add(new Snack(2, "Caramel Popcorn", "popcorn", 200));
        availableSnacks.add(new Snack(3, "Coca Cola", "Cold Drink", 60));
        availableSnacks.add(new Snack(4, "burgar", "Added Chees", 120));
        availableSnacks.add(new Snack(5, "Pizza", "Added extra Chees", 300));

        User user = loginUser(scanner);

        List<Movie> bookedMovies = new ArrayList<>();
        List<Snack> addedMeals = new ArrayList<>();

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Movie bookedMovie = bookingDesk.bookTicket(availableMovies);
                    if (bookedMovie != null) {
                        bookedMovies.add(bookedMovie);
                        System.out.println("Movie booked: " + bookedMovie.getName());
                        System.out.println("Movie ID: " + bookedMovie.getId());
                        System.out.println("Price: ₹" + bookedMovie.getPrice());
                        System.out.println("Rating: " + bookedMovie.getRating());
                        System.out.println("Genre: " + bookedMovie.getGenre());
                    }
                    break;
                case 2:
                    List<Snack> addedSnacks = bookingDesk.addMeal(availableSnacks);
                    addedMeals.addAll(addedSnacks);
                    break;
                case 3:
                    printTicketDetails(user, bookedMovies, addedMeals);
                    double totalMoviePrice = calculateTotalMoviePrice(bookedMovies);
                    double totalSnackPrice = calculateTotalSnackPrice(addedMeals);

                    System.out.println("Total Movie Price: ₹" + totalMoviePrice);
                    System.out.println("Total Snack Price: ₹" + totalSnackPrice);
                    break;
                case 4:
                    logout(user, bookedMovies, addedMeals);
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static User loginUser(Scanner scanner) {
        System.out.println("***************Welcome to BookMyShow**********************");
        System.out.println();
        System.out.print("Enter your username: ");
        String username = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        if (username.equals("admin") && password.equals("123")) {
            return new User(username, password);
        } else {
            System.out.println("Invalid username or password. Login failed.");
            return null;
        }
    }

    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Book Movie Ticket");
        System.out.println("2. Add Snack to Meal");
        System.out.println("3. Print Ticket Details");
        System.out.println("4. Logout");
        System.out.print("Select an option: ");
    }

    private static void printTicketDetails(User user, List<Movie> bookedMovies, List<Snack> addedMeals) {
    	if (user != null) {
            System.out.println("\nTicket Details for " + user.getUserName()+".");
        } else {
            System.out.println("\nTicket Details:");
        }

        System.out.println("Booked Movies:");
        for (Movie movie : bookedMovies) {
            System.out.println("ID: " + movie.getId());
            System.out.println("Name: " + movie.getName());
            System.out.println("Price: ₹" + movie.getPrice());
            System.out.println("Rating: " + movie.getRating());
            System.out.println("Genre: " + movie.getGenre());
            System.out.println();
        }

        System.out.println("Added Snacks:");
        for (Snack snack : addedMeals) {
            System.out.println("ID: " + snack.getId());
            System.out.println("Name: " + snack.getName());
            System.out.println("Desc: " + snack.getDesc());
            System.out.println("Price: ₹" + snack.getPrice());
            System.out.println();
        }
    }

    private static double calculateTotalMoviePrice(List<Movie> movies) {
        double totalMoviePrice = 0;
        for (Movie movie : movies) {
            totalMoviePrice += movie.getPrice();
        }
        return totalMoviePrice;
    }

    private static double calculateTotalSnackPrice(List<Snack> snacks) {
        double totalSnackPrice = 0;
        for (Snack snack : snacks) {
            totalSnackPrice += snack.getPrice();
        }
        return totalSnackPrice;
    }

    private static void logout(User user, List<Movie> bookedMovies, List<Snack> addedMeals) {
        if (user != null) {
            System.out.println("Logging out. Goodbye, " + user.getUserName()+".");
        } else {
            System.out.println("Logging out. Goodbye!");
        }
        System.out.println("Thank you for using BookMyShow. We hope you enjoyed your experience"+".");
    }

  }


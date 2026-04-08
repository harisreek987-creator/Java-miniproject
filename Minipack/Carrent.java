package Minipack;

import java.util.*;

// ─────────────────────────────────────────────
// Base Class
// ─────────────────────────────────────────────
abstract class Vehicle {
    private String  vehicleId;
    private String  brand;
    private double  rentPerDay;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String brand, double rentPerDay) {
        this.vehicleId   = vehicleId;
        this.brand       = brand;
        this.rentPerDay  = rentPerDay;
        this.isAvailable = true;
    }

    public String  getVehicleId()  { return vehicleId;  }
    public String  getBrand()      { return brand;      }
    public double  getRentPerDay() { return rentPerDay; }
    public boolean isAvailable()   { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public abstract double calculateRent(int days);
    public abstract String getType();

    public void display() {
        System.out.printf("  ID: %-5s | Type: %-5s | Brand: %-15s | Rs.%.2f/day | %s%n",
                vehicleId,
                getType(),
                brand,
                rentPerDay,
                isAvailable ? "Available" : "Rented");
    }
}

// ─────────────────────────────────────────────
// Vehicle Subclasses
// ─────────────────────────────────────────────
class Car extends Vehicle {
    // Automatically prefixes ID with "C"
    public Car(int id, String brand, double rent) { super("C" + id, brand, rent); }

    @Override
    public double calculateRent(int days) { return getRentPerDay() * days; }

    @Override
    public String getType() { return "Car"; }
}

class Bike extends Vehicle {
    // Automatically prefixes ID with "B"
    public Bike(int id, String brand, double rent) { super("B" + id, brand, rent); }

    @Override
    public double calculateRent(int days) { return getRentPerDay() * days * 0.9; } // 10% discount

    @Override
    public String getType() { return "Bike"; }
}

class Truck extends Vehicle {
    // Automatically prefixes ID with "T"
    public Truck(int id, String brand, double rent) { super("T" + id, brand, rent); }

    @Override
    public double calculateRent(int days) { return getRentPerDay() * days * 1.2; } // 20% surcharge

    @Override
    public String getType() { return "Truck"; }
}

// ─────────────────────────────────────────────
// Customer Class
// ─────────────────────────────────────────────
class Customer {
    private int    customerId;
    private String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name       = name;
    }

    public int    getCustomerId() { return customerId; }
    public String getName()       { return name; }
}

// ─────────────────────────────────────────────
// Rental Class
// ─────────────────────────────────────────────
class Rental {
    private int      rentalId;
    private Vehicle  vehicle;
    private Customer customer;
    private int      days;
    private double   totalCost;
    private boolean  returned;

    public Rental(int rentalId, Vehicle vehicle, Customer customer, int days) {
        this.rentalId  = rentalId;
        this.vehicle   = vehicle;
        this.customer  = customer;
        this.days      = days;
        this.totalCost = vehicle.calculateRent(days);
        this.returned  = false;
    }

    public int     getRentalId() { return rentalId; }
    public Vehicle getVehicle()  { return vehicle;  }
    public boolean isReturned()  { return returned; }

    public void markReturned() {
        this.returned = true;
        vehicle.setAvailable(true);
    }

    public void displayRental() {
        System.out.printf("  Rental #%-3d | Customer: %-15s (ID: %d) | Vehicle: %s %-12s (ID: %s) | Days: %-3d | Total: Rs.%.2f | %s%n",
                rentalId,
                customer.getName(),
                customer.getCustomerId(),
                vehicle.getType(),
                vehicle.getBrand(),
                vehicle.getVehicleId(),
                days,
                totalCost,
                returned ? "Returned" : "Active");
    }
}

// ─────────────────────────────────────────────
// Main Class
// ─────────────────────────────────────────────
public class Carrent {
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Rental>  rentals  = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.println("║    Vehicle Rental System     ║");
            System.out.println("╠══════════════════════════════╣");
            System.out.println("║  1. Add Vehicle              ║");
            System.out.println("║  2. View All Vehicles        ║");
            System.out.println("║  3. View Available Vehicles  ║");
            System.out.println("║  4. Rent a Vehicle           ║");
            System.out.println("║  5. Return a Vehicle         ║");
            System.out.println("║  6. View Rental History      ║");
            System.out.println("║  7. Exit                     ║");
            System.out.println("╚══════════════════════════════╝");
            System.out.print("Enter choice: ");

            choice = readInt();

            switch (choice) {
                case 1 -> addVehicle();
                case 2 -> viewAllVehicles();
                case 3 -> viewAvailableVehicles();
                case 4 -> rentVehicle();
                case 5 -> returnVehicle();
                case 6 -> viewRentalHistory();
                case 7 -> System.out.println("Thank you for using Vehicle Rental System. Goodbye!");
                default -> System.out.println("Invalid choice! Please enter 1-7.");
            }
        } while (choice != 7);
    }

    // ── Add Vehicle ──────────────────────────
    static void addVehicle() {
        System.out.println("\n-- Add Vehicle --");
        System.out.println("  1. Car   2. Bike   3. Truck");
        System.out.print("  Select type: ");
        int type = readInt();

        if (type < 1 || type > 3) {
            System.out.println("  Invalid vehicle type.");
            return;
        }

        // Show what prefix will be applied
        String prefix = switch (type) {
            case 1 -> "C";
            case 2 -> "B";
            case 3 -> "T";
            default -> "?";
        };

        System.out.print("  Enter Vehicle Number (will be saved as " + prefix + "<number>): ");
        int idNumber = readInt();

        String fullId = prefix + idNumber;

        // Check for duplicate prefixed ID
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(fullId)) {
                System.out.println("  A vehicle with ID " + fullId + " already exists.");
                return;
            }
        }

        System.out.print("  Enter Brand: ");
        String brand = scanner.nextLine().trim();
        if (brand.isEmpty()) {
            System.out.println("  Brand cannot be empty.");
            return;
        }

        System.out.print("  Enter Rent per Day (Rs.): ");
        double rent = readDouble();
        if (rent <= 0) {
            System.out.println("  Rent must be greater than 0.");
            return;
        }

        Vehicle vehicle = switch (type) {
            case 1 -> new Car(idNumber, brand, rent);
            case 2 -> new Bike(idNumber, brand, rent);
            case 3 -> new Truck(idNumber, brand, rent);
            default -> null;
        };

        vehicles.add(vehicle);
        System.out.println("  Vehicle added successfully with ID: " + fullId);
    }

    // ── View All Vehicles ────────────────────
    static void viewAllVehicles() {
        System.out.println("\n-- All Vehicles --");
        if (vehicles.isEmpty()) {
            System.out.println("  No vehicles in the system yet.");
            return;
        }
        for (Vehicle v : vehicles) v.display();
    }

    // ── View Available Vehicles ──────────────
    static void viewAvailableVehicles() {
        System.out.println("\n-- Available Vehicles --");
        boolean found = false;
        for (Vehicle v : vehicles) {
            if (v.isAvailable()) {
                v.display();
                found = true;
            }
        }
        if (!found) System.out.println("  No vehicles are currently available.");
    }

    // ── Rent a Vehicle ───────────────────────
    static void rentVehicle() {
        System.out.println("\n-- Rent a Vehicle --");

        if (vehicles.stream().noneMatch(Vehicle::isAvailable)) {
            System.out.println("  No vehicles are currently available for rent.");
            return;
        }

        System.out.print("  Enter Vehicle ID (e.g. C1, B2, T3): ");
        String vehicleId = scanner.nextLine().trim().toUpperCase();

        Vehicle selected = null;
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equals(vehicleId)) {
                if (!v.isAvailable()) {
                    System.out.println("  Vehicle " + vehicleId + " is currently rented out.");
                    return;
                }
                selected = v;
                break;
            }
        }

        if (selected == null) {
            System.out.println("  No vehicle found with ID " + vehicleId + ".");
            return;
        }

        System.out.print("  Enter Customer ID: ");
        int customerId = readInt();

        System.out.print("  Enter Customer Name: ");
        String customerName = scanner.nextLine().trim();
        if (customerName.isEmpty()) {
            System.out.println("  Customer name cannot be empty.");
            return;
        }

        System.out.print("  Enter number of rental days: ");
        int days = readInt();
        if (days <= 0) {
            System.out.println("  Number of days must be at least 1.");
            return;
        }

        Customer customer = new Customer(customerId, customerName);
        Rental rental = new Rental(rentals.size() + 1, selected, customer, days);
        rentals.add(rental);
        selected.setAvailable(false);

        System.out.println("\n  Rental confirmed!");
        rental.displayRental();
    }

    // ── Return a Vehicle ─────────────────────
    static void returnVehicle() {
        System.out.println("\n-- Return a Vehicle --");

        System.out.print("  Enter Rental ID: ");
        int rentalId = readInt();

        for (Rental r : rentals) {
            if (r.getRentalId() == rentalId) {
                if (r.isReturned()) {
                    System.out.println("  Rental #" + rentalId + " has already been returned.");
                    return;
                }
                r.markReturned();
                System.out.println("  Vehicle returned successfully!");
                r.displayRental();
                return;
            }
        }

        System.out.println("  No active rental found with ID " + rentalId + ".");
    }

    // ── View Rental History ──────────────────
    static void viewRentalHistory() {
        System.out.println("\n-- Rental History --");
        if (rentals.isEmpty()) {
            System.out.println("  No rentals recorded yet.");
            return;
        }
        for (Rental r : rentals) r.displayRental();
    }

    // ── Input Helpers ────────────────────────
    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Invalid input. Enter a whole number: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Invalid input. Enter a number: ");
            }
        }
    }
}
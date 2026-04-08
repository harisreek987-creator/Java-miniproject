# 🚗 Vehicle Rental System (Java OOP Mini Project)

## 📌 Overview

The **Vehicle Rental System** is a console-based Java application developed to demonstrate core **Object-Oriented Programming (OOP)** concepts. It allows users to manage vehicles, perform rentals, and maintain rental records in a structured and efficient manner.

This project is designed as a **mini project for First-Year Computer Science Engineering (Semester 2)** students.

---

## ✨ Features

* 🚘 Add vehicles (Car, Bike, Truck)
* 📋 View all vehicles in the system
* ✅ View only available vehicles
* 🧾 Rent vehicles with customer details
* 🔄 Return rented vehicles
* 📊 View complete rental history
* 🔢 Unique Vehicle ID system (C = Car, B = Bike, T = Truck)
* ⚠️ Input validation for smooth user interaction

---

## 🧠 OOP Concepts Implemented

| Concept       | Implementation                                    |
| ------------- | ------------------------------------------------- |
| Abstraction   | Abstract `Vehicle` class                          |
| Inheritance   | `Car`, `Bike`, `Truck` classes extend `Vehicle`   |
| Polymorphism  | Different rent calculations for each vehicle type |
| Encapsulation | Private fields with getters and setters           |

---

## 🏗️ Project Structure

```
Minipack/
│
├── Vehicle (Abstract Class)
├── Car.java
├── Bike.java
├── Truck.java
├── Customer.java
├── Rental.java
└── Carrent.java (Main Class)
```

---

## ⚙️ How It Works

1. User selects options from a menu-driven interface
2. Vehicles can be added with unique IDs
3. Available vehicles can be rented by entering customer details
4. Rental cost is calculated automatically based on vehicle type
5. Vehicles can be returned and marked available again
6. All rental transactions are stored and displayed

---

## 💻 Technologies Used

* **Java**
* **OOP Principles**
* **ArrayList (Collections Framework)**
* **Scanner (User Input Handling)**

---

## ▶️ How to Run

1. Clone the repository:

   ```
   git clone https://github.com/your-username/vehicle-rental-system.git
   ```

2. Navigate to the project folder:

   ```
   cd vehicle-rental-system
   ```

3. Compile the program:

   ```
   javac Minipack/*.java
   ```

4. Run the application:

   ```
   java Minipack.Carrent
   ```

---

## 📷 Sample Output

```
Vehicle Rental System
1. Add Vehicle
2. View All Vehicles
3. View Available Vehicles
4. Rent a Vehicle
5. Return a Vehicle
6. View Rental History
7. Exit
```

---

## 🎯 Learning Outcome

* Practical understanding of **OOP principles**
* Hands-on experience with **Java classes and objects**
* Implementation of a **real-world system simulation**
* Improved problem-solving and program structuring skills

---

## 📌 Future Improvements

* Add GUI (Java Swing / JavaFX)
* Store data using a database (MySQL / SQLite)
* Add user authentication system
* Generate rental receipts

---


## ⭐ Support

If you like this project, consider giving it a ⭐ on GitHub!

---

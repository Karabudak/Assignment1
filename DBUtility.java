package com.example.assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtility {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/testDB";
    private static final String USER = "root";
    private static final String PASSWORD = "Patates1910$";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
        return connection;
    }

    public static List<Income> getIncomes() {
        List<Income> incomes = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Income")) {
            while (resultSet.next()) {
                Income income = new Income(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getDate("date").toLocalDate()
                );
                incomes.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomes;
    }

    public static List<Expense> getExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Expense")) {
            while (resultSet.next()) {
                Expense expense = new Expense(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getBigDecimal("amount"),
                        resultSet.getDate("date").toLocalDate()
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    public static void main(String[] args) {
        List<Expense> expenses = getExpenses();
        for (Expense expense : expenses) {
            System.out.println(expense.getDescription() + " - " + expense.getAmount());
        }

        List<Income> incomes = getIncomes();
        for (Income income : incomes) {
            System.out.println(income.getDescription() + " - " + income.getAmount());
        }
    }
}

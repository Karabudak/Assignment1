package com.example.assignment1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Personal Finance Management System");

        // Benzersiz bir ikon ekleyin
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/image.jpeg")));

        // Create a BarChart for Expenses
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Expenses");
        xAxis.setLabel("Description");
        yAxis.setLabel("Amount");

        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        expenseSeries.setName("Expenses");

        List<Expense> expenses = DBUtility.getExpenses();
        for (Expense expense : expenses) {
            expenseSeries.getData().add(new XYChart.Data<>(expense.getDescription(), expense.getAmount()));
        }

        barChart.getData().add(expenseSeries);

        // Create a TableView for Expenses
        TableView<Expense> expenseTableView = new TableView<>();
        TableColumn<Expense, String> expenseDescriptionColumn = new TableColumn<>("Description");
        expenseDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Expense, BigDecimal> expenseAmountColumn = new TableColumn<>("Amount");
        expenseAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn<Expense, LocalDate> expenseDateColumn = new TableColumn<>("Date");
        expenseDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        expenseTableView.getColumns().addAll(expenseDescriptionColumn, expenseAmountColumn, expenseDateColumn);

        ObservableList<Expense> expenseList = FXCollections.observableArrayList(expenses);
        expenseTableView.setItems(expenseList);

        // Create a LineChart for Incomes
        CategoryAxis incomeXAxis = new CategoryAxis();
        NumberAxis incomeYAxis = new NumberAxis();
        LineChart<String, Number> lineChart = new LineChart<>(incomeXAxis, incomeYAxis);
        lineChart.setTitle("Incomes");
        incomeXAxis.setLabel("Description");
        incomeYAxis.setLabel("Amount");

        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Incomes");

        List<Income> incomes = DBUtility.getIncomes();
        for (Income income : incomes) {
            incomeSeries.getData().add(new XYChart.Data<>(income.getDescription(), income.getAmount()));
        }

        lineChart.getData().add(incomeSeries);

        // Create a TableView for Incomes
        TableView<Income> incomeTableView = new TableView<>();
        TableColumn<Income, String> incomeDescriptionColumn = new TableColumn<>("Description");
        incomeDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        TableColumn<Income, BigDecimal> incomeAmountColumn = new TableColumn<>("Amount");
        incomeAmountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn<Income, LocalDate> incomeDateColumn = new TableColumn<>("Date");
        incomeDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        incomeTableView.getColumns().addAll(incomeDescriptionColumn, incomeAmountColumn, incomeDateColumn);

        ObservableList<Income> incomeList = FXCollections.observableArrayList(incomes);
        incomeTableView.setItems(incomeList);

        // Sahne değiştirme düğmesi
        Button switchToTableScene = new Button("Switch to Table Scene");
        Button switchToChartScene = new Button("Switch to Chart Scene");

        // Grafik sahnesi
        VBox chartScene = new VBox(barChart, expenseTableView, switchToTableScene);
        Scene scene1 = new Scene(chartScene, 800, 600);

        // Tablo sahnesi
        VBox tableScene = new VBox(incomeTableView, switchToChartScene);
        Scene scene2 = new Scene(tableScene, 800, 600);

        // Düğme aksiyonları
        switchToTableScene.setOnAction(e -> primaryStage.setScene(scene2));
        switchToChartScene.setOnAction(e -> primaryStage.setScene(scene1));

        // Stil dosyasını ekleme
        scene1.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());
        scene2.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

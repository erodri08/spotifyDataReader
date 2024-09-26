package cpt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import cpt.sort;
import cpt.SpotifyData;

public class MainStage extends Application {

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    /**
    * Launches the main display of the program
    * @author E. Rodrigues
    */
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();

        // Create tabs
        Tab tableTab = createTableTab();
        Tab barGraphTab = createBarGraphTab();
        Tab searchTab = createSearchTab();

        // Add tabs to the tab pane
        tabPane.getTabs().addAll(tableTab, barGraphTab, searchTab);

        // Create the main scene
        Scene scene = new Scene(tabPane, 1700, 1000);

        // Set the scene and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("CPT Spotify Data");
        primaryStage.show();
    }

    /**
    * Creates the table tab
    * @author E. Rodrigues
    */
    private Tab createTableTab() {
        Tab tab = new Tab("Table");

        // Create table columns
        TableColumn<SpotifyData, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("strArtistName"));

        TableColumn<SpotifyData, String> albumColumn = new TableColumn<>("Album");
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("strAlbumName"));

        TableColumn<SpotifyData, String> trackColumn = new TableColumn<>("Track");
        trackColumn.setCellValueFactory(new PropertyValueFactory<>("strTrackName"));

        TableColumn<SpotifyData, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("strDate"));

        // Create the table view
        TableView<SpotifyData> tableView = new TableView<>();
        tableView.getColumns().addAll(artistColumn, albumColumn, trackColumn, dateColumn);

        // Load data from the file and put it on the table
        ObservableList<SpotifyData> data = loadSpotifyData();
        tableView.setItems(data);

        // Set the table view as the content of the tab
        tab.setContent(tableView);

        return tab;
    }

    /**
    * Creates the graph tab
    * @author E. Rodrigues
    */
    private Tab createBarGraphTab() {
        Tab tab = new Tab("Bar Graph");

        // Create axises for the bar chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Streaming Statistics");

        // Load data from the file and put it on the bar chart
        ObservableList<SpotifyData> data = loadSpotifyData();
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Count the number of times each artist is streamed
        for (SpotifyData SpotifyData : data) {
            String artistName = SpotifyData.getStrArtistName();

            // Find the series corresponding to the artist
            XYChart.Data<String, Number> dataPoint = series.getData().stream().filter(d -> d.getXValue().equals(artistName)).findFirst().orElse(null);
            if (dataPoint != null) {
                // Artist already exists in the series, increment the value
                dataPoint.setYValue(dataPoint.getYValue().intValue() + 1);
            } else {
                // Artist does not exist in the series, add a new data point
                series.getData().add(new XYChart.Data<>(artistName, 1));
            }
        }

        // Add the series to the bar chart
        barChart.getData().add(series);

        // Set the bar chart as the content of the tab
        tab.setContent(barChart);

        return tab;
    }

    private Tab createSearchTab() {
        Tab tab = new Tab("Search");
    
        // Create table columns
        TableColumn<SpotifyData, String> artistColumn = new TableColumn<>("Artist");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("strArtistName"));
    
        TableColumn<SpotifyData, String> albumColumn = new TableColumn<>("Album");
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("strAlbumName"));
    
        TableColumn<SpotifyData, String> trackColumn = new TableColumn<>("Track");
        trackColumn.setCellValueFactory(new PropertyValueFactory<>("strTrackName"));
    
        TableColumn<SpotifyData, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("strDate"));
    
        // Create the table view
        TableView<SpotifyData> tableView = new TableView<>();
        tableView.getColumns().addAll(artistColumn, albumColumn, trackColumn, dateColumn);

        // Create the search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Enter search query...");
    
        // Create the search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String query = searchField.getText().trim();
    
            // display the information entered into the search bar
            if (!query.isEmpty()) {
                ObservableList<SpotifyData> filteredData = loadSpotifyData().filtered(d -> d.getStrArtistName().toLowerCase().contains(query.toLowerCase()) || d.getStrAlbumName().toLowerCase().contains(query.toLowerCase()) || d.getStrTrackName().toLowerCase().contains(query.toLowerCase()) || d.getStrDate().toLowerCase().contains(query.toLowerCase()));
                    tableView.setItems(filteredData);
            } else {
                tableView.setItems(loadSpotifyData());
            }
        });
    
        // Create the search layout
        VBox searchLayout = new VBox(10);
        searchLayout.getChildren().addAll(searchField, searchButton);
        searchLayout.setPadding(new Insets(10));
    
        // Set the search layout and table view as the content of the tab
        VBox contentLayout = new VBox(10);
        contentLayout.getChildren().addAll(searchLayout, tableView);
        tab.setContent(contentLayout);
    
        return tab;
    }

    /**
    * Sets the data observed to the viewer and sorts it
    * @author E. Rodrigues
    */
    private ObservableList<SpotifyData> loadSpotifyData() {
        ObservableList<SpotifyData> data = FXCollections.observableArrayList();
    
        try (BufferedReader fileReader = new BufferedReader(new FileReader("CPTspotifyData.csv"))) {
            String line;
    
            while ((line = fileReader.readLine()) != null) {
                String[] parts = line.split(",");
    
                if (parts.length == 4) {
                    String artistName = parts[0];
                    String albumName = parts[1];
                    String trackName = parts[2];
                    String date = parts[3];
    
                    data.add(new SpotifyData(artistName, albumName, trackName, date));
                }
            }
    
            // Sort the data by artist name using the sort.mergeSort method
            SpotifyData[] dataArray = data.toArray(new SpotifyData[0]);
            sort.mergeSort(dataArray);

            // Convert the sorted array back to an ObservableList
            data = FXCollections.observableArrayList(dataArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return data;
    }
}
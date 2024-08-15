package com.graph.graph.charts;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

public class LineChart implements GraphServices {

    @Value("${project.image}")
    private String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        ReadingData data = new ReadingData(path);
        
        // Get log data
        List<String> logs = data.getLogData();
        
        // Map to store frequency of each log level over time
        Map<String, XYSeries> logLevelSeries = new HashMap<>();
        
        // Use the correct date format based on your log data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        // Initialize series for each log level
        logLevelSeries.put("INFO", new XYSeries("INFO"));
        logLevelSeries.put("WARN", new XYSeries("WARN"));
        logLevelSeries.put("ERROR", new XYSeries("ERROR"));
        
        // Starting point for time (assuming the first timestamp as t=0)
        LocalDateTime startTime = null;

        // Process log data
        for (String log : logs) {
            String[] parts = log.split(",");
            if (parts.length == 3) {
                try {
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0], formatter);
                    String logLevel = parts[1];

                    // Set start time if not already set
                    if (startTime == null) {
                        startTime = timestamp;
                    }

                    // Calculate time difference in seconds from start time
                    long timeElapsed = java.time.Duration.between(startTime, timestamp).getSeconds();

                    // Add the timeElapsed and frequency to the appropriate series
                    XYSeries series = logLevelSeries.get(logLevel);
                    if (series != null) {
                        series.add(timeElapsed, series.getItemCount() + 1); // Increment log level frequency
                    }
                } catch (Exception e) {
                    // Handle any parsing or conversion exceptions
                    System.err.println("Error processing log entry: " + log);
                    e.printStackTrace();
                }
            } else {
                System.err.println("Invalid log entry format: " + log);
            }
        }

        // Create dataset and add all series to it
        XYSeriesCollection dataset = new XYSeriesCollection();
        logLevelSeries.values().forEach(dataset::addSeries);

        // Create the line chart
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Log Level Frequency Over Time",
            "Time (seconds)",
            "Frequency",
            dataset
        );

        // Save the chart as an image
        String pathImage = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);

        try {
            ChartUtilities.saveChartAsJPEG(new File(pathImage), chart, 500, 300);
        } catch (IOException e) {
            System.err.println("Error saving chart image: " + pathImage);
            e.printStackTrace();
        }
    }
}

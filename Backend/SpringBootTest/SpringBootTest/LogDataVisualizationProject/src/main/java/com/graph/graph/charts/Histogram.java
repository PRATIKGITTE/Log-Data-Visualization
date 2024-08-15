package com.graph.graph.charts;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;

public class Histogram implements GraphServices {

    @Value("${project.image}")
    String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        ReadingData data = new ReadingData(path);
        Map<String, Integer> logData = data.getHistogramData();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> entry : logData.entrySet()) {
            dataset.addValue(entry.getValue(), "Frequency", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart("Log Frequency Histogram", // Chart title
                "Log Type", // X-Axis Label
                "Frequency", // Y-Axis Label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                false, // Include legend
                true, // Tooltips
                false // URLs
        );

        // Get the plot object and rotate the category labels
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.toRadians(45))
        );

        String pathImage = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);
        try {
            ChartUtilities.saveChartAsJPEG(new File(pathImage), chart, 500, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
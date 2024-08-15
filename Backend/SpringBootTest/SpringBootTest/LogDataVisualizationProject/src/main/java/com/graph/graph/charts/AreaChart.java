package com.graph.graph.charts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

public class AreaChart implements GraphServices {

    @Value("${project.image}") // Ye value properties file se read hogi jahan pe images ko save karna hai
    String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        // ReadingData class se CSV file ka data read kiya ja raha hai
        ReadingData data = new ReadingData(path);
        
        // Area chart ke liye data ko retrieve kar rahe hain
        Map<String, List<Double>> areaData = data.getAreaData();

        int n = 0;
        // Data set ki size find karne ke liye loop
        for (Map.Entry<String, List<Double>> e : areaData.entrySet()) {
            n = e.getValue().size();
            break;
        }

        // Data ko double array mein convert karna
        double values[][] = new double[areaData.size()][n];
        ArrayList<double[]> arr = new ArrayList<>();
        
        // Map ke har entry ko double array mein convert kar rahe hain
        for (Map.Entry<String, List<Double>> e : areaData.entrySet()) {
            double[] d = new double[n];
            int col = 0;
            for (double num : e.getValue()) {
                d[col] = num;
                col++;
            }
            arr.add(d);
        }
        
        // List se values ko 2D array mein daal rahe hain
        int i = 0;
        for (double[] d : arr) {
            values[i] = d;
        }

        // JFreeChart ke liye CategoryDataset banaya ja raha hai
        CategoryDataset dataset = DatasetUtilities.createCategoryDataset("Series ", "Type ", values);

        // Area chart create kar rahe hain
        JFreeChart chart = ChartFactory.createAreaChart(
                "Area Chart", // Chart ka title
                "X axis", // X-axis label
                "Y axis", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Chart orientation
                true, // Legend chahiye ya nahi
                true, // Tooltips enable karein ya nahi
                false // URLs generate karein ya nahi
        );

        // Chart ko save karne ke liye path banate hain
        String pathImage = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);

        try {
            // Chart ko JPEG file ke roop mein save karte hain
            ChartUtilities.saveChartAsJPEG(new File(pathImage), chart, 500, 300);
        } catch (IOException e) {
            // Agar koi error aata hai to uska stack trace print karenge
            e.printStackTrace();
        }
    }
}

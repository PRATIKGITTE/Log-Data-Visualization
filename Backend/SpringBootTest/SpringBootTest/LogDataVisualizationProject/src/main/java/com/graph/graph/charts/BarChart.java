package com.graph.graph.charts;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

public class BarChart implements GraphServices {

    @Value("${project.image}") // Ye value properties file se read hogi jahan pe images ko save karna hai
    String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        // ReadingData class ka use karke CSV file se data read kar rahe hain
        ReadingData data = new ReadingData(path);
        
        // Bar chart ke liye data ko retrieve kar rahe hain
        Map<String, Map<String, Double>> barData = data.getBarData();

        // DefaultCategoryDataset create kar rahe hain jo JFreeChart ke liye data hold karega
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        // BarData ko dataset mein populate kar rahe hain
        for (Map.Entry<String, Map<String, Double>> e : barData.entrySet()) {
            for (Map.Entry<String, Double> m : e.getValue().entrySet()) {
                // yahan e.getKey() series ka naam hai aur m.getKey() category ka naam hai
                dataset.addValue(m.getValue(), e.getKey(), m.getKey());
            }
        }

        // JFreeChart ka use karke Bar chart banaya ja raha hai
        JFreeChart chart = ChartFactory.createBarChart(
            "Bar Graph", // Chart ka title
            "X axis", // X-axis label
            "Y axis", // Y-axis label
            dataset // Data
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

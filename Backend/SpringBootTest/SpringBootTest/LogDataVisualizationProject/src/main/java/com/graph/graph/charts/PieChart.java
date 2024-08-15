package com.graph.graph.charts;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

public class PieChart implements GraphServices {

    @Value("${project.image}") // Properties file se image path ko fetch kar rahe hain
    String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        // CSV file se data read karne ke liye ReadingData class ka use kar rahe hain
        ReadingData data = new ReadingData(path);
        
        // Pie chart ke data ko retrieve kar rahe hain
        Map<String, Integer> pieData = data.getPieData();
        
        // DefaultPieDataset create kar rahe hain jo JFreeChart ke liye data ko hold karega
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        
        // Data ko process kar rahe hain aur pieDataset mein add kar rahe hain
        for (Map.Entry<String, Integer> e : pieData.entrySet()) {
            pieDataset.setValue(e.getKey(), e.getValue()); // Key ko label aur value ko slice size set kar rahe hain
        }

        // JFreeChart ka use karke Pie Chart create kar rahe hain
        JFreeChart chart = ChartFactory.createPieChart(
            "logs With Respect To Time", // Chart ka title
            pieDataset, // Data
            true, // Legend ko show karna hai ya nahi
            true, // Tooltips ko show karna hai ya nahi
            false // URLs ko generate karna hai ya nahi
        );

        // Image ka path specify kar rahe hain
        String pathImage = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);

        try {
            // Chart ko JPEG format mein save kar rahe hain
            ChartUtilities.saveChartAsJPEG(new File(pathImage), chart, 500, 300);
        } catch (IOException e) {
            // Agar koi error aata hai to uska stack trace print karenge
            e.printStackTrace();
        }
    }
}

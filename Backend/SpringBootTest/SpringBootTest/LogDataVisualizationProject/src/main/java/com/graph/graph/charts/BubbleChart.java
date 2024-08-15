package com.graph.graph.charts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYZDataset;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

public class BubbleChart implements GraphServices {

    @Value("${project.image}")
    String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        ReadingData data = new ReadingData(path);
        Map<String, List<Integer>> bubbleData = data.getBubbleData();

        String labels[] = new String[3];
        int i = 0;
        int n = 0;

        for (Map.Entry<String, List<Integer>> iter : bubbleData.entrySet()) {
            n = iter.getValue().size();
            break;
        }

        List<double[]> arr = new ArrayList<>();
        for (Map.Entry<String, List<Integer>> e : bubbleData.entrySet()) {
            labels[i] = e.getKey();
            double a[] = new double[n];
            int col = 0;
            for (int num : e.getValue()) {
                a[col] = (double) num;
                col++;
            }
            arr.add(a);
            i++;
        }

        DefaultXYZDataset dataset = new DefaultXYZDataset();
        double values[][] = { arr.get(0), arr.get(1), arr.get(2) };
        dataset.addSeries("Series 1", values);

        JFreeChart chart = ChartFactory.createBubbleChart(
            "BubbleChart",
            labels[0], // X-axis label (e.g., CPUUsage)
            labels[1], // Y-axis label (e.g., MemoryUsage)
            dataset
        );

        String pathImage = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);
        try {
            ChartUtilities.saveChartAsJPEG(new File(pathImage), chart, 500, 300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

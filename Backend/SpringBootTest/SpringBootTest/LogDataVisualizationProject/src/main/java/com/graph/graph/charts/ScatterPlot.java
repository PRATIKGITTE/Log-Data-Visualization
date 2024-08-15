package com.graph.graph.charts;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Value;

import com.graph.graph.CSVReading.ReadingData;
import com.graph.graph.chart.service.GraphServices;

public class ScatterPlot implements GraphServices {

    @Value("${project.image}")
    private String imagePath;

    @Override
    public void generateGraph(String path, String imageName) throws IOException {
        ReadingData data = new ReadingData(path);
        Map<String, List<String>> scatterData = data.getScatterData();

        // Extract the title if available, otherwise use a default title
        String title = scatterData.containsKey("Title") ? scatterData.get("Title").get(0) : "API Log Scatter Plot";

        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Response Times");

        List<String> timestamps = scatterData.get("Timestamp");
        List<String> responseTimes = scatterData.get("ResponseTime");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        for (int i = 0; i < timestamps.size(); i++) {
            try {
                long timestampMillis = dateFormat.parse(timestamps.get(i)).getTime(); // Convert timestamp to milliseconds
                double responseTime = Double.parseDouble(responseTimes.get(i));
                series.add(timestampMillis, responseTime);
            } catch (ParseException | NumberFormatException e) {
                System.err.println("Skipping invalid data point: Timestamp=" + timestamps.get(i) + ", ResponseTime=" + responseTimes.get(i));
            }
        }

        dataset.addSeries(series);

        // Create scatter plot chart
        JFreeChart chart = ChartFactory.createScatterPlot(
            title,
            "Timestamp",
            "Response Time",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        // Format the X-axis (timestamps)
        XYPlot plot = chart.getXYPlot();
        DateAxis dateAxis = new DateAxis("Timestamp");
        dateAxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        plot.setDomainAxis(dateAxis);

        String pathImage = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);
        try {
            ChartUtilities.saveChartAsJPEG(new File(pathImage), chart, 600, 400);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

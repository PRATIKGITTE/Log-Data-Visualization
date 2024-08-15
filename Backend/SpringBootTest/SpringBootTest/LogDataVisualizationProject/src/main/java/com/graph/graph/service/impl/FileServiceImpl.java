package com.graph.graph.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.graph.graph.charts.AreaChart;
import com.graph.graph.charts.BarChart;
import com.graph.graph.charts.BubbleChart;
import com.graph.graph.charts.Histogram;
import com.graph.graph.charts.LineChart;
import com.graph.graph.charts.PieChart;
import com.graph.graph.charts.ScatterPlot;
import com.graph.graph.chart.service.GraphServices;
import com.graph.graph.services.FileServices;

@Service
public class FileServiceImpl implements FileServices {

	@Value("${project.image}")
	private String imagePath; // Yeh variable image store karne ke path ko hold karega

	// Yeh map various chart types ke liye chart generators ko hold karta hai
	private final Map<String, GraphServices> chartGenerators = new HashMap<>();

	/**
	 * Constructor mein hum different chart generators ko initialize kar rahe hain.
	 */
	public FileServiceImpl() {
		chartGenerators.put("histogram", new Histogram());
		chartGenerators.put("area", new AreaChart());
		chartGenerators.put("bubble", new BubbleChart());
		chartGenerators.put("scatter", new ScatterPlot());
		chartGenerators.put("pie", new PieChart());
		chartGenerators.put("line", new LineChart());
		chartGenerators.put("bar", new BarChart());
	}

	/**
	 * Yeh method file ko specified path pe upload karta hai aur provided graph name
	 * ke basis pe ek chart generate karta hai.
	 *
	 * @param path      Jis path pe file upload hogi.
	 * @param file      Jo file upload karni hai.
	 * @param graphName Chart ka naam jo generate karna hai.
	 * @return Generated image file ka naam return karta hai.
	 * @throws IOException Agar file operations ke dauraan koi error aata hai toh.
	 */
	@Override
	public String uploadImage(String path, MultipartFile file, String graphName) throws IOException {
		 if (graphName == null || graphName.trim().isEmpty()) {
		        throw new IllegalArgumentException("Graph name cannot be null or empty.");
		    }
		// Original file name
	    String originalFilename = file.getOriginalFilename();
	    if (originalFilename == null || originalFilename.isEmpty()) {
	        throw new IllegalArgumentException("Uploaded file must have a name.");
	    }

	    // Generating a random file name
	    String randomID = UUID.randomUUID().toString();
	    String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
	    String fileName = randomID.concat(extension);

	    // Constructing the full file path
	    String filePath = Paths.get(path, fileName).toString();

	    // Ensuring the directory exists, if not, create it
	    File directory = new File(path);
	    if (!directory.exists()) {
	        boolean dirCreated = directory.mkdirs();
	        if (!dirCreated) {
	            throw new IOException("Failed to create directory: " + path);
	        }
	    }

	    // Copying the file to the specified path
	    try {
	        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
	        System.out.println("File uploaded successfully to: " + filePath);
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new IOException("Error copying file to " + filePath, e);
	    }

	    // Generating the graph image
	    String imageName = randomID + ".jpeg";
	    GraphServices chartGenerator = chartGenerators.get(graphName.toLowerCase());
	    if (chartGenerator != null) {
	        chartGenerator.generateGraph(filePath, imageName);
	        System.out.println("Graph generated successfully: " + imageName);
	    } else {
	        throw new IllegalArgumentException("Invalid graph name: " + graphName);
	    }

	    // Returning the generated image name
	    return imageName;
	}


	/**
	 * Yeh method ek file ko InputStream ke form mein retrieve karta hai specified
	 * path se.
	 *
	 * @param path      Base path jahan file locate hai.
	 * @param imageName Image file ka naam.
	 * @return InputStream return karta hai file ke liye, ya null agar file nahi
	 *         milti.
	 */
	@Override
	public InputStream getResource(String path, String imageName) {
		 String fullPath = String.format("C:\\Users\\Pratik\\Downloads\\Project\\Backend\\GraphGeneratedBYJfree\\%s", imageName);
	     
		try {
			System.out.println("File dhoondh rahe hain is path pe: " + fullPath);
			return new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}

package com.graph.graph.CSVReading;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;

public class ReadingData {

	private CSVReader reader;
	String[] nextline;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	private BufferedReader reader2;

	/**
	 * Constructor to initialize the CSVReader with the given file path.
	 *
	 * @param path The path of the CSV file to be read.
	 */

	public ReadingData(String path) {
		try {
// Constructor me CSV file ka path pass kar rahe ho, aur reader initialize kar
// rahe ho.
			reader = new CSVReader(new FileReader(path));
			reader2 = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Log error if the file is not found
		}
	}

	public List<String> getLogData() throws IOException {
		List<String> logData = new ArrayList<>();

		try {
			String[] nextline;

			while ((nextline = reader.readNext()) != null) {
				if (nextline.length >= 3) {
// Combine timestamp, log level, and message into a single string
					String logEntry = String.join(",", nextline);
					logData.add(logEntry); // Add the combined string to the list
				}
			}
		} catch (Exception e) {
			System.out.println("error if here getLogData ");
		}
		return logData; // Return the list of log entries
	}

	/**
	 * Reads data for PieChart from the CSV file.
	 *
	 * @return A map where keys are labels and values are integer counts.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	public Map<String, Integer> getPieData() throws IOException {
		Map<String, Integer> pieData = new LinkedHashMap<>();
		nextline = reader.readNext(); // Read header line
		while ((nextline = reader.readNext()) != null) {
			if (nextline != null) {
				pieData.put(nextline[0], Integer.parseInt(nextline[1]));
			}
		}
		return pieData;
	}

	/**
	 * Reads data for Histogram from the CSV file.
	 *
	 * @return A map where keys are categories and values are lists of integers for
	 *         each category.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	public Map<String, Integer> getHistogramData() throws IOException {
		Map<String, Integer> logData = new LinkedHashMap<>();
		String line;

		// Skip the header
		reader2.readLine();

		while ((line = reader2.readLine()) != null) {
			String logType = extractLogType(line);
			logData.put(logType, logData.getOrDefault(logType, 0) + 1);
		}

		reader2.close();
		return logData;
	}

	private String extractLogType(String line) {
		// Assuming the log type is the first part before the first comma
		return line.split(",")[0];
	}

	/**
	 * Reads data for BubbleChart from the CSV file.
	 *
	 * @return A map where keys are series names and values are lists of integers
	 *         for each series.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	public Map<String, List<Integer>> getBubbleData() throws IOException {
		Map<String, List<Integer>> bubbleData = new LinkedHashMap<>();

		// Create lists to hold each metric
		List<Integer> cpuUsageList = new ArrayList<>();
		List<Integer> memoryUsageList = new ArrayList<>();
		List<Integer> diskIOList = new ArrayList<>();

		// Read the CSV file
		String[] nextline;

		// Skip the header row
		reader.readNext();

		// Read each row in the CSV file
		while ((nextline = reader.readNext()) != null) {
			if (nextline != null && nextline.length >= 5) {
				// Parsing CPUUsage, MemoryUsage, and DiskIO values
				cpuUsageList.add(Integer.parseInt(nextline[2]));
				memoryUsageList.add(Integer.parseInt(nextline[3]));
				diskIOList.add(Integer.parseInt(nextline[4]));
			}
		}

		// Adding the extracted data to the map
		bubbleData.put("CPUUsage", cpuUsageList);
		bubbleData.put("MemoryUsage", memoryUsageList);
		bubbleData.put("DiskIO", diskIOList);

		return bubbleData;
	}

	/**
	 * Reads data for LineChart from the CSV file.
	 *
	 * @return A map where keys are series names and values are lists of data
	 *         points.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */

// Read the rest of the data
// Method to read the CSV file and generate line data for charting
	public Map<String, List<String>> getLineData() throws IOException {
		Map<String, List<String>> lineData = new HashMap<>();
		lineData.put("INFO", new ArrayList<>()); // Initialize list for INFO level logs
		lineData.put("WARN", new ArrayList<>()); // Initialize list for WARN level logs
		lineData.put("ERROR", new ArrayList<>()); // Initialize list for ERROR level logs

		try { // Initialize CSVReader with the file path
			String[] nextline;
			LocalDateTime startTime = null;

			while ((nextline = reader.readNext()) != null) { // Read each line from the CSV
				if (nextline.length >= 2) { // Ensure there are at least two columns (Timestamp and LogLevel)
					String timestampStr = nextline[0]; // Extract timestamp
					String logLevel = nextline[1]; // Extract log level

					LocalDateTime timestamp = LocalDateTime.parse(timestampStr, formatter); // Parse timestamp

// Set start time if not already set
					if (startTime == null) {
						startTime = timestamp;
					}

// Calculate time difference in seconds from start time
					long timeElapsed = java.time.Duration.between(startTime, timestamp).getSeconds();

// Add the timeElapsed to the appropriate log level series
					if (lineData.containsKey(logLevel)) {
						lineData.get(logLevel).add(timeElapsed + "," + (lineData.get(logLevel).size() + 1));
					}
				}
			}
		} catch (Exception e) {
			System.out.println("error if here");
		}
		return lineData; // Return the processed line data
	}

	/**
	 * Reads data for AreaChart from the CSV file.
	 *
	 * @return A map where keys are series names and values are lists of double
	 *         values.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	public Map<String, List<Double>> getAreaData() throws IOException {
		Map<String, List<Double>> areaData = new LinkedHashMap<>();
		nextline = reader.readNext(); // Read header line
		for (String col : nextline) {
			if (!col.isEmpty()) {
				areaData.put(col, new ArrayList<>());
			}
		}
		while ((nextline = reader.readNext()) != null) {
			if (nextline != null) {
				int i = 0;
				for (Map.Entry<String, List<Double>> e : areaData.entrySet()) {
					String val = nextline[i];
					e.getValue().add(Double.parseDouble(val));
					i++;
				}
			}
		}
		return areaData;
	}

	/**
	 * Reads data for ScatterChart from the CSV file.
	 *
	 * @return A map where keys are series names and values are lists of data points
	 *         (formatted as "x,y").
	 * @throws IOException If an I/O error occurs while reading the file.
	 */

// change i have made
// Assuming this code is in the ReadingData class
	public Map<String, List<String>> getScatterData() throws IOException {
		Map<String, List<String>> logData = new LinkedHashMap<>();
		String headerLine = reader2.readLine();

		if (headerLine == null) {
			System.err.println("CSV file is empty.");
			return logData;
		}

		String[] headers = headerLine.split(",");
		for (String header : headers) {
			logData.put(header.trim(), new ArrayList<>());
		}

		String line;
		while ((line = reader2.readLine()) != null) {
			String[] values = line.split(",");
			for (int i = 0; i < headers.length; i++) {
				if (i < values.length) {
					logData.get(headers[i].trim()).add(values[i].trim());
				} else {
					logData.get(headers[i].trim()).add(""); // Handle missing values
				}
			}
		}

		reader.close();
		return logData;
	}

	/**
	 * Reads data for BarChart from the CSV file.
	 *
	 * @return A map where keys are categories and values are maps of sub-categories
	 *         and their corresponding values.
	 * @throws IOException If an I/O error occurs while reading the file.
	 */
	public Map<String, Map<String, Double>> getBarData() throws IOException {
		Map<String, Map<String, Double>> barData = new LinkedHashMap<>();
		nextline = reader.readNext(); // Read header line
		int i = 0;
		for (String col : nextline) {
			if (i == 0) {
				i++;
				continue;
			}
			if (!col.isEmpty()) {
				barData.put(col, new LinkedHashMap<String, Double>());
			}
		}
		while ((nextline = reader.readNext()) != null) {
			if (nextline != null) {
				int index = 1;
				String cat = nextline[0];
				for (Map.Entry<String, Map<String, Double>> e : barData.entrySet()) {
					e.getValue().put(cat, Double.parseDouble(nextline[index]));
					index++;
				}
			}
		}
		return barData;
	}
}

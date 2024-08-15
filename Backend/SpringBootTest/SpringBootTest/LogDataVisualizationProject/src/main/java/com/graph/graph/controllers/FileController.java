package com.graph.graph.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.graph.graph.payload.FileResponse;
import com.graph.graph.services.FileServices;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // CORS configuration for allowing requests from
																// localhost:3000
@RestController
@RequestMapping("/file") // Base URL for this controller
public class FileController {

	@Autowired
	private FileServices fileService; // FileServices class ka instance

	@Value("${project.csv}")
	private String csvPath; // CSV files store karne ke liye path

	@Value("${project.image}")
	private String imagePath; // Generated images store karne ke liye path

	/**
	 * CSV file ko upload karne ke liye endpoint.
	 *
	 * @param file      CSV file jo upload karni hai.
	 * @param graphName File ke sath associated graph ka naam.
	 * @return ResponseEntity jo upload operation ke result ko contain karti hai.
	 */
	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("csv") MultipartFile file,
	                                               @RequestParam("graphName") String graphName) {
	    // Validate graphName
	    if (graphName == null || graphName.trim().isEmpty()) {
	        return ResponseEntity.badRequest().body(new FileResponse(null, "Graph name is required"));
	    }

	    try {
	        // Upload the CSV file and associate it with the graph name
	        String fileName = fileService.uploadImage(csvPath, file, graphName);
	        return ResponseEntity.ok(new FileResponse(fileName, "File is successfully uploaded"));
	    } catch (Exception e) {
	        e.printStackTrace(); // Consider using a logger for production code
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new FileResponse(null, "File is not uploaded due to server error"));
	    }
	}


	/**
	 * Image file ko download karne ke liye endpoint.
	 *
	 * @param imageName Image file ka naam jo download karni hai.
	 * @return ResponseEntity jo image file data ko contain karti hai.
	 */
	@GetMapping("/images/{imageName}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("imageName") String imageName) {
		// Requested image ke liye File object create kar rahe hain
		File file = new File(imagePath, imageName);

		// Check kar rahe hain agar file exist karti hai
		if (!file.exists()) {
			// Agar file nahi mili to 404 Not Found response return kar rahe hain
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		try (InputStream resource = new FileInputStream(file)) {
			// File data ko byte array mein read kar rahe hain
			byte[] data = StreamUtils.copyToByteArray(resource);

			// HTTP headers prepare kar rahe hain response ke liye
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG); // Content type set kar rahe hain; zarurat ho to adjust kar
															// sakte hain
			headers.setContentDispositionFormData("attachment", imageName); // Download ke liye file name set kar rahe
																			// hain

			// Image data ko appropriate headers ke sath return kar rahe hain
			return ResponseEntity.ok().headers(headers).body(data);
		} catch (IOException e) {
			// Error ko log kar rahe hain aur error response return kar rahe hain
			e.printStackTrace(); // Yahan logger ka use karna behtar hoga
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}

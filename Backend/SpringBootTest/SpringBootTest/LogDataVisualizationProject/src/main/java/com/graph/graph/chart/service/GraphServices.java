package com.graph.graph.chart.service;

import java.io.IOException;

/**
 * Ye interface alag-alag types ke graphs generate karne ke liye banaya gaya hai.
 */
public interface GraphServices {

    /**
     * Ye method graph generate karega jo specified file ke data par based hoga aur usse diya gaya image name ke saath save karega.
     *
     * @param path       File ka path jisme graph ka data hai.
     * @param imageName  Image file ka naam jisme generated graph save hoga.
     * @throws IOException Agar data read karte waqt ya graph save karte waqt koi error aata hai to ye exception throw karega.
     */
    void generateGraph(String path, String imageName) throws IOException;

	/**
	 * Generates a histogram chart based on the provided data.
	 *
	 * @param path      Path to the data file
	 * @param imageName Name of the output image file
	 * @param title     Chart title
	 * @param xAxisLabel X-axis label
	 * @param yAxisLabel Y-axis label
	 * @param bins      Number of bins for the histogram
	 * @throws IOException If an I/O error occurs
	 */

}

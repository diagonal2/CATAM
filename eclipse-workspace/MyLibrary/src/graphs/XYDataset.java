package graphs;

import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class XYDataset {
	
	public XYDataset(String chartTitle, String xLabel, String yLabel) {
		
		title = chartTitle;
		xLbl = xLabel;
		yLbl = yLabel;
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		
	}
	
	private XYSeriesCollection collection = new XYSeriesCollection();
	private String title, xLbl, yLbl;
	private NumberAxis xAxis = new NumberAxis();
	private NumberAxis yAxis = new NumberAxis();
	private JFreeChart chart;
	
	private XYSeries getSeries(String key) {
		
		XYSeries series;
		if (collection.indexOf(key) == -1) {
			series = new XYSeries(key);
			collection.addSeries(series);
		}
		else series = collection.getSeries(collection.indexOf(key));
		
		return series;
		
	}
	
	public void addSeries(double[][] data, int number, String key) {
		
		XYSeries series = getSeries(key);
		for (int i = 0; i < number; i++) series.add(data[i][0], data[i][1]);
		
	}
	
	public void addSeries(double[] xData, double[] yData, int number, String key) {
		
		XYSeries series = getSeries(key);
		for (int i = 0; i < number; i++) series.add(xData[i], yData[i]);
		
	}
	
	public void addSeries(double xDatum, double yDatum, String key) {
		
		XYSeries series = getSeries(key);
		series.add(xDatum, yDatum);
		
	}
	
	public void addSeries(XYData d1, String key) {
		
		XYSeries series = getSeries(key);
		for (int i = 0; i < d1.n; i++) series.add(d1.x[i], d1.y[i]);
		
	}
	
	public void useNumberAxis(char axis) {
		
		if (axis == 'x') xAxis = new NumberAxis(xLbl);
		if (axis == 'y') yAxis = new NumberAxis(yLbl);
				
	}
	
	public void useLogAxis(char axis) {
		
		if (axis == 'x') xAxis = new LogarithmicAxis(xLbl);
		if (axis == 'y') yAxis = new LogarithmicAxis(yLbl); 
		
	}
	
	public void setRange(char axis, double lower, double higher) {
		
		if (axis == 'x') xAxis.setRange(lower, higher);
		if (axis == 'y') yAxis.setRange(lower, higher);
		
	}
	
	public void setTickUnit(char axis, double tick) {
		
		if (axis == 'x') xAxis.setTickUnit(new NumberTickUnit(tick));
		if (axis == 'y') yAxis.setTickUnit(new NumberTickUnit(tick));
		
	}
	
	public void squareAxis() {
		
		double xLength = xAxis.getRange().getLength();
		double yLength = yAxis.getRange().getLength();
		
		if (xLength < yLength) {
			double xCen = xAxis.getRange().getCentralValue();
			xAxis.setRange(xCen - yLength / 2, xCen + yLength / 2);
		} else {
			double yCen = yAxis.getRange().getCentralValue();
			xAxis.setRange(yCen - xLength / 2, yCen + xLength / 2);
		}
		
	//	yAxis.setTickUnit(xAxis.getTickUnit());
		
	}
	
	public void display() {
		
		display(1000, 600);
		
	}
	
	public void display(int length, int width) {
		
		ApplicationFrame af = new ApplicationFrame(chart.getTitle().getText());
		
		ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(length, width));
	    
	    af.setContentPane(chartPanel);
		af.pack();
		RefineryUtilities.centerFrameOnScreen(af);
		af.setVisible(true);
		
	}
	
	public void save(String file) throws IOException {
		
		ChartUtilities.saveChartAsJPEG(new File(file), chart, 1000, 600);
		
	}
	
	public void save(String file, int length, int width) throws IOException {
		
		ChartUtilities.saveChartAsJPEG(new File(file), chart, length, width);
		
	}
	
	public void plotLine(boolean showShape) {
		
		chart = new JFreeChart(title, new XYPlot(collection, xAxis, yAxis, new XYLineAndShapeRenderer(true, showShape)));
		
	}
	
	public void plotScatter() {
		
		chart = new JFreeChart(title, new XYPlot(collection, xAxis, yAxis, new XYLineAndShapeRenderer(false, true)));
		
	}
	
	public void plotScatter(double dotSize) {
		
		XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
		renderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-dotSize / 2, -dotSize / 2, dotSize, dotSize));
		chart = new JFreeChart(title, new XYPlot(collection, xAxis, yAxis, renderer));
		
	}
	
}

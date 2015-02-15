package edu.seu.hwq.spider.data;

abstract class PageLayout {

	public double sim4Title = 0;
	public double sim4Tag = 0;
	public double sim4Content = 0;

	public abstract void setSim4Content();

	public double getTotalSim(double weight4Title, double weight4Tag) {
		double total;
		total = weight4Title * sim4Title + weight4Tag * sim4Tag
				+ (1 - weight4Title - weight4Tag) * sim4Content;

		return total;
	}
}

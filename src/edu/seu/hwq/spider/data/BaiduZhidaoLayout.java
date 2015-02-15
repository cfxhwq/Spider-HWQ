package edu.seu.hwq.spider.data;


public class BaiduZhidaoLayout extends QnALayout {

	public BaiduZhidaoLayout(double weight4Q) {
		// TODO Auto-generated constructor stub
		super.weight4Q = weight4Q;
		super.weight4A = 1 - weight4Q;
	}

	public void setSim4Content() {
		// TODO Auto-generated method stub
		sim4Content = weight4Q * sim4Question + weight4A * sim4Answer;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

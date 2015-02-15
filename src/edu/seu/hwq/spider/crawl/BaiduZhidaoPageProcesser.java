package edu.seu.hwq.spider.crawl;

import edu.seu.hwq.spider.data.BaiduZhidaoLayout;
import edu.seu.hwq.spider.util.VSM;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

public class BaiduZhidaoPageProcesser implements PageProcessor {

	@Override
	public void process(Page page) {

	}

	private double getSource(Page page) {
		double source;
		double weight = 0.5;

		double sum_SS = getSharkSearch(page);

		double sum_PR = getPR();

		source = weight * sum_SS + (1 - weight) * sum_PR;
		return source;
	};

	private double getSharkSearch(Page page) {
		double weight = 0.5;
		double sum = weight * getInherited(page) + (1 - weight)
				* getNeighborhood(page.getUrl().toString());
		return sum;
	}

	private double getInherited(Page page) {
		double weight4Title = 0.5;
		double weight4Tag = 0;
		double weight4Question;

		BaiduZhidaoLayout instance;

		String title = page.getHtml()
				.xpath("//*[@id=\"wgt-ask\"]/h1/span/text()").toString();
		String question = page.getHtml()
				.xpath("//*[@id=\"wgt-ask\"]/pre/text()").toString();
		page.putField("title", title);
		page.putField("question", question);

		String bestAnswner = page.getHtml()
				.xpath("//*[@class=\"best-text mb-10\"]/text()").toString();
		if (bestAnswner == null) {
			bestAnswner = page.getHtml()
					.xpath("//*[@class=\"recommend-text mb-10\"]/text()")
					.toString();
		} else {
			String tmp = page.getHtml()
					.xpath("//*[@class=\"recommend-text mb-10\"]/text()")
					.toString();
			if (tmp != null) {
				bestAnswner = "#BestAnswner1:\n" + bestAnswner
						+ "\n#BestAnswner2:\n" + tmp;
			}
		}

		if (bestAnswner != null) {
			page.putField("answer", bestAnswner);
			instance = new BaiduZhidaoLayout(weight4Question = 0.5);
		} else {
			instance = new BaiduZhidaoLayout(weight4Question = 0.7);
		}

		instance.sim4Title = VSM.getSim(title);
		instance.sim4Question = VSM.getSim(question);
		if (bestAnswner != null) {
			instance.sim4Answer = VSM.getSim(bestAnswner);
		} else {

		}

		String tag = page.getHtml()
				.xpath("//*[@id=\"ask-info\"]/span[3]/a/text()").toString();
		if (tag != null) {
			page.putField("tag", tag);
			weight4Title = 0.3;
			weight4Tag = 0.3;
			instance.sim4Tag = VSM.getSim(tag);
		}

		double total = instance.getTotalSim(weight4Title, weight4Tag);
		System.out.println("Source >>>\t" + total);

		return 0;
	}

	private double getNeighborhood(String url) {
		return 0;
	}

	private double getPR() {// PageRank Value

		return 0;
	}

	@Override
	public Site getSite() {
		return Site.me().setDomain("http://zhidao.baidu.com/question/");
		// .addStartUrl("http://bbs.tianya.cn/"); //To change body of
		// implemented methods use File | Settings | File Templates.
	}
}

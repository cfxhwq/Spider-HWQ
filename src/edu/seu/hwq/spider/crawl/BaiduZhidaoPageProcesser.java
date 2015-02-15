package edu.seu.hwq.spider.crawl;

import java.util.Iterator;
import java.util.List;

import edu.seu.hwq.spider.data.BaiduZhidaoLayout;
import edu.seu.hwq.spider.data.Inherited;
import edu.seu.hwq.spider.util.VSM;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class BaiduZhidaoPageProcesser implements PageProcessor {
	public static void main(String[] args) {
		Spider.create(new BaiduZhidaoPageProcesser())
				.addUrl("http://zhidao.baidu.com/question/1755448004224524868.html")// .thread(6)
				.start();
	}

	long id;

	@Override
	public void process(Page page) {
		id = Long.valueOf(page.getUrl()
				.regex("http://zhidao.baidu.com/question/(\\d+).html.*")
				.toString());
		System.out.println(id);
		// get The sub Links
		List<String> subLinks = page.getHtml()
				.xpath("//*[@class=\"related-list line\"]/ul/li/a/@href")
				.regex("http://zhidao.baidu.com/question/\\d+.html.*").all();
		// System.out.println(subLinks);

		double source = getSource(getSharkSearch(page), getPR());
		if (source > 0.4) { // go on

			 page.addTargetRequests(subLinks);
			 List<String> subIds = page.getHtml()
			 .xpath("//*[@class=\"related-list line\"]/ul/li/a/@href")
			 .regex("http://zhidao.baidu.com/question/(\\d+).html.*")
			 .all();
			 for (Iterator iterator = subIds.iterator(); iterator.hasNext();)
			 {
			 long father = Long.valueOf((String) iterator.next());
			
			 Inherited.inherited.put(id, father);
			 }
		} else
			return; // stop
	}

	private double getSource(double sum_SS, double sum_PR) {
		double source;
		double weight = 0.5;

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
		if (question != null)
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
		if (question != null)
			instance.sim4Question = VSM.getSim(question);
		else
			instance.sim4Question = instance.sim4Title;
		if (bestAnswner != null) {
			instance.sim4Answer = VSM.getSim(bestAnswner);
		} else {
			String tmp = page.getHtml()
					.xpath("//*[@class=\"answer-text mb-10\"]/text()").all()
					.toString();
			instance.sim4Answer = VSM.getSim(tmp);
		}

		String tag = page.getHtml()
				.xpath("//*[@id=\"ask-info\"]/span[3]/a/text()").toString();
		if (tag != null) {
			page.putField("tag", tag);
			weight4Title = 0.3;
			weight4Tag = 0.3;
			instance.sim4Tag = VSM.getSim(tag);
		}

		instance.setSim4Content();
		double total = instance.getTotalSim(weight4Title, weight4Tag);
		System.out.println("Source >>>\t" + total);

		if (total > 0.5) {
			Inherited.kv.put(id, 0.5 * total);
			return 0.5 * total;
		} else if (Inherited.kv.containsKey(Inherited.inherited.get(id))) {
			Inherited.kv.put(id,
					0.5 * Inherited.kv.get(Inherited.inherited.get(id)));
			return 0.5 * 0.5;
		} else
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

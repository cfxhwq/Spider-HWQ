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
	BaiduZhidaoLayout instance;

	@Override
	public void process(Page page) {
		id = Long.valueOf(page.getUrl()
				.regex("http://zhidao.baidu.com/question/(\\d+).html.*")
				.toString());

		// get The sub Links
		List<String> subLinks = page.getHtml()
				.xpath("//*[@class=\"related-list line\"]/ul/li/a/@href")
				.regex("(http://zhidao.baidu.com/question/\\d+.html).*").all();
		// System.out.println(subLinks);

		double inherited = getInherited(page);
		double PR = getPR();
		List<String> subAnchors = page.getHtml()
				.xpath("//*[@class=\"related-list line\"]/ul/li/a/text()")
				.all();
		System.out.println(subAnchors);
		double group_score = getGroupSource(subAnchors);
		double group_strength = getGroupStrength();
		for (Iterator iterator = subLinks.iterator(); iterator.hasNext();) {
			String link = (String) iterator.next();
			double source = getSource(
					getSharkSearch(inherited,
							getNeighborhood(group_score, group_strength)), PR);
			if (source > 0.4) { // go on
				page.addTargetRequest(link);
			}
		}

		List<String> subIds = page.getHtml()
				.xpath("//*[@class=\"related-list line\"]/ul/li/a/@href")
				.regex("http://zhidao.baidu.com/question/(\\d+).html.*").all();
		for (Iterator iterator = subIds.iterator(); iterator.hasNext();) {
			long son = Long.valueOf((String) iterator.next());
			Inherited.inherited.put(son, id);
		}
	}

	private double getSource(double sum_SS, double sum_PR) {
		double source;
		double weight = 0.5;

		source = weight * sum_SS + (1 - weight) * sum_PR;
		return source;
	};

	private double getSharkSearch(double inherited, double neighborhood) {
		double weight = 0.5;
		double sum = weight * inherited + (1 - weight) * neighborhood;
		return sum;
	}

	private double getInherited(Page page) {
		double weight4Title = 0.5;
		double weight4Tag = 0;
		double weight4Question;

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

	private double getGroupSource(List<String> anchors) {
		double group_score = 0;
		for (Iterator iterator = anchors.iterator(); iterator.hasNext();) {
			String anchor = (String) iterator.next();
			group_score += VSM.getSim(anchor);
		}
		group_score = group_score / anchors.size();
		return group_score;
	}

	private double getGroupStrength() {
		double group_strength;
		group_strength = 1 / Math.log(0);
		return group_strength;
	}

	private double getNeighborhood(double group_source, double group_strength) {
		double total, anchor_url;
		double weight = 0.4;

		anchor_url = instance.sim4Title;

		total = weight * anchor_url + (1 - weight) * group_source
				* group_strength;
		return total;
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

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class BaiduZhidaoPageProcesser implements PageProcessor {

	@Override
	public void process(Page page) {
		// List<String> strings =
		// page.getHtml().regex("<a[^<>]*href=[\"']{1}(/post-free.*?\\.shtml)[\"']{1}").all();
		// page.addTargetRequests(strings);
		// page.putField("title",
		// page.getHtml().xpath("//div[@id='post_head']//span[@class='s_title']//b"));
		// page.putField("body",page.getHtml().smartContent());
		// System.out.println(page.toString());

	}

	private double getSource(Page page) {
		double source;
		double weight = 0.5;

		double sum_SS = getSharkSpark(page);

		double sum_PR = getPR();

		source = weight * sum_SS + (1 - weight) * sum_PR;
		return source;
	};

	private double getSharkSpark(Page page) {
		double weight = 0.5;
		double sum = weight * getInherited(page.toString()) + (1 - weight)
				* getNeighborhood(page.getUrl().toString());
		return sum;
	}

	private double getInherited(String content) {

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

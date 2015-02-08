import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.PriorityScheduler;

public class Crawler {

	// static Map
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Spider baiduZhidao = Spider.create(new BaiduZhidaoPageProcesser());
		// baiduZhidao.setScheduler(new PriorityScheduler());
		// baiduZhidao.addPipeline(null);
		baiduZhidao
				.addUrl("http://zhidao.baidu.com/question/918752740286781299.html");
		baiduZhidao.run();

	}

}

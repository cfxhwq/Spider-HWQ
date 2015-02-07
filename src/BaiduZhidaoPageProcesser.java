

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


public class BaiduZhidaoPageProcesser implements PageProcessor {

    @Override
    public void process(Page page) {
//        List<String> strings = page.getHtml().regex("<a[^<>]*href=[\"']{1}(/post-free.*?\\.shtml)[\"']{1}").all();
//        page.addTargetRequests(strings);
//        page.putField("title", page.getHtml().xpath("//div[@id='post_head']//span[@class='s_title']//b"));
//        page.putField("body",page.getHtml().smartContent());
    	//System.out.println(page.toString());
    	
    }
    
    private  double  getSharkSpark(Page page){
    	double weight = 0.5;
    	double sum = weight*getInherited() + (1-weight) * getNeighborhood();
    	return sum;
    }
    
    private  double  getInherited(){
    	
    	return 0;
    }
    
    private  double  getNeighborhood(){
    	return 0;
    }
    
    private double getPR(){//PageRank
    	return 0;
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("http://zhidao.baidu.com/question/");
        		//.addStartUrl("http://bbs.tianya.cn/");  //To change body of implemented methods use File | Settings | File Templates.
    }
}

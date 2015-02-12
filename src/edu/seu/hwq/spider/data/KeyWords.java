package edu.seu.hwq.spider.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KeyWords {

	public static HashMap<String, String> keywords = new HashMap<String, String>();

	public static HashMap<String, Double> topic;
	static{
		keywords.put("android", null);
		{
			keywords.put("安卓", "android");
			keywords.put("broadcastreceiver", "android");
			keywords.put("广播", "android");
			keywords.put("activity", "android");
			{
				keywords.put("oncreate", "activity");
				keywords.put("onstart", "activity");
				keywords.put("onresume", "activity");
				keywords.put("onpause", "activity");
				keywords.put("onstop", "activity");
				keywords.put("ondestroy", "activity");
				keywords.put("onrestart", "activity");
				keywords.put("onsaveinstancestate", "activity");
				keywords.put("putextra", "activity");
				keywords.put("intent", "activity");
				keywords.put("bundle", "activity");
				keywords.put("startactivityfromresult", "activity");
				keywords.put("startactivityfrom", "activity");
				keywords.put("setcontentview", "activity");

			}
			keywords.put("布局", "android");
			keywords.put("layout", "android");
			{
				keywords.put("linearlayout", "layout");
				keywords.put("线性布局", "layout");
				keywords.put("tablelayout", "layout");
				keywords.put("relativelayout", "layout");
				keywords.put("相对布局", "layout");
				keywords.put("framelayout", "layout");
				keywords.put("帧布局", "layout");
				keywords.put("absolutelayout", "layout");
				keywords.put("fragment", "layout");

				keywords.put("paddingleft", "layout");
				keywords.put("paddingright", "layout");
				keywords.put("paddingtop", "layout");
				keywords.put("paddingbottom", "layout");
				keywords.put("padding", "layout");
				keywords.put("textview", "layout");
				keywords.put("文本框", "layout");
				{
					keywords.put("width", "textview");
					keywords.put("height", "textview");
					keywords.put("gravity", "textview");
					keywords.put("margin", "textview");
				}
				keywords.put("button", "layout");
				keywords.put("checkbox", "layout");
				keywords.put("复选框", "layout");
				keywords.put("button", "layout");
				{
					keywords.put("radiobutton", "button");
					keywords.put("单选框", "button");
					keywords.put("togglebutton", "button");
					keywords.put("setonclicklistener", "button");
				}
				keywords.put("spinner", "layout");
				keywords.put("radiogroup", "layout");
				keywords.put("ratingstar", "layout");
				keywords.put("videoview", "layout");
				keywords.put("imageview", "layout");
				keywords.put("mediacontroller", "layout");

				keywords.put("alignparentBottom", "layout");
				keywords.put("alignparenttop", "layout");
				keywords.put("alignparentleft", "layout");
				keywords.put("alignparentright", "layout");
				keywords.put("alignparentright", "layout");

			}

			keywords.put("service", "android");
			{
				keywords.put("onbind", "service");
				keywords.put("onunbind", "service");
				keywords.put("onrebind", "service");
				keywords.put("startservice", "service");
				keywords.put("stopservice", "service");
			}
			keywords.put("contentprovider", "android");
			keywords.put("开发", "android");
			keywords.put("develop", "android");
			{
				keywords.put("developer", "develop");
				keywords.put("test", "develop");
				keywords.put("程序", "develop");
				keywords.put("开发", "develop");
				keywords.put("测试", "develop");
				keywords.put("code", "develop");
				keywords.put("adt", "develop");
				keywords.put("gms", "develop");
				keywords.put("代码", "develop");
				keywords.put("mainfest", "develop");
				keywords.put("sharedpreference", "develop");
				keywords.put("asyncplayer", "develop");
				keywords.put("andrdroidmainfest", "develop");
				{
					keywords.put("permission", "andrdroidmainfest");
					keywords.put("权限", "andrdroidmainfest");
				}
				keywords.put("tabhost", "develop");
				keywords.put("slidingdrawer", "develop");
				keywords.put("适配器", "develop");
				keywords.put("adapter", "develop");
				{
					keywords.put("baseadapter", "adapter");
					keywords.put("cursoradapter", "adapter");
					keywords.put("resourcecursoradapter", "adapter");
					keywords.put("simplecursoradapter", "adapter");
				}
				keywords.put("scroller", "develop");
				{
					keywords.put("scrollview", "scroller");
				}
				keywords.put("gallery", "developer");
				keywords.put("handler", "developer");
				{
					keywords.put("handleMessage", "handler");
				}
				keywords.put("asynctask", "developer");
				{
					keywords.put("onprogressupdate", "asynctask");
					keywords.put("onpreexecute", "asynctask");
					keywords.put("oncancelled", "asynctask");
					keywords.put("doinbackground", "asynctask");
					keywords.put("onpostexecute", "asynctask");
				}
				keywords.put("listview", "developer");
				keywords.put("gridview", "developer");
				keywords.put("edittext", "developer");
				keywords.put("xml", "developer");
				keywords.put("eclipse", "developer");
				keywords.put("sdk", "developer");
				keywords.put("java", "developer");
				{
					keywords.put("thread", "java");
					keywords.put("线程", "java");
				}
				keywords.put("jni", "developer");
				{
					keywords.put("opencv", "developer");
					keywords.put("ffmpeg", "developer");
				}
			}

		}
	}

	public static void init() {
		if (topic != null) {
			return;
		}
		topic = new HashMap<String, Double>();
		topic.put("android", 1.0);
		for (Iterator<?> iterator = keywords.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iterator.next();
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();
			int depth;

			if (!topic.containsKey(key)) {
				depth = getDepth(key);
				topic.put(key, Math.pow(0.9, depth));
				//System.out.println(key+"\t"+topic.get(key));
				key = value;
				while (!topic.containsKey(key)){
					topic.put(key, Math.pow(0.9,-- depth));
					//System.out.println(key+"\t"+topic.get(key));
					key = (String) keywords.get(key);
					//if(!keywords.containsKey(key)){System.out.println(key);}//test whether keywords exit error
				} 	
			}
		}
	}

	public static int getDepth(String str) {
		if (!keywords.containsKey(str) || str == null)
			return -1;// error
		int depth = 0;
		while (str != null) {
			str = keywords.get(str);
			depth++;
		}
		return depth;
	}
	public static void main(String[] args) {
		System.out.println(keywords.size());
		//System.out.println(KeyWords.getDepth("android"));
		KeyWords.init();
		System.out.println(topic.size());
	}
}

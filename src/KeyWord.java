import java.util.HashMap;

public class KeyWord {

	public static HashMap<String, String> keywords = new HashMap<String, String>();

	{
		keywords.put("android", null);
		{
			keywords.put("broadcastreceiver", "android");
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
			keywords.put("layout", "android");
			{
				keywords.put("linearlayout", "layout");
				keywords.put("tablelayout", "layout");
				keywords.put("relativelayout", "layout");
				keywords.put("framelayout", "layout");
				keywords.put("absolutelayout", "layout");
				keywords.put("fragment", "layout");

				keywords.put("paddingleft", "layout");
				keywords.put("paddingright", "layout");
				keywords.put("paddingtop", "layout");
				keywords.put("paddingbottom", "layout");
				keywords.put("padding", "layout");
				keywords.put("textview", "layout");
				{
					keywords.put("width", "textview");
					keywords.put("height", "textview");
					keywords.put("gravity", "textview");
					keywords.put("margin", "textview");
				}
				keywords.put("button", "layout");
				keywords.put("checkbox", "layout");
				keywords.put("button", "layout");
				{
					keywords.put("radiobutton", "button");
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
			keywords.put("develop", "android");
			{
				keywords.put("mainfest", "developer");
				keywords.put("sharedpreference", "developer");
				keywords.put("asyncplayer", "developer");
				keywords.put("andrdroidmainfest", "developer");
				{
					keywords.put("permission", "andrdroidmainfest");
				}
				keywords.put("tabhost", "developer");
				keywords.put("slidingdrawer", "developer");
				keywords.put("adapter", "developer");
				{
					keywords.put("baseadapter", "adapter");
					keywords.put("cursoradapter", "adapter");
					keywords.put("resourcecursoradapter", "adapter");
					keywords.put("simplecursoradapter", "adapter");
				}
				keywords.put("scroller", "developer");
				{
					keywords.put("scrollview", "scroller");
				}
				keywords.put("gallery", "developer");
				keywords.put("asynctask", "developer");
				keywords.put("asynctask", "developer");
				keywords.put("asynctask", "developer");
				keywords.put("listview", "developer");
				keywords.put("gridview", "developer");
				keywords.put("edittext", "developer");
				keywords.put("asynctask", "developer");
				keywords.put("xml", "developer");
				keywords.put("eclipse", "developer");
				keywords.put("sdk", "developer");
				keywords.put("java", "developer");
				keywords.put("jni", "developer");
				{
					keywords.put("opencv", "developer");
					keywords.put("ffmpeg", "developer");
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
}

package edu.seu.hwq.spider.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import edu.seu.hwq.spider.data.KeyWords;

public class VSM {
	static {
		System.loadLibrary("Keyword");
		KeyWords.init();
	}

	public static double getSim(String content) {
		double total, numerator = 0, denominator = 0;
		double d_keyword = 0, d_topic = 0;
		HashMap<String, Double> keyword_value = VSM.getKeywords(content);
		for (Iterator<?> iterator = keyword_value.entrySet().iterator(); iterator
				.hasNext();) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) iterator.next();
			String key = ((String) entry.getKey()).toLowerCase();
			if (KeyWords.topic.containsKey(key)) {
				numerator += (Double) KeyWords.topic.get(key)
						* (Double) entry.getValue();
				d_topic += Math.pow((Double) KeyWords.topic.get(key), 2);
				System.out.print(key + " ");
			}
			d_keyword += Math.pow((Double) entry.getValue(), 2);
		}
		denominator = Math.sqrt(d_keyword * d_topic);
		if (denominator == 0)
			return 0;//denominator can't be 0
		total = numerator / denominator;
		System.out.println("\nSim >>>>\t" + total + " = " + numerator + " / "
				+ denominator);
		return total;
	}

	public static HashMap<String, Double> getKeywords(String content) {
		String result = Keyword.getKeywords(content);
		String[] keywords = result.split(",");

		HashMap<String, Double> keyword_value = new HashMap<String, Double>();
		for (int i = 0; i < keywords.length; i++) {
			String key = keywords[i].substring(0, keywords[i].indexOf('|'))
					.toLowerCase();
			if (keyword_value.containsKey(key)) {
				keyword_value
						.put(key,
								(Double.valueOf(keywords[i]
										.substring(keywords[i].indexOf('|') + 1)) + keyword_value
										.get(key)) / 2);
			} else {
				keyword_value.put(
						keywords[i].substring(0, keywords[i].indexOf('|'))
								.toLowerCase(), Double.valueOf(keywords[i]
								.substring(keywords[i].indexOf('|') + 1)));
			}
		}
		System.out.println(keyword_value);
		return keyword_value;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// PythonInterpreter interpreter = new PythonInterpreter();
		// PySystemState sys = Py.getSystemState();
		// sys.path.add("/home/mark");
		// interpreter.exec("import jiaba");
		// interpreter.exec("seg_list = jieba.cut_for_search(\"小明硕士毕业于中国科学院计算所，后在日本京都大学深造\") #搜索引擎模式 ");
		// interpreter.exec("print \", \".join(seg_list)");

		// interpreter.execfile("/home/mark/getKeywords.py");
		// PyFunction func = (PyFunction) interpreter.get("adder",
		// PyFunction.class);
		//
		// int a = 2010, b = 2;
		// PyObject pyobj = func.__call__();
		// System.out.println("anwser = " + pyobj.toString());

		// interpreter.execfile("/home/mark/test.py");

		// Process proc;
		// try {
		// proc = Runtime.getRuntime().exec("python test.py");
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// proc.getInputStream()));
		// String line;
		// while ((line = in.readLine()) != null) {
		// System.out.println(line);
		// }
		// in.close();
		// proc.waitFor();
		// System.out.println("end");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// System.out.println( System.getProperty("java.library.path"));
		// System.out.println(Keyword
		// .getKeywords("我是拖拉机学院手扶拖拉机专业的。不用多久，我就会升职加薪，当上CTO，走上人生巅峰。"));
		// System.out.println( System.getProperty("java.library.path"));

		// VSM.getSim("在Handler 异步实现时,涉及到develop, Handler, Looper, Message,Thread四个对象，实现异步的流程是主线程启动Thread（子线程）àthread(子线程)运行并生成Message-àLooper获取Message并传递给HandleràHandler逐个获取Looper中的Message，并进行UI变更。");
		/*
		 * shell get *.so g++ -I/usr/lib/jvm/java-7-sun/include/
		 * -I/usr/lib/jvm/java-7-sun/include/linux/ -fPIC -shared -o
		 * ../libKeyword.so jni_Keyword.cpp
		 */
		VSM.getSim("xxxxxxxxxxxxxxx  xxxx");
	}

}

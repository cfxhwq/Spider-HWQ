package edu.seu.hwq.spider.util;




public class VSM {

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

//		Process proc;
//		try {
//			proc = Runtime.getRuntime().exec("python test.py");
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//				     proc.getInputStream()));
//				   String line;
//				   while ((line = in.readLine()) != null) {
//				    System.out.println(line);
//				   }
//				   in.close();
//				   proc.waitFor();
//				   System.out.println("end");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//System.out.println( System.getProperty("java.library.path"));
		System.loadLibrary("Keyword");
		System.out.println(Keyword.getKeywords("我是拖拉机学院手扶拖拉机专业的。不用多久，我就会升职加薪，当上CTO，走上人生巅峰。"));
//		System.out.println( System.getProperty("java.library.path"));
	}

}

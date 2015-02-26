package edu.seu.hwq.spider.data;

import java.util.HashMap;
import java.util.HashSet;

public class PageRank {
	public static HashMap<Long, HashSet<Long>> inherited = new HashMap<Long, HashSet<Long>>();
	public static HashMap<Long, Double> kv = new HashMap<Long, Double>();

	public static void add(long son, long father) {
		HashSet<Long> f;
		if (inherited.containsKey(son)) {
			f = ((HashSet<Long>) (inherited.get(son)));
			f.add(father);
		} else {
			f = new HashSet<Long>();
			f.add(father);
			inherited.put(son, f);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

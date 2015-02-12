#include <jni.h>
#include "edu_seu_hwq_spider_util_Keyword.h"
#include <stdio.h>
#include "/home/mark/cppjieba/src/KeywordExtractor.hpp"
using namespace CppJieba;

JNIEXPORT jstring JNICALL Java_edu_seu_hwq_spider_util_Keyword_getKeywordsbyJieba
  (JNIEnv *env, jclass jc, jstring input){
	KeywordExtractor extractor("/home/mark/cppjieba/dict/jieba.dict.utf8", "/home/mark/cppjieba/dict/hmm_model.utf8", "/home/mark/cppjieba/dict/idf.utf8", "/home/mark/cppjieba/dict/stop_words.utf8");
	//KeywordExtractor extractor("../dict/jieba.dict.utf8", "../dict/hmm_model.utf8", "../dict/idf.utf8", "../dict/stop_words.utf8", "../dict/user.dict.utf8");
	//string s("我是拖拉机学院手扶拖拉机专业的。不用多久，我就会升职加薪，当上CEO，走上人生巅峰。");
	const char *tmp = env->GetStringUTFChars(input, 0);
	string s(tmp);
	vector<pair<string, double> > wordweights;
	vector<string> words;
	size_t topN = 20;
	extractor.extract(s, wordweights, topN);
	//cout<< s << '\n' << wordweights << endl;
	//extractor.extract(s, words, topN);
	//cout<< s << '\n' << words << endl;
	string str;
	for(int i=0;i < wordweights.size();i++){
		char c[20];
		sprintf(c,"%f",wordweights[i].second); 

		str += wordweights[i].first+"|"+c+",";
	}
	str.erase(str.size()-1);
	jstring output = env->NewStringUTF(str.c_str());
	return output;
}


package com.example.management.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;

import java.util.List;

public class JiebaUtils {
    private static JiebaSegmenter segmenter = new JiebaSegmenter();

    public static List<String> getSignaleWord(String words){
        List<String> resultList = segmenter.sentenceProcess(words);
        return resultList;
    }
}

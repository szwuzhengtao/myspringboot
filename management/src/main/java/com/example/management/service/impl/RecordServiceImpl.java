package com.example.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.management.mapper.*;
import com.example.management.pojo.po.Blockingwords;
import com.example.management.pojo.po.Member;
import com.example.management.pojo.ro.RecordIds;
import com.example.management.pojo.po.Record;
import com.example.management.pojo.vo.RecordDetails;
import com.example.management.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.management.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired(required = false)
    private RecordMapper recordMapper;

    @Autowired(required = false)
    private RecordblockMapper recordblockMapper;

    @Autowired(required = false)
    private BlockMapper blockMapper;

    @Autowired(required = false)
    private BlockingwordsMapper blockingwordsMapper;

    @Autowired(required = false)
    private MemberMapper memberMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public CommonResult selectAll() {
        List<Record> records = recordMapper.selectList(null);
        List<RecordDetails> recordDetails = new ArrayList<>();
        MyConverter converter = new MyConverter();
        for(Record record : records){
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("memberQQ",record.getPersonId());
            if(memberMapper.selectCount(wrapper1) > 0){
                Member member = memberMapper.selectOne(wrapper1);
                RecordDetails recordDetail = converter.recordPlusMember(record,member);
                recordDetails.add(recordDetail);
            }
        }
//        MyConverter converter = new MyConverter();
//        List<RecordDetails> recordDetails = converter.recordCollection(list);
        return CommonResult.success(recordDetails);
    }

    @Override
    public CommonResult selectById(String customerId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("personId",customerId);
        List<Record> list = recordMapper.selectList(wrapper);
        List<RecordDetails> recordDetails = new ArrayList<>();
        MyConverter converter = new MyConverter();
        for(Record record : list){
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("memberQQ",record.getPersonId());
            if(memberMapper.selectCount(wrapper1) > 0){
                Member member = memberMapper.selectOne(wrapper1);
                RecordDetails recordDetail = converter.recordPlusMember(record,member);
                recordDetails.add(recordDetail);
            }
        }
//        MyConverter converter = new MyConverter();
//        List<RecordDetails> recordDetails = converter.recordCollection(list);
        return CommonResult.success(recordDetails);
    }

    @Override
    public CommonResult selectByChatId(int chatId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chatId",chatId);
        List<Record> list = recordMapper.selectList(wrapper);
        List<RecordDetails> recordDetails = new ArrayList<>();
        MyConverter converter = new MyConverter();
        for(Record record : list){
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("memberQQ",record.getPersonId());
            if(memberMapper.selectCount(wrapper1) > 0){
                Member member = memberMapper.selectOne(wrapper1);
                RecordDetails recordDetail = converter.recordPlusMember(record,member);
                recordDetails.add(recordDetail);
            }
        }
//        MyConverter converter = new MyConverter();
//        List<RecordDetails> recordDetails = converter.recordCollection(list);
        return CommonResult.success(recordDetails);
    }

    @Override
    public CommonResult selectByKey(int chatId, String key) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("chatId",chatId);
        wrapper.like("content",key);
        List<Record> list = recordMapper.selectList(wrapper);
        List<RecordDetails> recordDetails = new ArrayList<>();
        MyConverter converter = new MyConverter();
        for(Record record : list){
            QueryWrapper wrapper1 = new QueryWrapper();
            wrapper1.eq("memberQQ",record.getPersonId());
            if(memberMapper.selectCount(wrapper1) > 0){
                Member member = memberMapper.selectOne(wrapper1);
                RecordDetails recordDetail = converter.recordPlusMember(record,member);
                recordDetails.add(recordDetail);
            }
        }
//        MyConverter converter = new MyConverter();
//        List<RecordDetails> recordDetails = converter.recordCollection(list);
        return CommonResult.success(recordDetails);
    }

    @Override
    public CommonResult recordBlock(RecordIds recordIds) {
        return CommonResult.success();
    }

    @Override
    public CommonResult keyWords(String personId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("personId",personId);
        List<Record> list = recordMapper.selectList(wrapper);
        List<Blockingwords> blockingwords = blockingwordsMapper.selectList(null);
        List<String> bwords = new ArrayList<>();
        for (Blockingwords blockingword : blockingwords){
            bwords.add(blockingword.getWord());
        }
        List<String> wordList = new ArrayList<>();
        for(Record record : list){
            List<String> words = JiebaUtils.getSignaleWord(record.getContent());
            wordList.addAll(words);
        }
        if(!wordList.isEmpty()){
            Map<String,Integer> map = new HashMap<>();
            wordList.removeAll(bwords);
            for(String word : wordList){
//                if(bwords.contains(word)){
//                    continue;
//                }
                Integer count = map.get(word);
                map.put(word,(count == null) ? 1 : count + 1);
            }
            PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>((a,b)->b.getValue()-a.getValue());
            for(Map.Entry<String,Integer> entry : map.entrySet()){
                pq.offer(entry);
            }
            return CommonResult.success(pq);
        }
        return CommonResult.success();
    }

    @Override
    public CommonResult emotionAnalyze(String personId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("personId",personId);
        List<Record> list = recordMapper.selectList(wrapper);
        HanLpClassifier.initClassifier(ClassifierConstant.DATASET_HOTEL_PATH, ClassifierConstant.HOTEL_MODEL_PATH);
        Map<String,Integer> map = new HashMap<>();
        for(Record record : list){
            String emotion = HanLpClassifier.getClassification(record.getContent());
            Integer count = map.get(emotion);
            map.put(emotion,(count == null) ? 1 : count + 1);
        }
        return CommonResult.success(map);
    }
}

package com.example.consumingrest;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ReplyDictInstance {
    private static HashMap<Integer, Reply> replyDict = new HashMap<>();
    private static final AtomicInteger atomicInt = new AtomicInteger();
    private static ReplyDictInstance instance;

    private ReplyDictInstance(){}

    public synchronized static ReplyDictInstance getInstance(){
        if(instance == null){
            instance = new ReplyDictInstance();
        }
        return instance;
    }

    public void addReply(Reply reply){
        replyDict.put(atomicInt.getAndIncrement(), reply);
    }

    public Reply getReply(int id){
        //return replyDict.get(id);
        return  replyDict.getOrDefault(id, null);
    }

    public String allReply(){
        String str = "";
        for(Integer id : replyDict.keySet()){
            str += replyDict.get(id).toString();
            str += "\n";
        }
        return str;
    }

}

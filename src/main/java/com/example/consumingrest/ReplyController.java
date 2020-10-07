package com.example.consumingrest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {

    ReplyDictInstance instance;

    public ReplyController() {
        this.instance = ReplyDictInstance.getInstance();
    }

    @GetMapping("/allReply")
    public ReplyObj getAllReply(){
        return new ReplyObj(instance.allReply());
    }

    @GetMapping("/reply")
    public ReplyObj getAReply(@RequestParam(value = "id", defaultValue = "1") String id){
        Reply reply = instance.getReply(Integer.parseInt(id));
        if (reply == null){
            return new ReplyObj("Can not find reply with given id");
        }
        else{
            return new ReplyObj(reply.toString());
        }
    }
}

package com.example.consumingrest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ReplyController {

    @Autowired
    JdbcComponent component;

    private ReplyObj buildReplyObj(List<Map<String, Object>> list){
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : list) {
            sb.append("type: ");
            sb.append(row.get("type"));
            sb.append("; ");
            sb.append("id: ");
            sb.append(row.get("id"));
            sb.append("; ");
            sb.append("quote: ");
            sb.append(row.get("quote"));
            sb.append("; ");
        }
        sb.append("\n");
        return new ReplyObj(sb.toString());
    }

    @GetMapping("/allReply")
    public ReplyObj getAllReply(){
        List<Map<String, Object>> list = component.getTemplate().queryForList("select * from reply");
        return buildReplyObj(list);
    }

    @GetMapping("/reply")
    public ReplyObj getAReply(@RequestParam(value = "id", defaultValue = "1") String id){
        List<Map<String, Object>> list = component.getTemplate().queryForList("select * from reply where id = ?", id);
        return buildReplyObj(list);
    }
}

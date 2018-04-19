package com.linyk3.myblog.aync;

import com.alibaba.fastjson.JSONObject;
import com.linyk3.myblog.service.JedisService;
import com.linyk3.myblog.util.RedisKeyUntil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {
    @Autowired
    private JedisService jedisService;

    public void fireEvent(EventModel eventModel){
        String json = JSONObject.toJSONString(eventModel);
        String key = RedisKeyUntil.getEventQueue();
        jedisService.lpush(key,json);
    }
}

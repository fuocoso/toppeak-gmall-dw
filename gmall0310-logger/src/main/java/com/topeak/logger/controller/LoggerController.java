package com.topeak.logger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.topeak.logger.service.KafkaReceiver;
import com.topeak.logger.service.KafkaSender;
import com.toppeak.gmall0310.common.constant.GmallConstans;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // = @Controller +  @ResponseBody
public class LoggerController {

    /**
     * 自动注入KafkaTemp
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoggerController.class);

    //@RequestMapping(value = "/log",method = RequestMethod.POST)
    @PostMapping("log")
    //@ResponseBody
    public String dolog(String logString) {
        //1.补充时间戳
        JSONObject jsonObject = JSON.parseObject(logString);
        jsonObject.put("ts", System.currentTimeMillis());

        //2.写日志
        String logJson = jsonObject.toJSONString();
        logger.info(logJson);

        //3.写kafka
        /**
         * bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic gmall_start --from-beginning
         * bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic gmall_event --from-beginning
         */
        if ("startup".equals(jsonObject.getString("type"))) {
            System.out.println("发送数据到：" + GmallConstans.KAFKA_TOPIC_START);
            kafkaTemplate.send(GmallConstans.KAFKA_TOPIC_START, logJson);
        } else {
            kafkaTemplate.send(GmallConstans.KAFKA_TOPIC_EVENT, logJson);
        }



        return "success";
    }
}

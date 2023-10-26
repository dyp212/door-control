package com.door.control.sdk.swagger;

import com.door.addin.util.UtilJson;
import com.door.addin.vo.TerminalStatus;
import com.door.control.sdk.procotol.response.*;
import com.door.control.sdk.service.AccessTerminalService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * MQ消息
 */
@Controller
public class DoorControlSdkMQController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private RestTemplate restTemplate = new RestTemplate();
    @Autowired
    AccessTerminalService accessTerminalService;
    /**
     * 用户信息上传
     * @param message
     */
    @JmsListener(destination = "terminal.user",containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void terminalUserQueue(String message) {
        if (message == null) {
            return;
        }
        TerminalUserResponse resp = UtilJson.readValue(message, TerminalUserResponse.class);
        resp.getSn();
        logger.info("destination={},message={}", "terminal.user", resp.getClass());
    }

    /**
     * 提取历史记录
     *
     * @param message
     */
    @JmsListener(destination = "history.user", containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void historyUserQueue(String message) {
        if (message == null) {
            return;
        }
        TerminalHistoryResponse resp = UtilJson.readValue(message, TerminalHistoryResponse.class);
        resp.getSn();
        logger.info("destination={},message={}", "history.user", resp.getClass());
    }

    /**
     * 终端实时上传
     *
     * @param message
     */
    @JmsListener(destination = "data.real", containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void dataRealQueue(String message) {
        if (message == null) {
            return;
        }
        DataRealTimeResponse resp =  UtilJson.readValue(message, DataRealTimeResponse.class);
        resp.getSn();
        logger.info("destination={},message={}", "data.real", resp.getClass());

    }

    /**
     * 动态终端实时上传
     *
     * @param message
     */
    @JmsListener(destination = "data.dynamic", containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void dynamicDataRealQueue(String message) {
        if (message == null) {
            return;
        }
        DynamicDataRealTimeResponse resp =  UtilJson.readValue(message, DynamicDataRealTimeResponse.class);

        this.checkCode(resp);

        logger.info("destination={},message={}", "data.dynamic", resp.getClass());
    }

    private void checkCode(DynamicDataRealTimeResponse resp){
        String url = "http://172.26.77.80:51000/pda/v1/gate/open";
        String sn = resp.getSn();
        Long channelNo = Long.valueOf(resp.getChannelVoList().get(0).getChannelNo());
        Integer time = 60;
        String code = resp.getDynamicData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("code", code);
        requestBody.put("deviceId", sn);
        HttpEntity httpEntity = new HttpEntity<>(requestBody, headers);

        Map<String, Object> result = restTemplate.postForObject(url, httpEntity, Map.class);
        logger.info("二维码校验结果={}", result);
        String content = "已开门，请通过...";
        if(result.get("code").equals(1)){
            accessTerminalService.remoteOpen(sn, channelNo, time);
        } else {
            Object msg = result.get("msg");
            content = msg == null?"请确认二维码还在有效期":String.valueOf(msg);
        }
        accessTerminalService.setDisplayContent(sn, 3, content);
    }

    /**
     * 终端实时上传
     *
     * @param message
     */
    @JmsListener(destination = "control.real", containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void controlRealQueue(String message) {
        if (message == null) {
            return;
        }
        ControlRealTimeResponse resp =  UtilJson.readValue(message, ControlRealTimeResponse.class);
        resp.getSn();
        logger.info("destination={},message={}", "control.real", resp.getClass());
    }

    /**
     * 心跳管理
     * @param message
     */
    @JmsListener(destination = "hearbeat.queue",containerFactory = "jmsListenerContainerQueue") // 监听指定消息主题
    public void hearbeatQueue(String message) {
        if(message == null){
            return ;
        }

        TerminalStatus resp= UtilJson.readValue(message,TerminalStatus.class);
        resp.getSn();
        logger.info("destination={},message={}", "hearbeat.queue", resp.getClass());
    }
}

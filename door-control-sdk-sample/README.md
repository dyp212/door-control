#  door-control-sdk-java

#### SDK简单说明
- java版本sdk采用的是springboot
- 所有的设置都是采用http接口
- 设备主动上报数据采用mq转发

#### 框架
- door-control-sdk-service //springboot 主函数入口
- door-control-sdk-sqagger //接口发布入口

#### 接口说明
##### DoorControlSdkHttpController（http接口）,以下为接口声明
- ResponseVo类说明
```
{
  "data": {//对应NomalData类
    "dataType": "读取终端时间命令（>GT）设备上传（精确到秒）",
    "dataValue": 1625151844
  },
  "msg": "操作成功",
  "code": "SUCCESS",
  "status": true //true数据获取成功，操作成功
}
或
{
  "data": {
    "dataType": "设置终端为本地存储模式[离线工作模式]（>LM）设备上传",
    "dataValue": true
  },
  "msg": "操作成功",
  "code": "SUCCESS",
  "status": true
}

```

- 用户类型说明
```
  1, "智能卡"
  2, "数字密码"
  3, "手机蓝牙"
  4, "二维码"
  5, "指纹用户"
  6, "人脸用户"
  7, "虹膜用户"
  17, "智能ID卡用户"
  18, "智能CPU卡用户"
  19, "身份证用户"
  20, "NFC手机用户"
  21, "AIFace+用户"
  225, "手机用户远程请求"
  226, "人流量值"
  176, "测温人脸"
  255, "动态数据"
```
- 1、获取终端时间
```
    @ApiOperation(value = "获取终端时间")
    @RequestMapping(value = "/getTimestamp", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo getTimestamp(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
```

- 2、获取终端用户,下发后终端持续上报数据

```
    @ApiOperation(value = "获取终端用户,下发后终端持续上报数据")
    @RequestMapping(value = "/getUser", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo getUser(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
```

- 3、获取终端用户历史数据,下发后终端持续上报数据
````
    @ApiOperation(value = "获取终端用户历史数据,下发后终端持续上报数据")
    @RequestMapping(value = "/getHistory", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo getHistory(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
````

- 4、设置终端时间
````
    @ApiOperation(value = "设置终端时间")
    @RequestMapping(value = "/setTime", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setTime(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "时间：秒", required = true) Integer time);
````

- 5、移除链接对象
````
    @ApiOperation(value = "移除链接对象")
    @RequestMapping(value = "/removedDeviceConnect", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo removedDeviceConnect(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
````

- 6、设置终端上传模式
````
    @ApiOperation(value = "设置终端上传模式")
    @RequestMapping(value = "/setUploadMode", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setUploadMode(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
````

- 7、设置终端上传模式为离线
````
    @ApiOperation(value = "设置终端上传模式为离线")
    @RequestMapping(value = "/setUploadModeOffline", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setUploadModeOffline(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
````

- 8、设置人员权限
````
    @ApiOperation(value = "设置人员权限")
    @RequestMapping(value = "/addAuth", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo addAuth(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "用户总数", required = true) Integer total,
            @RequestParam @ApiParam(value = "当前传输的编号", required = true) Integer curNum,
            @RequestParam @ApiParam(value = "用户类型，参考sdk说明文档", required = true) Integer userType,
            @RequestParam @ApiParam(value = "用户名称", required = true) String username,
            @RequestParam @ApiParam(value = "用户id，就是卡号，10位", required = true) Long userId,
            @RequestParam(required = false) @ApiParam(value = "如果是人脸用户，传输人脸图片的url") String faceImgUrl,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "开始时间：秒", required = true) Integer beginTime,
            @RequestParam @ApiParam(value = "结束时间：秒", required = true) Integer endTime);
````

- 9、删除人员权限
````
    @ApiOperation(value = "删除人员权限")
    @RequestMapping(value = "/delAuth", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo delAuth(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "需要删除的用户总数", required = true) Integer total,
            @RequestParam @ApiParam(value = "当前需要传输的用户编号-从1开始", required = true) Integer currNum,
            @RequestParam @ApiParam(value = "用户类型，参考API说明文档", required = true) Integer userType,
            @RequestParam @ApiParam(value = "用户id", required = true) Long userId);
````

- 10、设置终端动作执行时间
````
    @ApiOperation(value = "设置终端动作执行时间")
    @RequestMapping(value = "/setAction", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setAction(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "秒", required = true) Integer time);
````

- 11、远程开门
````
    @ApiOperation(value = "远程开门")
    @RequestMapping(value = "/remoteOpen", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo remoteOpen(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "秒", required = true) Integer time);
````

- 12、重启设备
````
    @ApiOperation(value = "重启设备")
    @RequestMapping(value = "/restartDevice", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo restartDevice(@RequestParam String sn);
````

- 13、重启设备
````
    @ApiOperation(value = "删除所有权限")
    @RequestMapping(value = "/delAllAuth", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo delAllAuth(@RequestParam @ApiParam(value = "设备SN", required = true) String sn);
````

- 14、删除所有历史记录
````
    @ApiOperation(value = "删除所有历史记录")
    @RequestMapping(value = "/delAllHistory", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo delAllHistory(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.delAllHistory(sn);
    }
````

- 15、手机远程开门
````
    @ApiOperation(value = "手机远程开门")
    @RequestMapping(value = "/phoneOpen", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo phoneOpen(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "用户类型，参见api说明文档", required = true) Integer userType,
            @RequestParam @ApiParam(value = "用户id", required = true) Long userId,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo);
````

- 16、设置报警
````
    @ApiOperation(value = "设置报警")
    @RequestMapping(value = "/setSetAlarm", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setSetAlarm(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "胁迫开门预警,1-开启，0-关闭", required = true) Integer fireControl,
            @RequestParam @ApiParam(value = "非法开门预警,1-开启，0-关闭", required = true) Integer doorLongTime,
            @RequestParam @ApiParam(value = "门长时间未关预警,1-开启，0-关闭", required = true) Integer illegal,
            @RequestParam @ApiParam(value = "消防报警（默认开启）,1-开启，0-关闭", required = true) Integer coercion);
````

- 17、设置音频
````
    @ApiOperation(value = "设置音频")
    @RequestMapping(value = "/setAudio", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setAudio(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "1--有权限用户提示内容 2--无权限用户提示内容 3--过期用户提示内容 4—用户无对应门权限", required = true) Integer mode,
            @RequestParam @ApiParam(value = "语音提示内容", required = true) String content);
````

- 18、设置刷卡数量
````
    @ApiOperation(value = "设置刷卡数量")
    @RequestMapping(value = "/setCardSum", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setCardSum(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "1-正常刷卡验证模式 2-限次刷卡验证模式", required = true) Integer mode,
            @RequestParam @ApiParam(value = "刷卡次数", required = true) Long cardSum,
            @RequestParam @ApiParam(value = "开始时间：秒", required = true) Integer beginTime,
            @RequestParam @ApiParam(value = "结束时间：秒", required = true) Integer endTime);
````

- 19、设置屏幕显示内容
````
    @ApiOperation(value = "设置屏幕显示内容")
    @RequestMapping(value = "/setDisplayContent", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setDisplayContent(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "1—第一行显示内容 2—第二行显示内容 3—第三行显示内容 。。。。依次类推", required = true) Integer rowNo,
            @RequestParam @ApiParam(value = "显示内容", required = true) String content);
````


- 20、设置指纹终端为进入指纹下发模式
````
    @ApiOperation(value = "设置指纹终端为进入指纹下发模式")
    @RequestMapping(value = "/setFinger", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setFinger(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "1- 表示退出指纹下发模式 2- 表示进入指纹下发模式", required = true) Integer mode) ;
````

- 20、设置多卡开门模式
````
    @ApiOperation(value = "设置多卡开门模式")
    @RequestMapping(value = "/setMoreCardOpen", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setMoreCardOpen(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "1-多卡开 门模式 2-首卡开 门模式", required = true) Integer mode,
            @RequestParam @ApiParam(value = "用户数量", required = true) Integer userSum,
            @RequestParam @ApiParam(value = "用户id集合", required = true) List<Long> userIds,
            @RequestParam @ApiParam(value = "开始时间 秒", required = true) Integer beginTime,
            @RequestParam @ApiParam(value = "结束时间 秒", required = true) Integer endTime);
````

- 21、设置超级密码
````
    @ApiOperation(value = "设置超级密码")
    @RequestMapping(value = "/setSuperPwd", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setSuperPwd(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "密码10位数字", required = true) Long superPwd,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "true：添加密码；false：删除密码", required = true) Boolean isAddPwd);
````

- 22、设置终端工作模式
````
    @ApiOperation(value = "设置终端工作模式")
    @RequestMapping(value = "/setWorkMode", method = {RequestMethod.POST})
    @ResponseBody
    @Description("设备号")
    ResponseVo setWorkMode(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门1的索引值-从1开始", required = true) Long channelNo1,
            @RequestParam @ApiParam(value = "门2的索引值-从1开始，不可与1相同 设置为多门互锁模式和反潜回模式，该字段才使用，其他模式时均无效", required = true) Long channelNo2,
            @RequestParam @ApiParam(value = "1、刷卡验证；2、常开模式；3、常闭模式；4、开关模式；5、双重验证模式；6、多门互锁模式；7、反潜回模式；8、限次刷卡验证模式", required = true) Integer mode,
            @RequestParam @ApiParam(value = "开始时间：秒", required = true) Integer beginTime,
            @RequestParam @ApiParam(value = "结束时间：秒", required = true) Integer endTime);
````



- DoorControlSdkMQController（MQ监听接口）

- 1、用户信息上传
```
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
        logger.info("destination={},message={}", "terminal.user", resp.getClass());
    }
```

- 2、提取历史记录
```
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
        logger.info("destination={},message={}", "history.user", resp.getClass());
    }
```

- 2、终端实时上传
```
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
        logger.info("destination={},message={}", "data.real", resp.getClass());

    }

    DataRealTimeResponse类：
	/**
	 * 	1, "智能卡"
	 * 	2, "数字密码"
	 * 	3, "手机蓝牙"
	 * 	4, "二维码"
	 * 	5, "指纹用户"
	 * 	6, "人脸用户"
	 * 	7, "虹膜用户"
	 * 	17, "智能ID卡用户"
	 * 	18, "智能CPU卡用户"
	 * 	19, "身份证用户"
	 * 	20, "NFC手机用户"
	 * 	21, "AIFace+用户"
	 * 	225, "手机用户远程请求"
	 * 	226,"人流量值"
	 * 	176,"测温人脸"
	 * 	208,"动态二维码原始数据"
	 * 	209,"动态加密卡原始数据"
	 * 	210,"动态身份证数据"
	 *  255,"动态数据"
	 */
	Integer userType;
	/**
	 * 用户id-卡号
	 */
	Long userId;
	/**
	 * 当前时间戳
	 */
	Integer currentTimestamp;
	/**
	 * 通道号
	 */
	byte[] channelNo;
```

- 3、动态终端实时上传
```
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
        logger.info("destination={},message={}", "data.dynamic", resp.getClass());
    }

    DynamicDataRealTimeResponse类：
    /**
	 * 	1, "智能卡"
	 * 	2, "数字密码"
	 * 	3, "手机蓝牙"
	 * 	4, "二维码"
	 * 	5, "指纹用户"
	 * 	6, "人脸用户"
	 * 	7, "虹膜用户"
	 * 	17, "智能ID卡用户"
	 * 	18, "智能CPU卡用户"
	 * 	19, "身份证用户"
	 * 	20, "NFC手机用户"
	 * 	21, "AIFace+用户"
	 * 	225, "手机用户远程请求"
	 * 	226,"人流量值"
	 * 	176,"测温人脸"
	 * 	208,"动态二维码原始数据"
	 * 	209,"动态加密卡原始数据"
	 * 	210,"动态身份证数据"
	 *  255,"动态数据"
	 */
    Integer openType;
    Integer dynamicType;
    String dynamicData;
	/**
	 * 用户id-卡号
	 */
	Long userId;
	/**
	 * 当前时间戳
	 */
	Integer currentTimestamp;
	/**
	 * 通道号
	 */
	byte[] channelNo;
    /**
     * ActionTypeEnum （ 01-表示权限ID（卡/密码）开门  ； 02-表示钥匙开门 03-表示反锁  04-表示出门按钮开门  05-表示按门铃）
     */
    Integer actionType;
    /**
     * AuthTypeEnum
     * （ FF-表示非法用户，
     * FE-表示权限时间错误，
     * FD-表示没有对应通道权限....
     * 01-表示有效用户 ）
     */
    Integer authType;
```

- 4、终端实时上传
```
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
        logger.info("destination={},message={}", "control.real", resp.getClass());
    }
    
    ControlRealTimeResponse类：
    /**
	 * 	1, "智能卡"
	 * 	2, "数字密码"
	 * 	3, "手机蓝牙"
	 * 	4, "二维码"
	 * 	5, "指纹用户"
	 * 	6, "人脸用户"
	 * 	7, "虹膜用户"
	 * 	17, "智能ID卡用户"
	 * 	18, "智能CPU卡用户"
	 * 	19, "身份证用户"
	 * 	20, "NFC手机用户"
	 * 	21, "AIFace+用户"
	 * 	225, "手机用户远程请求"
	 * 	226,"人流量值"
	 * 	176,"测温人脸"
	 * 	208,"动态二维码原始数据"
	 * 	209,"动态加密卡原始数据"
	 * 	210,"动态身份证数据"
	 *  255,"动态数据"
	 */
	Integer openType;
	Long userId;
	Integer currentTimestamp;
	byte[] channelNo;
	/**
	 *   ActionTypeEnum （ 01-表示权限ID（卡/密码）开门  ； 02-表示钥匙开门 03-表示反锁  04-表示出门按钮开门  05-表示按门铃）
	 */
	Integer actionType;
	/**
	 * AuthTypeEnum
	 * （ FF-表示非法用户，
		FE-表示权限时间错误，
		FD-表示没有对应通道权限....
		01-表示有效用户 ）
	 */
	Integer authType;	
```

- 4、心跳管理
```
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
        logger.info("destination={},message={}", "hearbeat.queue", resp.getClass());
    }

    TerminalStatus类：
	Integer status;//1 在线 0 离线
	Long lastTime;//最后在线时间
	Integer commMode;
	String ip;
	Long address;
	Integer type;
	byte[] channel;//通道号
	String version;//硬件版本
	String rowVersion;//固件版本
```

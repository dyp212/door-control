/**
 * 北京钉图互动科技 all right reserver
 */
package com.door.control.sdk.swagger;


import com.door.control.sdk.procotol.response.*;
import com.door.control.sdk.service.AccessTerminalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

/**
 * @author 屠科钻
 * @since 2016年6月7日
 */
@Controller
@Api(hidden = true)
@Slf4j
public class DoorControlSdkHttpController {
    @Autowired
    AccessTerminalService accessTerminalService;

    /**
     * 获取终端时间
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "获取终端时间")
    @RequestMapping(value = "/getTimestamp", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseVo getTimestamp(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.getTimestamp(sn);
    }

    /**
     * 获取终端用户,下发后终端持续上报数据
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "获取终端用户,下发后终端持续上报数据")
    @RequestMapping(value = "/getUser", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo getUser(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.getUser(sn);
    }

    /**
     * 获取终端用户,下发后终端持续上报数据
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "获取终端用户,下发后终端持续上报数据")
    @RequestMapping(value = "/getHistory", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo getHistory(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.getHistory(sn);
    }

    /**
     * 设置终端时间
     *
     * @param sn
     * @param time
     * @return
     */
    @ApiOperation(value = "设置终端时间")
    @RequestMapping(value = "/setTime", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setTime(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "时间：秒", required = true) Integer time) {
        return accessTerminalService.setTime(sn, time);
    }


    /**
     * 移除链接对象
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "移除链接对象")
    @RequestMapping(value = "/removedDeviceConnect", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo removedDeviceConnect(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.removedDeviceConnect(sn);
    }

    /**
     * 设置终端上传模式
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "设置终端上传模式")
    @RequestMapping(value = "/setUploadMode", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setUploadMode(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.setUploadMode(sn);
    }

    /**
     * 设置终端上传模式为离线
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "设置终端上传模式为离线")
    @RequestMapping(value = "/setUploadModeOffline", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setUploadModeOffline(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.setUploadModeOffline(sn);
    }

    /**
     * 设置人员权限
     *
     * @param sn
     * @param total
     * @param curNum
     * @param userType
     * @param username
     * @param faceImgUrl
     * @param channelNo
     * @param beginTime
     * @param endTime
     * @return
     */
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
            @RequestParam @ApiParam(value = "结束时间：秒", required = true) Integer endTime) {
        if (userId > 4294967295L)
            return new ResponseVo("userId错误，请输入正确的用户Id，必须小于4294967295");
        return accessTerminalService.addAuth(sn, total, curNum, userType, username, userId, faceImgUrl, channelNo, beginTime, endTime);
    }

    /**
     * 删除人员权限
     *
     * @param sn
     * @param total
     * @param currNum
     * @param userType
     * @param userId
     * @return
     */
    @ApiOperation(value = "删除人员权限")
    @RequestMapping(value = "/delAuth", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo delAuth(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "需要删除的用户总数", required = true) Integer total,
            @RequestParam @ApiParam(value = "当前需要传输的用户编号-从1开始", required = true) Integer currNum,
            @RequestParam @ApiParam(value = "用户类型，参考API说明文档", required = true) Integer userType,
            @RequestParam @ApiParam(value = "用户id", required = true) Long userId) {
        if (userId >  4294967295L)
            return new ResponseVo("userId错误，请输入正确的用户Id，必须小于4294967295");
        return accessTerminalService.delAuth(sn, total, currNum, userType, userId);
    }

    /**
     * 设置终端动作执行时间
     *
     * @param sn
     * @param time
     * @return
     */
    @ApiOperation(value = "设置终端动作执行时间")
    @RequestMapping(value = "/setAction", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setAction(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "秒", required = true) Integer time) {
        return accessTerminalService.setAction(sn, time);
    }


    /**
     * 远程开门
     *
     * @param sn
     * @param channelNo
     * @param time
     * @return
     */
    @ApiOperation(value = "远程开门")
    @RequestMapping(value = "/remoteOpen", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo remoteOpen(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "秒", required = true) Integer time) {
        return accessTerminalService.remoteOpen(sn, channelNo, time);
    }

    /**
     * 重启设备
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "重启设备")
    @RequestMapping(value = "/restartDevice", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo restartDevice(@RequestParam String sn) {
        return accessTerminalService.restartDevice(sn);
    }


    /**
     * 删除所有权限
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "删除所有权限")
    @RequestMapping(value = "/delAllAuth", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo delAllAuth(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.delAllAuth(sn);
    }


    /**
     * 删除所有历史记录
     *
     * @param sn
     * @return
     */
    @ApiOperation(value = "删除所有历史记录")
    @RequestMapping(value = "/delAllHistory", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo delAllHistory(@RequestParam @ApiParam(value = "设备SN", required = true) String sn) {
        return accessTerminalService.delAllHistory(sn);
    }

    /**
     * 手机远程开门
     *
     * @param sn
     * @param userType
     * @param userId
     * @param channelNo
     * @return
     */
    @ApiOperation(value = "手机远程开门")
    @RequestMapping(value = "/phoneOpen", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo phoneOpen(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "用户类型，参见api说明文档", required = true) Integer userType,
            @RequestParam @ApiParam(value = "用户id", required = true) Long userId,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo) {
        if (userId > 4294967295L)
            return new ResponseVo("userId错误，请输入正确的用户Id，必须小于4294967295");
        return accessTerminalService.phoneOpen(sn, userType, userId, channelNo);
    }

    /**
     * 设置报警
     *
     * @param sn
     * @param channelNo
     * @param fireControl
     * @param doorLongTime
     * @param illegal
     * @param coercion
     * @return
     */
    @ApiOperation(value = "设置报警")
    @RequestMapping(value = "/setSetAlarm", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setSetAlarm(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "胁迫开门预警,1-开启，0-关闭", required = true) Integer fireControl,
            @RequestParam @ApiParam(value = "非法开门预警,1-开启，0-关闭", required = true) Integer doorLongTime,
            @RequestParam @ApiParam(value = "门长时间未关预警,1-开启，0-关闭", required = true) Integer illegal,
            @RequestParam @ApiParam(value = "消防报警（默认开启）,1-开启，0-关闭", required = true) Integer coercion) {
        return accessTerminalService.setSetAlarm(sn, channelNo, fireControl, doorLongTime, illegal, coercion);
    }

    /**
     * 设置音频
     *
     * @param sn
     * @param mode
     * @param content
     * @return
     */
    @ApiOperation(value = "设置音频")
    @RequestMapping(value = "/setAudio", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setAudio(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "1--有权限用户提示内容 2--无权限用户提示内容 3--过期用户提示内容 4—用户无对应门权限", required = true) Integer mode,
            @RequestParam @ApiParam(value = "语音提示内容", required = true) String content) {
        return accessTerminalService.setAudio(sn, mode, content);
    }


    /**
     * 设置刷卡数量
     *
     * @param sn
     * @param channelNo
     * @param mode
     * @param cardSum
     * @param beginTime
     * @param endTime
     * @return
     */
    @ApiOperation(value = "设置刷卡数量")
    @RequestMapping(value = "/setCardSum", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setCardSum(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "1-正常刷卡验证模式 2-限次刷卡验证模式", required = true) Integer mode,
            @RequestParam @ApiParam(value = "刷卡次数", required = true) Long cardSum,
            @RequestParam @ApiParam(value = "开始时间：秒", required = true) Integer beginTime,
            @RequestParam @ApiParam(value = "结束时间：秒", required = true) Integer endTime) {
        return accessTerminalService.setCardSum(sn, channelNo, mode, cardSum, beginTime, endTime);
    }

    /**
     * 设置屏幕显示内容
     *
     * @param sn
     * @param rowNo
     * @param content
     * @return
     */
    @ApiOperation(value = "设置屏幕显示内容")
    @RequestMapping(value = "/setDisplayContent", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setDisplayContent(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "1—第一行显示内容 2—第二行显示内容 3—第三行显示内容 。。。。依次类推", required = true) Integer rowNo,
            @RequestParam @ApiParam(value = "显示内容", required = true) String content) {
        return accessTerminalService.setDisplayContent(sn, rowNo, content);
    }

    /**
     * 设置满屏幕显示内容
     * @param sn
     * @param content1
     * @param content2
     * @param content3
     * @param content4
     * @param content5
     * @return
     */
    @ApiOperation(value = "设置满屏幕显示内容")
    @RequestMapping(value = "/setFullDisplayContent", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setDisplayContent(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "第一行显示内容", required = true) String content1,
            @RequestParam @ApiParam(value = "第二行显示内容", required = true) String content2,
            @RequestParam @ApiParam(value = "第三行显示内容", required = true) String content3,
            @RequestParam @ApiParam(value = "第四行显示内容", required = true) String content4,
            @RequestParam @ApiParam(value = "第五行显示内容", required = true) String content5) {
        return accessTerminalService.setFullDisplayContent(sn, content1, content2, content3, content4, content5);
    }


    /**
     * 设置指纹终端为进入指纹下发模式
     *
     * @param sn
     * @param mode
     * @return
     */
    @ApiOperation(value = "设置指纹终端为进入指纹下发模式")
    @RequestMapping(value = "/setFinger", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setFinger(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "1- 表示退出指纹下发模式 2- 表示进入指纹下发模式", required = true) Integer mode) {
        return accessTerminalService.setFinger(sn, mode);
    }

    /**
     * 设置多卡开门模式
     *
     * @param sn
     * @param channelNo
     * @param mode
     * @param userSum
     * @param userIds
     * @param beginTime
     * @param endTime
     * @return
     */
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
            @RequestParam @ApiParam(value = "结束时间 秒", required = true) Integer endTime) {
        return accessTerminalService.setMoreCardOpen(sn, channelNo, mode, userSum, userIds, beginTime, endTime);
    }

    /**
     * 设置超级密码
     *
     * @param sn
     * @param superPwd
     * @param channelNo
     * @param isAddPwd
     * @return
     */
    @ApiOperation(value = "设置超级密码")
    @RequestMapping(value = "/setSuperPwd", method = {RequestMethod.POST})
    @ResponseBody
    ResponseVo setSuperPwd(
            @RequestParam @ApiParam(value = "设备SN", required = true) String sn,
            @RequestParam @ApiParam(value = "密码10位数字", required = true) Long superPwd,
            @RequestParam @ApiParam(value = "门的编号-从1开始", required = true) Long channelNo,
            @RequestParam @ApiParam(value = "true：添加密码；false：删除密码", required = true) Boolean isAddPwd) {
        return accessTerminalService.setSuperPwd(sn, superPwd, channelNo, isAddPwd);
    }

    /**
     * 设置终端工作模式
     *
     * @param channelNo1 门的索引值
     * @param channelNo2 门的索引值 设置为多门互锁模式和反潜回模式，该字段才使用，其他模式时均无效。
     * @param mode       1、刷卡验证；2、常开模式；3、常闭模式；4、开关模式；5、双重验证模式；6、多门互锁模式；7、反潜回模式；8、限次刷卡验证模式
     * @param beginTime  开始时间：秒
     * @param endTime    结束时间：秒
     * @return
     */
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
            @RequestParam @ApiParam(value = "结束时间：秒", required = true) Integer endTime) {
        return accessTerminalService.setWorkMode(sn, channelNo1, channelNo2, mode, beginTime, endTime);
    }

    /**
     * 获取二维码
     *
     * @param card
     * @return
     */
    @ApiOperation(value = "获取二维码")
    @RequestMapping(value = "/getQrCode", method = {RequestMethod.POST})
    @ResponseBody
    @Description("卡号")
    ResponseVo getQrCode(
            @RequestParam @ApiParam(value = "卡号", required = true) Long card) {
        return accessTerminalService.getQrCode(card);
    }
}
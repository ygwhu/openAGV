package com.robot.mvc.core.interfaces;

import com.robot.mvc.core.exceptions.RobotException;

/**
 * 协议对象编码解码器匹配器接口
 * <p>
 * Created by laotang on 2020/1/12.
 */
public interface IProtocolMatcher {

    /**
     * 编码，将字符串转换为协议对象
     *
     * @param message 协议字符串
     * @return 协议对象
     * @throws RobotException
     */
    IProtocol encode(String message) throws RobotException;

    /**
     * 解码，将协议对象转换为字符串
     *
     * @param protocol 协议对象
     * @return 协议字符串
     * @throws RobotException
     */
    String decode(IProtocol protocol) throws RobotException;

    /**
     * 根据协议对象取当前点
     *
     * @param protocol 协议对象
     * @return 当前点位置
     * @throws RobotException
     */
    String getPoint(IProtocol protocol) throws RobotException;
}

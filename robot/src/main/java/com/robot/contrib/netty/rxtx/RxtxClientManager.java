package com.robot.contrib.netty.rxtx;


import com.robot.contrib.netty.ConnectionEventListener;
import com.robot.contrib.netty.comm.TelegramDecoder;
import com.robot.contrib.netty.comm.TelegramEncoder;
import com.robot.contrib.netty.tcp.TcpClientChannelManager;
import com.robot.mvc.core.exceptions.RobotException;
import io.netty.channel.ChannelHandler;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Created by laotang on 2020/1/25.
 */
public class RxtxClientManager {

    private static ConnectionEventListener connectionEventListener;
    private static RxtxClientChannelManager clientChannelManager;

    public RxtxClientManager(ConnectionEventListener eventListener) {
        connectionEventListener = eventListener;
        clientChannelManager = new RxtxClientChannelManager(connectionEventListener,
                this::getChannelHandlers,
                10000,
                true);
    }

    /**
     * 解码及编码器
     */
    private List<ChannelHandler> getChannelHandlers() {
        return Arrays.asList(
                new TelegramDecoder(),
                new TelegramEncoder());
    }

    public void initialize() {
        clientChannelManager.initialize();
    }

    public boolean isInitialized() {
        return clientChannelManager.isInitialized();
    }

    public void terminate() {
        clientChannelManager.terminate();
    }

    public void connect(String host, int port) throws RobotException {
        clientChannelManager.connect(host, port);
    }

    public void disconnect() {
        clientChannelManager.disconnect();
    }

    public boolean isConnected() {
        return clientChannelManager.isConnected();
    }

    public void setLoggingEnabled(boolean enable) {
        clientChannelManager.setLoggingEnabled(enable);
    }

    public void scheduleConnect(@Nonnull String host, int port, long delay) {
        clientChannelManager.scheduleConnect(host, port, delay);
    }

    public void send(String telegram) {
        try {
            clientChannelManager.send(telegram);
        } catch (Exception e) {
            throw new RobotException(e.getMessage(), e);
        }
    }
}

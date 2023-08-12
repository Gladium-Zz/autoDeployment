package com.gladium.autodeployment.common.socket.server;

import com.gladium.autodeployment.common.protobuf.Main.MainPb;
import com.google.protobuf.InvalidProtocolBufferException;
import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

import java.nio.ByteBuffer;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 17:35
 * @description 服务端数据编码
 */
public class AutoDeployProtocol implements Protocol<MainPb> {


    @Override
    public MainPb decode(ByteBuffer readBuffer, AioSession aioSession) {
        int remaining = readBuffer.remaining();
        if (remaining < Integer.BYTES) {
            return null;
        }
        readBuffer.mark();
        int length = readBuffer.getInt();
        if (length > readBuffer.remaining()) {
            readBuffer.reset();
            return null;
        }
        byte[] b = new byte[length];
        readBuffer.get(b);
        readBuffer.mark();
        MainPb mainPb = null;
        try {
            mainPb = MainPb.parseFrom(b);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return mainPb;
    }
}

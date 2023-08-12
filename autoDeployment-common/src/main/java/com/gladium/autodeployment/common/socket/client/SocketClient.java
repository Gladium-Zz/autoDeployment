package com.gladium.autodeployment.common.socket.client;

import com.gladium.autodeployment.common.protobuf.Main;
import com.gladium.autodeployment.common.socket.server.AutoDeployProtocol;
import lombok.Getter;
import org.smartboot.socket.transport.AioQuickClient;
import org.smartboot.socket.transport.AioSession;
import org.smartboot.socket.transport.WriteBuffer;

import java.io.IOException;
import java.util.Optional;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 18:35
 * @description TODO
 */
@Getter
public class SocketClient {


    private final String host;

    private final int port;

    private final AioQuickClient client;

    public SocketClient(String host,int port){
        this.host = host;
        this.port = port;
        Optional.ofNullable(host).filter(this::validHost).orElseThrow(() -> new RuntimeException("host["+host+"] address is error,Please check host"));
        client = new AioQuickClient(host, port, new AutoDeployProtocol(), (session, msg) ->{
            System.out.println("session attr："+session.getAttachment());
        });
    }


    public void sendMessage(Main.MainPb mainPb){
        AioSession session = null;
        try {
            session = client.start();
            session.setAttachment("客户端port:"+port);
            WriteBuffer writeBuffer = session.writeBuffer();
            byte[] data = mainPb.toByteArray();
            writeBuffer.writeInt(data.length);
            writeBuffer.write(data);
            writeBuffer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stop(){
        Optional.ofNullable(client).ifPresent(AioQuickClient::shutdown);
    }




    private boolean validHost(String host){
        if("localhost".equals(host)){
            return true;
        }

        return false;
    }


}

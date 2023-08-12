package com.gladium.autodeployment.common.socket;

import com.gladium.autodeployment.common.protobuf.Main;
import com.gladium.autodeployment.common.socket.client.SocketClient;
import com.gladium.autodeployment.common.socket.server.SocketServer;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 18:52
 * @description TODO
 */
public class TestSocket {


    public static void main(String[] args) {

        /*SocketServer server = new SocketServer();
        server.startServer(9999);*/

        SocketClient client = new SocketClient("localhost",9527);
        Main.MainPb mainPb= Main.MainPb.newBuilder()
                .setIp("127.0.0.1")
                .setPort(1234)
                .setMessageType(Main.MainPb.MessageType.HEART)
                .build();
        client.sendMessage(mainPb);


        SocketClient client1 = new SocketClient("localhost",9527);
        Main.MainPb mainPb1= Main.MainPb.newBuilder()
                .setIp("127.0.0.2")
                .setPort(2345)
                .setMessageType(Main.MainPb.MessageType.HEART).build();
        client1.sendMessage(mainPb1);

    }


}

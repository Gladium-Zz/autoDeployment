package com.gladium.autodeployment.common.socket.server;

import com.gladium.autodeployment.common.protobuf.Main;
import org.smartboot.socket.transport.AioQuickServer;
import org.smartboot.socket.transport.WriteBuffer;

import java.io.IOException;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 17:26
 * @description socket服务端，依赖于 smart-socket @link:<a href="https://gitee.com/smartboot/smart-socket/">...</a>
 */
public class SocketServer {

    private static AioQuickServer server;


    /**
     * @Author gladium
     * @Description 启动服务端
     * @Date 17:43 2023/8/12
     * @Param [port]
     * @return void
     **/

    public void startServer(int port){
        if(server == null){
            server = new AioQuickServer(port,new AutoDeployProtocol(),(session, msg)->{
                System.out.println("receive msg: session:"+session.getAttachment()+" ; msg:"+msg);
            });
        }
        try {
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("socket server start : "+port);
    }

    /**
     * @Author gladium
     * @Description 停止服务端
     * @Date 17:43 2023/8/12
     * @Param []
     * @return void
     **/

    public void stopServer(){
        if(server == null){
            System.out.println(" SocketServer is not running");
            return;
        }
        server.shutdown();
    }

    /**
     * @Author gladium
     * @Description 校验当前服务是否启动
     * @Date 17:43 2023/8/12
     * @Param []
     * @return boolean
     **/

    public boolean isRunning(){
        return server != null;
    }



}

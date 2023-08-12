package com.gladium.autoDeployment.admin;

import com.gladium.autodeployment.common.socket.server.SocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 22:02
 * @description TODO
 */
@SpringBootApplication
public class AutoDeploymentAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoDeploymentAdminApplication.class,args);
    }

    @PostConstruct
    public void init(){
        SocketServer server = new SocketServer();
        server.startServer(9527);
    }

}

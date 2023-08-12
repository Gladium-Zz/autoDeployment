package com.gladium.autodeployment.common.socket.process;

import com.gladium.autodeployment.common.protobuf.Main;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 22:18
 * @description
 */
public interface MessageEncodeProcess {


    Main.MainPb encode(String clientName, Main.MainPb mainPb);

}

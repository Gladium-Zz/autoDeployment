package com.gladium.autodeployment.common.socket.process;

import com.gladium.autodeployment.common.protobuf.Main;

/**
 * @author gladium
 * @version v1.0
 * @date 2023/8/12 22:21
 * @description TODO
 */
public interface MessageDecodeProcess {

    Main.MainPb decode(String clientName, Main.MainPb mainPb);

}

package com.wms.admin.util;

import javax.smartcardio.*;

public class APDUUtil {

    public static ResponseAPDU exec(CommandAPDU command) {
        ResponseAPDU responseAPDU = null;
        try {
            TerminalFactory factory = TerminalFactory.getDefault();// 得到一个默认的读卡器工场
            CardTerminal cardTerminal = factory.terminals().getTerminal("ACS ACR122 0");// 从工厂获得插在电脑上的读卡器列表,get读卡器列表
            cardTerminal.waitForCardPresent(0L);// 等待放置卡片
            Card card = cardTerminal.connect("*");// 连接卡片
            CardChannel cardChannel = card.getBasicChannel();// 打开通道
            responseAPDU = cardChannel.transmit(command);
        } catch (CardException e) {
            e.printStackTrace();
        }
        return responseAPDU;
    }
}

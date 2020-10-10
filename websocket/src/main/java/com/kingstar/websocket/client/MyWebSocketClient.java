package com.kingstar.websocket.client;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author xiayc
 * @date 2020/10/9 14:57
 */
@Slf4j
public class MyWebSocketClient extends WebSocketClient {
    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    public MyWebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
    }

    public MyWebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders, int connectTimeout) {
        super(serverUri, protocolDraft, httpHeaders, connectTimeout);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        log.info("websocket on open ");
    }

    @Override
    public void onMessage(String s) {
        log.info("websocket on message ");
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("websocket on close ");
    }

    @Override
    public void onError(Exception e) {
        log.info("websocket on error ");
    }


    /**
     * 启动服务端
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        //new MyWebSocketClient("ws://localhost:9090/client").start();
    }
}

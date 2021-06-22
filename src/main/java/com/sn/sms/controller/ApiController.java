package com.sn.sms.controller;

import com.google.gson.Gson;
import com.sn.sms.config.websocket.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import static com.sn.sms.config.websocket.SocketHandler.*;

@RestController
@RequestMapping("/info")
public class ApiController {


    @RequestMapping(value = "sessions", method = RequestMethod.GET)
    public @ResponseBody
    String getAllConnectedSessions() {

        List<Object> data = new ArrayList<>();
        for (WebSocketSession session : ACTIVE_SESSIONS.values()) {
            InetSocketAddress clientAddress = session.getRemoteAddress();
            data.add(new SessionData(clientAddress.getHostString() + ":" + clientAddress.getPort(), session.getId(), session.isOpen()));
        }


        for (String key : CLOSED_SESSIONS.keySet()) {
            data.add(new SessionMsg(key, CLOSED_SESSIONS.get(key), "close"));
        }

        for (String key : ERROR_SESSIONS.keySet()) {
            data.add(new SessionMsg(key, CLOSED_SESSIONS.get(key), "error"));
        }


        return new Gson().toJson(data);
    }

    private class SessionData {
        private String address;
        private String id;
        private boolean isOpen;

        public SessionData(String address, String id, boolean isOpen) {
            this.address = address;
            this.id = id;
            this.isOpen = isOpen;
        }
    }

    private class SessionMsg {
        private String id;
        private String msg;
        private String status;

        public SessionMsg(String id, String msg, String status) {
            this.id = id;
            this.msg = msg;
            this.status = status;
        }
    }

}

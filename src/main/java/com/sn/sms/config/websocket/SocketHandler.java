package com.sn.sms.config.websocket;

import com.sn.sms.model.bean.websocket.RequestMessage;
import com.sn.sms.model.bean.websocket.ResponseMessage;
import com.sn.sms.service.impl.WebSocketServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

	private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
	
	public static List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	public static Map<String, WebSocketSession> ACTIVE_SESSIONS = new HashMap<>();
	public static Map<String, String> CLOSED_SESSIONS = new HashMap<>();
	public static Map<String, String> ERROR_SESSIONS = new HashMap<>();

//	@Autowired
	private WebSocketServiceProvider provider;

	private ApplicationContext ctx;

	public SocketHandler(ApplicationContext ctx) {
		this.ctx = ctx;
		this.provider = ctx.getBean(WebSocketServiceProvider.class);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
//		Map<String, String> value = new Gson().fromJson(message.getPayload(), Map.class);
		try {
			logger.info("received message: {}", message.getPayload());
			RequestMessage reqMsg = RequestMessage.convert(message.getPayload());
			ResponseMessage resp = provider.processReq(reqMsg);
			logger.info("response : {} ", resp.toJson());
			session.sendMessage(new TextMessage(resp.toJson()));
		} catch (Exception e) {
			logger.info("internal server error occurred");
			e.printStackTrace();
		}
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//the messages will be broadcasted to all users.
		sessions.add(session);
		ACTIVE_SESSIONS.put(session.getId(), session);
		System.out.println("connection established " + session);
		InetSocketAddress clientAddress = session.getRemoteAddress();
		logger.info("Accepted connection from: {}:{}", clientAddress.getHostString(), clientAddress.getPort());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//		super.afterConnectionClosed(session, status);
		ACTIVE_SESSIONS.remove(session.getId());
		CLOSED_SESSIONS.put(session.getId(), session.getRemoteAddress().getHostString()+":"+session.getRemoteAddress().getPort());
		logger.info("Connection closed by {}:{}:"+ status, session.getRemoteAddress().getHostString(), session.getRemoteAddress().getPort());
		sessions.remove(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		logger.info("on error : {} ", exception.getLocalizedMessage());
		ERROR_SESSIONS.put(session.getId(),
				session.getRemoteAddress().getHostString() + ":" + session.getRemoteAddress().getPort() + " ( " + exception.getMessage() + " ) ");
		exception.printStackTrace();
		super.handleTransportError(session, exception);
	}

	public List<WebSocketSession> getSessions() {
		return sessions;
	}
}
package com.example.websocketdemo.configuration;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.JsonObject;

public class ConnectionHandler implements WebSocketHandler {

	private static ConcurrentHashMap<String, WebSocketSession> webSocketSessionCache = new ConcurrentHashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println(":::Hurray Connection Made:::  From ID: " + session.getId());
		webSocketSessionCache.put(session.getId(), session);
		sendMessage(session.getId(), createConnectionIdResponse(session.getId()));
	}

	private String createConnectionIdResponse(String id) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("connectionId", id);
		return jsonObject.toString();
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		try {
			System.out.println(message.getPayload().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println(":::This is Handle Transport Error:::");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.out.println(":::Aww Man Connection Closed:::  From ID: " + session.getId());
		webSocketSessionCache.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	public static void sendMessage(String message) {
		try {
			TextMessage textMessage = new TextMessage(message.getBytes());
			webSocketSessionCache.forEach((sessionId, session) -> {
				try {
					System.out.println(":::Sending message to client:::  Message: " + message + "to");
					System.out.println("ID: " +  sessionId);
					session.sendMessage(textMessage);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendMessage(String id, String message) {
		try {
			TextMessage textMessage = new TextMessage(message.getBytes());
			System.out.println(":::Sending message to client:::  Message: " + message + "to: " + id);
			webSocketSessionCache.get(id).sendMessage(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

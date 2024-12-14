package com.seetizen.backend.service;

import com.seetizen.backend.dto.EventMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {

    private final ConcurrentHashMap<String, SseEmitter> clients = new ConcurrentHashMap<>();

    // 새로운 SSE 연결 생성
    public SseEmitter createConnection(String clientId) {
        SseEmitter emitter = new SseEmitter(3600_000L); // 3600초 타임아웃
        clients.put(clientId, emitter);

        emitter.onCompletion(() -> clients.remove(clientId));
        emitter.onTimeout(() -> clients.remove(clientId));
        emitter.onError((e) -> clients.remove(clientId));

        // 연결 초기화 메시지
        try {
            emitter.send(SseEmitter.event().name("INIT").data("Connection established for client: " + clientId));
        } catch (IOException e) {
            clients.remove(clientId);
        }

        return emitter;
    }

    // 메시지 전송
    public void sendMessage(String clientId, EventMessage eventMessage) {
        SseEmitter emitter = clients.get(clientId);
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().name("MESSAGE").data(eventMessage));
            } catch (IOException e) {
                clients.remove(clientId); // 실패 시 제거
            }
        }
    }

    // 연결 제거
    public void removeConnection(String clientId) {
        clients.remove(clientId);
    }
}

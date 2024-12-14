package com.seetizen.backend.controller;

import com.seetizen.backend.dto.EventMessage;
import com.seetizen.backend.service.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@RestController
@RequestMapping("/sse")
public class SseController {

    @Autowired
    private SseService sseService;

    // SSE 연결 엔드포인트
    @GetMapping(value = "/connect/{clientId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(@PathVariable String clientId) {
        return sseService.createConnection(clientId);
    }

    // 메시지 전송 엔드포인트
    @PostMapping("/send")
    public Map<String, String> sendMessage(@RequestParam String clientId, @RequestParam String message, @RequestParam String url) {
        EventMessage eventMessage = new EventMessage(message, url);
        sseService.sendMessage(clientId, eventMessage);
        return Map.of("status", "Message sent to client: " + clientId);
    }

    // 연결 해제
    @DeleteMapping("/disconnect/{clientId}")
    public Map<String, String> disconnect(@PathVariable String clientId) {
        sseService.removeConnection(clientId);
        return Map.of("status", "Disconnected client: " + clientId);
    }
}

package apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public Mono<ResponseEntity<String>> fallback(ServerWebExchange exchange) {
        System.out.println("Fallback вызван для URI: " + exchange.getRequest().getURI());
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Сервис временно недоступен. Попробуйте позже."));
    }

    @RequestMapping("/fallback")
    public Mono<ResponseEntity<String>> fallbackAll(ServerWebExchange exchange) {
        System.out.println("Fallback (ALL) вызван для URI: " + exchange.getRequest().getURI());
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Сервис временно недоступен. Попробуйте позже."));
    }
}

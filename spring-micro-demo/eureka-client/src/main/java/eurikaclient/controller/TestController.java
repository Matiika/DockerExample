package eurikaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class TestController {

    @Autowired
    private ServletWebServerApplicationContext webServerAppCtxt;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/health")
    public String health() {
        int port = webServerAppCtxt.getWebServer().getPort();
        return "Client is running on port: " + port;
    }

    @GetMapping("/info")
    public String info() {
        int port = webServerAppCtxt.getWebServer().getPort();
        return "Eureka Client Application - Port: " + port;
    }
}
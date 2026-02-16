package com.example.book_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public Map<String, String> sayHello(@RequestParam(defaultValue = "Student") String name) {
        // 返回一个 Map，Spring 会自动把它转成学生喜欢的 JSON 格式
        return Map.of(
                "message", "Welcome to SpringBoard, " + name + "!",
                "status", "Running",
                "week", "1"
        );
    }
}

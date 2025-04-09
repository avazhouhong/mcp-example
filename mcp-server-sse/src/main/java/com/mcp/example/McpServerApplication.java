package com.mcp.example;

import com.mcp.example.service.ToolService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author redredzh
 * @Date 2025/4/8
 * @Description
 */
@SpringBootApplication
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }
    @Bean
    public ToolCallbackProvider dateTimeTools(ToolService dateTimeService) {
        return MethodToolCallbackProvider.builder().toolObjects(dateTimeService).build();
    }
}
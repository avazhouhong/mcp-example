package com.mcp.example.config;

import com.mcp.example.service.ToolService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author redredzh
 * @Date 2025/4/8
 * @Description
 */
@Configuration
public class McpWebFluxServiceExampleConfig {
    @Bean
    public ToolCallbackProvider dateTimeTools(ToolService dateTimeService) {
        return MethodToolCallbackProvider.builder().toolObjects(dateTimeService).build();
    }
}

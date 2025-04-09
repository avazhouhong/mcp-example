package com.mcp.example;

/**
 * @Author redredzh
 * @Date 2025/4/8
 * @Description
 */

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class McpClientExampleApplication implements CommandLineRunner {
    @Resource
    private ToolCallbackProvider tools;
    @Resource
    ChatClient.Builder chatClientBuilder;

    public static void main(String[] args) {
        SpringApplication.run(McpClientExampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        final ToolCallback[] newTools= new ToolCallback[1];
        Arrays.stream(tools.getToolCallbacks()).filter(a->a.getName().contains("getCurrentDateTime")).forEach(a->{
            newTools[0] = (ToolCallback) a;
        });
        var chatClient = chatClientBuilder
//                .defaultTools(tools)
                .defaultTools(newTools)
                .build();
        String content1 = chatClient.prompt("明天星期几?").call().content();
        System.out.println(content1);
        String content = chatClient.prompt("10分钟后，设置一个闹铃。").call().content();
        System.out.println(content);

    }

}


import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Map;

/**
 * @Author redredzh
 * @Date 2025/4/8
 * @Description MCP客户端
 */
@Slf4j
public class FileSystemMcpClient {
    public static void main(String[] args) {
        McpSyncClient client = createClient();
        tools(client);
        client.listResources().resources()
                .forEach(resource -> System.out.println(resource.name()));
        list_directory(client);
    }

    public static McpSyncClient createClient() {
        ServerParameters params = ServerParameters.builder("C:\\Program Files\\nodejs\\npx.cmd")
                .args("-y", "@modelcontextprotocol/server-filesystem", "/data")
                .build();
        StdioClientTransport transport = new StdioClientTransport(params);
// Create an async client with custom configuration
        McpSyncClient client = McpClient.sync(transport)
                .requestTimeout(Duration.ofSeconds(10))
                .capabilities(McpSchema.ClientCapabilities.builder()
                        .roots(true)      // Enable roots capability
                        .sampling()       // Enable sampling capability
                        .build())
                .build();

        client.initialize();
        return client;
    }

    public static void tools(McpSyncClient client) {
        McpSchema.ListToolsResult listToolsResult = client.listTools();
        listToolsResult.tools().forEach(System.out::println);
    }

    public static void list_directory(McpSyncClient client) {
        McpSchema.CallToolRequest callToolRequest = new McpSchema.CallToolRequest(
                "list_directory",
                Map.of("path", "D:\\data\\cache")
        );
        McpSchema.CallToolResult callToolResult = client.callTool(callToolRequest);
        System.out.println(callToolResult.content());
    }

}

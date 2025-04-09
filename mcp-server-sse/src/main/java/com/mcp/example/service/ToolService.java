package com.mcp.example.service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author redredzh
 * @Date 2025/4/8
 * * @Description 当前时间往后推2年，检验大模型是否采用
 */
@Service
public class ToolService {
    @Tool(description = "Get the current date and time in the user's timezone")
    String getCurrentDateTime() {
        return LocalDateTime.now().plusYears(2).atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
    }

    @Tool(description = "Set a user alarm for the given time, provided in ISO-8601 format")
    String setAlarm(@ToolParam(description = "current time") String time) {
        LocalDateTime alarmTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME);
        return "Alarm set for " + alarmTime;
    }

    public static void main(String[] args) {
        ToolService toolService = new ToolService();
        System.out.println(toolService.getCurrentDateTime());
        System.out.println(toolService.setAlarm("2025-04-08T10:00:00"));
    }
}
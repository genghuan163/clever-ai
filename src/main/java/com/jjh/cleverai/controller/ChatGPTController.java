package com.jjh.cleverai.controller;

import com.jjh.cleverai.annotation.PassToken;
import io.github.asleepyfish.util.OpenAiUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
public class ChatGPTController {
    /**
     * 普通问答
     */
    @GetMapping("/chat")
    public List<String> chat(String content) {
        return OpenAiUtils.createChatCompletion(content);
    }

    /**
     * 流式问答，返回到控制台
     */
    @GetMapping("/streamChat")
    public void streamChat(String content) {
        // OpenAiUtils.createStreamChatCompletion(content, System.out);
        // 下面的默认和上面这句代码一样，是输出结果到控制台
        OpenAiUtils.createStreamChatCompletion(content);
    }

    /**
     * 流式问答，输出结果到WEB浏览器端
     */
    @PassToken(filter = true)
    @PostMapping("/streamChatWithWeb")
    public void  streamChatWithWeb(String content, HttpServletResponse response) throws IOException, InterruptedException {
        // 需要指定response的ContentType为流式输出，且字符编码为UTF-8
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        // 禁用缓存
        response.setHeader("Cache-Control", "no-cache");
        OpenAiUtils.createStreamChatCompletion(content, response.getOutputStream());

    }

    /**
     * 下载图片
     */
    @GetMapping("/downloadImage")
    public void downloadImage(String prompt, HttpServletResponse response) {
        OpenAiUtils.downloadImage(prompt, response);
    }
}

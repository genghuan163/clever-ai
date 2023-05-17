package com.jjh.cleverai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjh.cleverai.utils.AESDecryptUtils;
import com.theokanning.openai.OpenAiApi;
import com.theokanning.openai.service.OpenAiService;
import io.github.asleepyfish.config.ChatGPTProperties;
import io.github.asleepyfish.util.OpenAiUtils;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.Duration;

@Configuration
@EnableConfigurationProperties(ChatGPTProperties.class)
public class ChatGPTConfigure {
    @Autowired
    private ChatGPTProperties properties;

    private final String proxyDomain = "https://api.openai-proxy.com/";

    @Bean
    public OpenAiService openAiService() {
        String token = properties.getToken();
        return new OpenAiReverseProxyService(AESDecryptUtils.decrypt(token), proxyDomain);
// return Strings.isNullOrEmpty(properties.getProxyHost()) ? new OpenAiService(properties.getToken(), Duration.ZERO) :
// new OpenAiProxyService(properties.getToken(), Duration.ZERO, properties.getProxyHost(), properties.getProxyPort());
    }

    @Bean
    public OpenAiUtils openAiUtils(OpenAiService openAiService, ChatGPTProperties properties) {
        return new OpenAiUtils(openAiService, properties);
    }

    public static class OpenAiReverseProxyService extends OpenAiService {

        private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10L);

        public OpenAiReverseProxyService(String token, String proxyDomain) {
            super(buildApi(token, proxyDomain), defaultClient(token, DEFAULT_TIMEOUT).dispatcher().executorService());
        }

        public static OpenAiApi buildApi(String token, String proxyDomain) {
            ObjectMapper mapper = defaultObjectMapper();
            OkHttpClient client = defaultClient(token, DEFAULT_TIMEOUT);
            Retrofit retrofit = defaultRetrofit(client, mapper, proxyDomain);
            return retrofit.create(OpenAiApi.class);
        }

        public static Retrofit defaultRetrofit(OkHttpClient client, ObjectMapper mapper, String proxyDomain) {
            return (new Retrofit.Builder()).baseUrl(proxyDomain).client(client).addConverterFactory(JacksonConverterFactory.create(mapper)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        }

    }
}
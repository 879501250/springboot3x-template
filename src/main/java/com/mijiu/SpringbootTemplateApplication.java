package com.mijiu;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author mijiupro
 */
@SpringBootApplication
@MapperScan("com.mijiu.mapper")
@Slf4j
public class SpringbootTemplateApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootTemplateApplication.class);
        Environment env = app.run(args).getEnvironment();
        app.setBannerMode(Banner.Mode.CONSOLE);
        logApplicationStartup(env);

    }
    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (StringUtils.isBlank(contextPath)) {
            contextPath = "/doc.html";
        } else {
            contextPath = contextPath + "/doc.html";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }
        log.info("""

                        ----------------------------------------------------------
                        \t应用程序“{}”正在运行中......
                        \t接口文档访问 URL:
                        \t本地: \t\t{}://localhost:{}{}
                        \t外部: \t{}://{}:{}{}
                        \t德鲁伊监控面板访问url:
                        \t本地: \t{}://localhost:{}/druid/login.html
                        \t外部: \t{}://{}:{}/druid/login.html
                        \t配置文件: \t{}
                       
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                protocol,
                serverPort,
                contextPath,
                protocol,
                hostAddress,
                serverPort,
                contextPath,
                protocol,
                serverPort,
                protocol,
                hostAddress,
                serverPort,
                env.getActiveProfiles()

        );
    }

}

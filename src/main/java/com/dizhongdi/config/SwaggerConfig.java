package com.dizhongdi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * ClassName:SwaggerConfig
 * Package:com.dizhongdi.config
 * Description:
 *
 * @Date: 2022/1/1 2:32
 * @Author:dizhongdi
 */

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {

    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
    @Bean
    public ApiInfo apiInfo(){

        Contact contact = new Contact("弟中弟", "http://localhost:8080/", "2755063993@qq.com");
        ApiInfo apiInfo = new ApiInfo("弟中弟的API文档",
                "学习Swagger",
                "v1.1",
                "http://localhost:8080/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
        return apiInfo;
    }

}

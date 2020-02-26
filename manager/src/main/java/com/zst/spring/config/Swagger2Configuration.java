package com.zst.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/2/25 15:32
 * @description ：swagger的配置文件
 * <p>
 * http://springfox.github.io/springfox/docs/snapshot/#configuration-explained
 * <p>
 * 启动类中添加
 * @EnableSwagger2 启用Springfox swagger 2
 * @ComponentScan(basePackages = "com.zst.spring.controller") 指示spring在哪里扫描API控制器
 *
 * springfox-swagger访问UI：http://[ip]:[port]/swagger-ui.html
 *
 * bootstrap: http://${host}:${port}/doc.html
 * knife4j: http://${host}:${port}/doc.html
 *
 * No mapping for GET /swagger-ui.html，这个异常的出现其实就是因为他按照原来的路径已经找不到了
 *
 * swagger文档的json数据地址：http://[ip]:[port]/v2/api-docs，导出后可导入yapi
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    private static final String BASE_PACKAGE = "com.zst.spring.controller";

    @Bean
    public Docket getApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("my-spring2")
                // 描述
                .description("SpringBoot - Swagger2 构建Api")
                // 作者
                .contact(new Contact("zst", "", "item233@163.com"))
                // 服务url条款
                .termsOfServiceUrl("http://www.baidu.com")
                // 版本号
                .version("1.0.0")
                .build();
    }
}

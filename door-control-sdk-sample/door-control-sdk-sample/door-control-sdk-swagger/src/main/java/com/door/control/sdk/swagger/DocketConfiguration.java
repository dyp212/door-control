/**
 * 北京钉图互动科技 all right reserver
 */
package com.door.control.sdk.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 屠科钻
 * @since 2016年9月21日
 */
@Configuration
@EnableSwagger2
public class DocketConfiguration {

	@Bean
	public Docket startApiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("agent").apiInfo(apiInfo()).select().build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API").description("请求接口需要对参数进行签名以确定安全，具体签名算法见外部文档")
				.version("1.0").build();
	}

}

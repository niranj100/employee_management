package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.service.contexts.SecurityContext;

@Configuration
public class SwaggerConfiguration {
	//private static final String BASIC_AUTH = "basicAuth";
	@Bean  //Bean - Indicates that a method produces a bean to be managed by the Spring container. 
	//Docket - A builder which is intended to be the primary interface into the Springfox framework
	public Docket api(){
// 		 HttpAuthenticationScheme authenticationScheme = HttpAuthenticationScheme
// 		            .BASIC_AUTH_BUILDER
// 		            .name("Basic Authentication")
// 		            .build();
		 return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointInfo());
//                 .securitySchemes(Collections.singletonList(authenticationScheme))
// 			 .securityContexts(List.of(securityContext()))
	}
   
	public ApiInfo apiEndPointInfo(){
        return new ApiInfoBuilder().title("EMPLOYEE MANAGEMENT SYSTEM")
                .description("Sprint")
                //.contact(new Contact("Sprint", "https://EmployeeManagementSystem.com", "pavithramohan1712@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1-SNAPSHOT")
                .build();
	}
	
// 	@SuppressWarnings("unused")
// 	private List<SecurityScheme> securitySchemes() {
//        return List.of(new BasicAuth(BASIC_AUTH));
//    }
// //	A class to represent a default set of authorizations to apply to each api operation
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(Arrays.asList(basicAuthReference())).build();
//    }
   
//    private SecurityReference basicAuthReference() {
//        return new SecurityReference(BASIC_AUTH, new AuthorizationScope[0]);
//    }
}

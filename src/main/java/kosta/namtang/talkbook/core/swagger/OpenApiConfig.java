//package kosta.namtang.talkbook.core.swagger;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
//        return new OpenAPI()
//                .components(new Components().addSecuritySchemes("basicScheme",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
//                .info(new Info().title("Tweet API").version(appVersion)
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
//
////    @Bean
////    public GroupedOpenApi storeOpenApi() {
////        String[] paths = {"/api/v1/**"};
////        return GroupedOpenApi.builder().setGroup("talkbook").pathsToMatch(paths)
////                .build();
////    }
////
////    @Bean
////    public GroupedOpenApi userOpenApi() {
////        String[] paths = {"/stream/**"};
////        String[] packagedToMatch = {"kosta.namtang.talkbook"};
////        return GroupedOpenApi.builder().setGroup("stream").pathsToMatch(paths).packagesToScan(packagedToMatch)
////                .build();
////    }
//
//
//}

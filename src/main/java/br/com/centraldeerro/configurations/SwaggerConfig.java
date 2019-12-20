package br.com.centraldeerro.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.centraldeerro.controllers"))
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.ant("/**"))
                    .build()
                .apiInfo(metaData())
                .globalOperationParameters(Arrays.asList( //ADICIONA PARAMETROS GLOBAIS EM TODOS OS ENDPOINTS
                        new ParameterBuilder()
                                .name("Authorization") //NOME DO PARAMETRO
                                .description("Header para Token OAUTH") //DESCRICAO
                                .modelRef(new ModelRef("string")) //O TIPO DELE
                                .parameterType("header") //ONDE ELE SERÁ ENVIADO
                                .required(false) //SE É NECESSÁRIO
                                .build()));
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot Codenation Central de Erros")
                .description("The best spring course out there")
                .version("1.0")
                .contact(new Contact("Lucas/Marcos/Maycon", "url", "email@email.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/license/LICENSE-2.0")
                .build();
    }


}

package io.builders.swagger;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.classmate.TypeResolver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import io.swagger.annotations.Api;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {

    private final TypeResolver typeResolver;
    private final ApplicationEventPublisher publisher;

    @Value("${spring.application.name}")
    private String nomeProjeto;

    @Value("${app.isSeguro}")
    private boolean isSeguro;

    public SwaggerConfig(TypeResolver typeResolver, ApplicationEventPublisher publisher) {
        this.typeResolver = typeResolver;
        this.publisher = publisher;
    }

    @Bean
    public Docket apiV1() {
        return criarDocket("v1");
    }

    @Bean
    public Docket apiV2() {
        return criarDocket("v2");
    }

    private Docket criarDocket(String apiVersion) {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .protocols(newHashSet(isSeguro?"https":"http"))
                .forCodeGeneration(true)
                .groupName(apiVersion)
                .produces(newHashSet(APPLICATION_JSON_UTF8_VALUE))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.regex("/" + apiVersion + "/.*"))
                .build()
                .globalOperationParameters(Arrays.asList(new ParameterBuilder().name("Authorization").parameterType("header").modelRef(new ModelRef("string")).required(false).build()))
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(LocalDateTime.class, java.util.Date.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(true)
                .globalResponseMessage(RequestMethod.GET, globalResponses())
                .globalResponseMessage(RequestMethod.POST, globalResponses())
                .globalResponseMessage(RequestMethod.DELETE, globalResponses())
                .globalResponseMessage(RequestMethod.PUT, globalResponses())
                .globalResponseMessage(RequestMethod.PATCH, globalResponses());

        publisher.publishEvent(new DocketCriadoEvent(this, docket));

        return docket;
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .validatorUrl(null)
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version("1.0.0")
                .title("API")
                .description("")
                .contact(new Contact("Builders", "builders.io", "contact.builders@mail.com"))
                .build();
    }

    private List<ResponseMessage> globalResponses() {
        return newArrayList(
                new ResponseMessageBuilder().code(500).message("Erro inesperado no servidor, não foi possível processar a solicitação").build()
                , new ResponseMessageBuilder().code(400).message("Dados inválidos fornecidos pelo cliente").build()
                , new ResponseMessageBuilder().code(401).message("Usuário não autenticado").build()
                , new ResponseMessageBuilder().code(403).message("Usuário sem permissão para esta operação").build()
        );
    }

}

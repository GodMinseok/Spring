package daelim.spring_ch10.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import daelim.spring_ch10.CommonExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/view/", ".jsp");
    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages.label");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public CommonExceptionHandler commonExceptionHandler() {
        return new CommonExceptionHandler();
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 날짜 형식 출력시 유닉스 타임스탬프로 출력하는 기능을 비활성화 -> iso-8601형태로 출력
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
//                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                .build();

        // 커스텀 패턴 사용시
        // -> java.util.date타입에만 적용
        // -> LocalDateTime 타입은 ISO-8651형태 적용
//        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
//                        .simpleDateFormat("yyyy-MM-dd")
//                                .build();

        //모든 LocalDateTime 타입에 패턴 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .serializerByType(LocalDateTime.class, new LocalDateSerializer(formatter))
                .build();
        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }

}



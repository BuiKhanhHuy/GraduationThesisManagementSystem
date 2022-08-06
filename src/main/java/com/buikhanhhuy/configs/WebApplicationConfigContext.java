package com.buikhanhhuy.configs;

import com.buikhanhhuy.converters.StringToLocalDateConverter;
import com.buikhanhhuy.formatters.*;
import com.buikhanhhuy.validators.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.buikhanhhuy.controllers", "com.buikhanhhuy.api", "com.buikhanhhuy.repository", "com.buikhanhhuy.service", "com.buikhanhhuy.validators"})
public class WebApplicationConfigContext implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**").addResourceLocations("/resources/admin/");
        registry.addResourceHandler("/user/**").addResourceLocations("/resources/user/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor).addPathPatterns("/**");
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToLocalDateConverter("yyyy-MM-dd"));
        registry.addFormatter(new UserFormatter());
        registry.addFormatter(new DepartmentFormatter());
        registry.addFormatter(new MajorFormatter());
        registry.addFormatter(new SchoolYearFormatter());
        registry.addFormatter(new PositionFormatter());
        registry.addFormatter(new RoleFormatter());
        registry.addFormatter(new LecturerFormatter());
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/validator");
        source.setDefaultEncoding("UTF-8");

        return source;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookiePath("/");
        resolver.setCookieMaxAge(30 * 24 * 60 * 60);
        resolver.setDefaultLocale(new Locale("vi"));

        return resolver;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());

        return localValidatorFactoryBean;
    }

    @Bean
    public WebAppValidator departmentValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new DepartmentValidator());

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator schoolYearValidator(){
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new SchoolYearValidator());

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator thesisValidator(){
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new ThesisInstructorsValidator());
        springValidators.add(new ThesisStudentPerformValidator());
        springValidators.add(new ThesisReviewLecturerValidator());

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }
}

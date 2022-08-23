package com.buikhanhhuy.configs;

import com.buikhanhhuy.converters.StringToLocalDateConverter;
import com.buikhanhhuy.formatters.*;
import com.buikhanhhuy.service.*;
import com.buikhanhhuy.validators.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SchoolYearService schoolYearService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ManageService manageService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ThesisService thesisService;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/public/**").addResourceLocations("/resources/public/");
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
        registry.addFormatter(new StudentFormatter());
        registry.addFormatter(new ThesisFormatter());
        registry.addFormatter(new CouncilFormatter());
        registry.addFormatter(new CouncilDetailFormatter());
        registry.addFormatter(new ScoreColumnFormatter());
    }


    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/validator", "messages/label", "messages/layout");
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
        springValidators.add(new UniqueDepartmentCodeValidator(departmentService));
        springValidators.add(new UniqueDepartmentNameValidator(departmentService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator majorValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniqueMajorCodeValidator(majorService));
        springValidators.add(new UniqueMajorNameValidator(majorService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator schoolYearValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new SchoolYearDateValidator());
        springValidators.add(new UniqueSchoolYearNameValidator(schoolYearService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator positionValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniquePositionNameValidator(positionService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator lecturerValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniqueLecturerCodeValidator(lecturerService));
        springValidators.add(new UniqueLecturerEmailValidator(lecturerService));
        springValidators.add(new UniqueLecturerPhoneValidator(lecturerService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator studentValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniqueStudentCodeValidator(studentService));
        springValidators.add(new UniqueStudentEmailValidator(studentService));
        springValidators.add(new UniqueStudentPhoneValidator(studentService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator manageValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniqueManageEmailValidator(manageService));
        springValidators.add(new UniqueManagePhoneValidator(manageService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator thesisValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new ThesisLecturersValidator());
        springValidators.add(new ThesisStudentsValidator(thesisService));
        springValidators.add(new ThesisReviewLecturerValidator());
        springValidators.add(new ThesisDateValidator());

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator councilValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new CouncilThesisValidator());
        springValidators.add(new CouncilMemberValidator());
        springValidators.add(new CouncilMemberUnique());

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator userValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniqueUserUsernameValidator(userService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public WebAppValidator topicValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UniqueTopicNameValidator(topicService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }


    @Bean
    public WebAppValidator passwordUserValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new PasswordValidator(userService));

        WebAppValidator webAppValidator = new WebAppValidator();
        webAppValidator.setValidators(springValidators);

        return webAppValidator;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap("cloud_name", "dtnpj540t", "api_key", "371357798369383", "api_secret", "9zy7ehlUetIxxl7ibee4y3tmdL4", "secure", true));
    }
}

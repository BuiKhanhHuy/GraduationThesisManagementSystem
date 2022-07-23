package com.buikhanhhuy.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{TilesConfig.class, HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebApplicationConfigContext.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}

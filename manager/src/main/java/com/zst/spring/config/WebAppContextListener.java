package com.zst.spring.config;

import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@Configuration
public class WebAppContextListener implements ServletContextListener {
    @SuppressWarnings("unused")
    private ServletContextEvent event;

    @Override
    public void contextDestroyed(ServletContextEvent event) {
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        this.event = event;
        SpringBeanUtil.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
}

package com.ustcsoft.gs.crm.webui.customer.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ustcsoft.gs.crm.webui.customer.service.impl.CustomerCommonServiceImpl;

public class CustomerListener implements ServletContextListener{

    private static final Log LOG = LogFactory.getLog(ServletContextListener.class);

    private Timer timer = null;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.debug("method contextInitialized start!");
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext());  
        CustomerCommonServiceImpl service = (CustomerCommonServiceImpl)ctx.getBean("customerCommonService");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date now = calendar.getTime();
        timer = new Timer(true);
        timer.scheduleAtFixedRate(service, now, 24 * 60 * 60 * 1000);
        LOG.debug("method contextInitialized end!");
    }

    /**
     * Destroy Timer
     * 
     * @param sce
     *            ServletContextEvent the event class for notifications about
     *            changes to the servlet context of a web application
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOG.info("method contextDestroyed start!");
        timer.cancel();
        LOG.info("method contextDestroyed end!");
    }
}

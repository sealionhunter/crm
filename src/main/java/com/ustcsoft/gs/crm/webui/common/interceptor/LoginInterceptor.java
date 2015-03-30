package com.ustcsoft.gs.crm.webui.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

    private static final long serialVersionUID = 1L;

    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }

    /**
     * 初始化
     */
    @Override
    public void init() {
    }

    /**
     * 拦截除登陆外的所有请求，若session中的登陆信息不为1，则跳转登陆页面
     */
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        Map<String, Object> session = ctx.getSession();
        if (session.isEmpty() || (Integer) session.get("isLogin") != 1) {
            HttpServletResponse response = ServletActionContext.getResponse();
            HttpServletRequest request = ServletActionContext.getRequest();
            String requestURI = request.getRequestURI();
            if (requestURI != null && requestURI.contains("/main.action")) {
                // 去登陆页面
                return Action.LOGIN;
            }
            response.addHeader("sessionStatus", "timeOut");
            String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath() + "/login.jsp";
            response.addHeader("redirectUrl", path);
            // 去跳转页面
            return "redirect";
        }
        return invocation.invoke();
    }

}

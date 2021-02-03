/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.interceptor;


import id.co.ahm.jx.sys.app000.dao.AhmjxsysLogservicesDao;
import id.co.ahm.jx.sys.app000.model.AhmjxsysLogservices;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.jxf.vo.VoPstUserCred;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author achmad.ha
 */
@Transactional(readOnly = true)
public class Interceptor extends HandlerInterceptorAdapter{
    
    @Autowired
    @Qualifier(value = "ahmjxsysLogservicesDao")
    private AhmjxsysLogservicesDao ahmjxsysLogservicesDao;
    
    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }
    
    @Override
    @Transactional(readOnly = false)
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        String agent = request.getHeader("User-Agent");
        String origin = request.getHeader("Origin");
        String token = "";
        if("POST".equalsIgnoreCase(request.getMethod())){
            token = request.getHeader(CommonConstant.JXID);
        }else if ("GET".equalsIgnoreCase(request.getMethod())){
            token = request.getParameter(CommonConstant.JXID);
        }
        long startTime = (Long) request.getAttribute("startTime");
        long finishTime = System.currentTimeMillis();
        long executeTime = finishTime - startTime;
        AhmjxsysLogservices ahmjxsysLogservices = new AhmjxsysLogservices();
        VoPstUserCred voPstUserCred = tokenPstUtil.getUserCred(token);
        if(voPstUserCred!=null){
            ahmjxsysLogservices.setVusername(voPstUserCred.getUsername());
        }else{
            ahmjxsysLogservices.setVusername("ANONIMOUS");
        }
        ahmjxsysLogservices.setVagent(agent);
        ahmjxsysLogservices.setVorigin(origin);
        ahmjxsysLogservices.setVurl(request.getRequestURL().toString());
        ahmjxsysLogservices.setVipaddress(request.getRemoteAddr());
        ahmjxsysLogservices.setIexectime(Math.toIntExact(executeTime));
        ahmjxsysLogservices.setVmethod(request.getMethod());
        ahmjxsysLogservicesDao.save(ahmjxsysLogservices, "SYSTEM", "INTERCEPTOR");
    }

    public AhmjxsysLogservicesDao getAhmjxsysLogservicesDao() {
        return ahmjxsysLogservicesDao;
    }

    public void setAhmjxsysLogservicesDao(AhmjxsysLogservicesDao ahmjxsysLogservicesDao) {
        this.ahmjxsysLogservicesDao = ahmjxsysLogservicesDao;
    }

    public TokenPstUtil getTokenPstUtil() {
        return tokenPstUtil;
    }

    public void setTokenPstUtil(TokenPstUtil tokenPstUtil) {
        this.tokenPstUtil = tokenPstUtil;
    }
}

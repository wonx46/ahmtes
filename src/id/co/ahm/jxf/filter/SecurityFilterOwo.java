/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jxf.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.security.TokenOwoUtil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author achmad.ha
 */
@Component(value = "securityFilterOwo")
public class SecurityFilterOwo implements Filter {

    @Autowired
    @Qualifier(value = "tokenOwoUtil")
    private TokenOwoUtil tokenOwoUtil;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (tokenOwoUtil.validateToken(req.getHeader("token"))) {
            chain.doFilter(request, response);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            DtoResponse dtoRespond = new DtoResponse();
            dtoRespond.setStatus("0");
            dtoRespond.addMessage("authorization", "Invalid Request");
            String jsonResponse = objectMapper.writeValueAsString(dtoRespond);
            response.setContentType("application/json");
            response.getOutputStream().print(jsonResponse);
        }
    }

    @Override
    public void destroy() {

    }

    public TokenOwoUtil getTokenOwoUtil() {
        return tokenOwoUtil;
    }

    public void setTokenOwoUtil(TokenOwoUtil tokenOwoUtil) {
        this.tokenOwoUtil = tokenOwoUtil;
    }

}

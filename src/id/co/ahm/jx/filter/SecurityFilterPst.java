/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.jx.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.co.ahm.jx.uam.app000.vo.VoAuthorize;
import id.co.ahm.jxf.constant.CommonConstant;
import id.co.ahm.jxf.constant.HTTPMethodEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.security.TokenBearerAuth;
import id.co.ahm.jxf.security.TokenPstUtil;
import id.co.ahm.jxf.util.DtoHelper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author achmad.ha
 */
@Component(value = "securityFilterPst")
public class SecurityFilterPst implements Filter {

    @Autowired
    @Qualifier(value = "tokenPstUtil")
    private TokenPstUtil tokenPstUtil;

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (validateRequest(req)) {
            chain.doFilter(request, response);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            DtoResponse dtoRespond = DtoHelper.constructResponseInvalidRequest();
            String jsonResponse = objectMapper.writeValueAsString(dtoRespond);
            response.setContentType("application/json");
            response.getOutputStream().print(jsonResponse);
        }
    }

    private boolean validateRequest(HttpServletRequest req) {
        if ((validateHttpMethodReq(req)) && (validateToken(req))) {
            if (authorizeReq(req)) {
                return true;
            }
        }
        return false;
    }

    private boolean authorizeReq(HttpServletRequest req) {
        InputStream input = null;

        try {
            String contextPath = req.getContextPath();
            String pathConfig = System.getProperty("jxconfig");
            Properties prop = new Properties();
            input = new FileInputStream(pathConfig);
            // load a properties file
            prop.load(input);
            String server = prop.getProperty("auth-server");
            if ("localhost".equals(req.getServerName())) {
                server = "http://localhost:8080";
            }
            String method = req.getMethod();
            String temp = req.getPathInfo();
            String s[] = temp.split("/");
            String pathInfo = "";
            if (s.length == 4) {
                pathInfo = temp;
            } else {
                for (int i = 1; i < 4; i++) {
                    pathInfo = pathInfo + "/" + s[i];
                }
            }
            Cookie[] cookies = req.getCookies();
            String tkid = "";
            String jxid = "";
            if (cookies != null) {
                for (Cookie ck : cookies) {
                    if (CommonConstant.TKID.equals(ck.getName())) {
                        tkid = ck.getValue();
                    } else if (CommonConstant.JXID.equals(ck.getName())) {
                        jxid = ck.getValue();
                    }
                }
            }
            pathInfo = contextPath + pathInfo;

            VoAuthorize voAuthorize = new VoAuthorize();
            voAuthorize.setUrl(pathInfo + "/");
            HttpHeaders headers = getTokenHeader(jxid, tkid);
            HttpEntity<VoAuthorize> requestWithToken = new HttpEntity<VoAuthorize>(voAuthorize, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<DtoResponse> resp = restTemplate.exchange(server + "/jx02/ahmsvipdsh000-pst/rest/ip/dsh001/authorize",
                    HttpMethod.POST,
                    requestWithToken,
                    new ParameterizedTypeReference<DtoResponse>() {
            });
            DtoResponse dtoResponse = resp.getBody();
            if ("1".equalsIgnoreCase(dtoResponse.getStatus())) {
                return true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SecurityFilterPst.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SecurityFilterPst.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(SecurityFilterPst.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    private HttpHeaders getTokenHeader(String jxid, String tkid) {
        HttpHeaders headers = new HttpHeaders();
        //headers.add("Authorization", getBasicAuthToken());
        String cookieJxid = "\"" + jxid + "\"";
        headers.add("JXID", jxid);
        headers.add("Cookie", "TKID=" + tkid);
        headers.add("Cookie", "JXID=" + cookieJxid);
        headers.add("Content-Type", "application/json");

        return headers;
    }

    private boolean validateHttpMethodReq(HttpServletRequest req) {
        if ((req.getMethod().equalsIgnoreCase(HTTPMethodEnum.GET.toString()))
                || (req.getMethod().equalsIgnoreCase(HTTPMethodEnum.POST.toString()))) {
            return true;
        }
        return false;
    }

    private boolean validateToken(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        String tkid = "";
        String jxid = "";
        String temp = "";

        if (cookies != null) {
            for (Cookie ck : cookies) {
                if (CommonConstant.TKID.equals(ck.getName())) {
                    tkid = ck.getValue();
                } else if (CommonConstant.JXID.equals(ck.getName())) {
                    temp = ck.getValue();
                }
            }
        }

        if (TokenBearerAuth.isBearer(req.getHeader("Authorization"))) {
            jxid = TokenBearerAuth.getToken(req);
        } else {
            if ((req.getMethod().equalsIgnoreCase(HTTPMethodEnum.POST.toString()))) {
                jxid = req.getHeader(CommonConstant.JXID);
            } else if ((req.getMethod().equalsIgnoreCase(HTTPMethodEnum.GET.toString()))) {
                jxid = req.getParameter(CommonConstant.JXID);
            }
        }

        if ((StringUtils.isNotBlank(jxid)) && (StringUtils.isNotBlank(tkid))
                && (StringUtils.isNotBlank(temp))) {
            if (temp.equals(jxid)) {
                return tokenPstUtil.validateToken(jxid, tkid);
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }

    public TokenPstUtil getTokenPstUtil() {
        return tokenPstUtil;
    }

    public void setTokenPstUtil(TokenPstUtil tokenPstUtil) {
        this.tokenPstUtil = tokenPstUtil;
    }

//    public AhmjxuamMstservicesDao getAhmjxuamMstservicesDao() {
//        return ahmjxuamMstservicesDao;
//    }
//
//    public void setAhmjxuamMstservicesDao(AhmjxuamMstservicesDao ahmjxuamMstservicesDao) {
//        this.ahmjxuamMstservicesDao = ahmjxuamMstservicesDao;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package id.co.ahm.jx.rest.advice;

import id.co.ahm.jxf.constant.StatusMsgEnum;
import id.co.ahm.jxf.dto.DtoResponse;
import id.co.ahm.jxf.util.DtoHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author Elly Pally
 * Created at 29/10/2018 4:40:45 PM
 */
public class BaseApiAdvice {
    
    private static final String ERROR = "error";
    private static final String SERVER_PROCESSING_ERROR = "Server Processing Error";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    DtoResponse handleException(Exception e, HttpServletResponse response) {
        e.printStackTrace();
        Map<String, Object> msg = new HashMap<String, Object>();
        msg.put(ERROR, SERVER_PROCESSING_ERROR);
        return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, msg, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DtoResponse processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        fieldErrors.get(0).getDefaultMessage();
        fieldErrors.get(0).getField();
        Map<String, Object> message = new HashMap<>();
        message = processFieldErrors(fieldErrors);
        return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, message, null);
    }

    private Map<String,Object> processFieldErrors(List<FieldError> fieldErrors) {
        Map<String,Object> result = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {      
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            result.put(fieldError.getField(), localizedErrorMessage);
        }
        return result;
    }

    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
//        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        String localizedErrorMessage = messageSource.getMessage(fieldError, null);

        //If a message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
//        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
//            String[] fieldErrorCodes = fieldError.getCodes();
//            localizedErrorMessage = fieldErrorCodes[0];
//        }
        return localizedErrorMessage;
    }

}
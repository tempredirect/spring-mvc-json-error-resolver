package com.logicalpractice.springmvcjsonerrorresolver;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author gareth
 */
@Controller
public class ApiController {

    public static class NotFoundException extends RuntimeException{
        public NotFoundException(String message) {
            super(message);
        }
    }

    public static class ReallyNotFoundException extends RuntimeException{
        public ReallyNotFoundException(String message) {
            super(message);
        }
    }


    @RequestMapping(value = "/200")
    public Map<String,Object> ok(){
        return new LinkedHashMap<String, Object>(){{
            put("key", 1);
            put("key2", 2);
        }};
    }


    @RequestMapping( value = "/404")
    public Map<String,Object> notFound() {
        throw new NotFoundException("I didn't find it");
    }


    @RequestMapping( value = "/404b")
    public Map<String,Object> notFoundB() {
        throw new ReallyNotFoundException("I didn't find it");
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, String> notFoundHandler(NotFoundException e){
        return Collections.singletonMap("message", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Really really not found")
    public Map<String, String> reallyNotFoundHandler(ReallyNotFoundException e){
        return Collections.singletonMap("message", e.getMessage());
    }

}

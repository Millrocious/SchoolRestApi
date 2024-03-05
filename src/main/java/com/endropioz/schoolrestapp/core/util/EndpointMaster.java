package com.endropioz.schoolrestapp.core.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Getter
@Component
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EndpointMaster {
    AntPathMatcher matcher = new AntPathMatcher();

    RequestMappingHandlerMapping requestMappingHandlerMapping;


    public boolean isHandlerNotExist(HttpServletRequest request) {
        HandlerExecutionChain handler;

        try {
            handler = requestMappingHandlerMapping.getHandler(request);
        } catch (Exception e) {
            return true;
        }

        return handler == null;
    }

    public boolean isEndpointMatchedWithPattern(HttpServletRequest request, String pattern) {
        return matcher.match(pattern, request.getServletPath());
    }
}

package com.homework.rewards.error;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class RewardsCustomErrorAttributes extends DefaultErrorAttributes {
	@Override
    public Map<String, Object> getErrorAttributes(
      WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = 
          super.getErrorAttributes(webRequest, options);
        errorAttributes.put("help!", "If error persists please raise a ticket at bugs.sos");

        return errorAttributes;
    }
}

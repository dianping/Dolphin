package com.dianping.paas.spring.conditional.annotation;

import com.dianping.paas.spring.conditional.AgentModuleCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Conditional(AgentModuleCondition.class)
public @interface AgentModule {

    String value() default "";

}

package com.dianping.paas.spring.conditional.annotation;

import com.dianping.paas.spring.conditional.HealthManagerModuleCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Conditional(HealthManagerModuleCondition.class)
public @interface HealthManagerModule {

    String value() default "";

}

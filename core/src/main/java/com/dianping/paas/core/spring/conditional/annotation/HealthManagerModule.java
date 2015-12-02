package com.dianping.paas.core.spring.conditional.annotation;

import com.dianping.paas.core.spring.conditional.HealthManagerModuleCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Conditional(HealthManagerModuleCondition.class)
public @interface HealthManagerModule {

    String value() default "";

}

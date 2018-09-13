package com.hxkj.aspect;

import com.hxkj.model.HttpResult;
import com.hxkj.model.Pagination;
import com.hxkj.utils.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dsd
 * @version 2018/7/6 14:01
 */
@Aspect
@Component
public class HttpServletRequestAspect {

    private final Logger logger = LogManager.getLogger(getClass());

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void pointcut() {
    }

    @SuppressWarnings("all")
    @Before("pointcut()")
    protected void before(JoinPoint joinPoint) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        Map<String, Object> report = new LinkedHashMap<>();
        Map<String, Object> methodInfo = getMethodInfo(joinPoint);
        if (null != methodInfo && methodInfo.isEmpty()) {
            return;
        }
        Object[] args = (Object[]) methodInfo.get("args");
        String[] names = (String[]) methodInfo.get("names");
        for (int i = 0; i < names.length; i++) {
            if (args[i] instanceof HttpServletResponse ||
                    args[i] instanceof HttpResult ||
                    args[i] instanceof HttpSession ||
                    args[i] instanceof Pagination) {
                continue;
            }
            if (args[i] instanceof HttpServletRequest) {
                HttpServletRequest hsr = (HttpServletRequest) args[i];
                Map<String, String[]> map = new HashMap<>();
                map.putAll(hsr.getParameterMap());
                parameters.put(names[i], map);
            } else {
                parameters.put(names[i], args[i]);
            }
        }
        report.put("Method", methodInfo.get("method"));
        report.put("Parameters", parameters);
        logger.info("\r\n{}", JsonUtils.toPJson(report));
    }

    private Map<String, Object> getMethodInfo(JoinPoint jp) {
        Map<String, Object> returns = new HashMap<>();
        CodeSignature cs = (CodeSignature) jp.getSignature();
        MethodSignature ms = (MethodSignature) jp.getSignature();

        String[] names = cs.getParameterNames();
        if (null == names) {
            return null;
        }
        String[] argNames = new String[names.length];
        Class<?>[] types = cs.getParameterTypes();
        for (int i = 0; i < names.length; i++) {
            argNames[i] = "<" + types[i].getSimpleName() + "> " + names[i];
        }
        returns.put("args", jp.getArgs());
        returns.put("names", argNames);
        returns.put("method", ms.getMethod().getDeclaringClass().getSimpleName() + "." + ms.getMethod().getName());
        return returns;
    }

}

package com.jh.www.imgmanage.config.egovconfig;

import com.jh.www.imgmanage.util.egov.EgovComTraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace;
import org.egovframe.rte.fdl.cmmn.trace.handler.TraceHandler;
import org.egovframe.rte.fdl.cmmn.trace.manager.DefaultTraceHandleManager;
import org.egovframe.rte.fdl.cmmn.trace.manager.TraceHandlerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class EgovConfigAppCommon {
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
    /**
     * @return [LeaveaTrace 설정] defaultTraceHandler 등록
     */
    @Bean
    public EgovComTraceHandler defaultTraceHandler() {
        return new EgovComTraceHandler();
    }

    /**
     * @return [LeaveaTrace 설정] traceHandlerService 등록. TraceHandler 설정
     */
    @Bean
    public DefaultTraceHandleManager traceHandlerService() {
        DefaultTraceHandleManager defaultTraceHandleManager = new DefaultTraceHandleManager();
        defaultTraceHandleManager.setReqExpMatcher(antPathMatcher());
        defaultTraceHandleManager.setPatterns(new String[] {"*"});
        defaultTraceHandleManager.setHandlers(new TraceHandler[] {defaultTraceHandler()});
        return defaultTraceHandleManager;
    }

    /**
     * @return [LeaveaTrace 설정] LeaveaTrace 등록
     */
    @Bean
    public LeaveaTrace leaveaTrace() {
        LeaveaTrace leaveaTrace = new LeaveaTrace();
        leaveaTrace.setTraceHandlerServices(new TraceHandlerService[] {traceHandlerService()});
        return leaveaTrace;
    }
    @Bean
    public CommonsMultipartResolver springRegularCommonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100000000);
        commonsMultipartResolver.setMaxInMemorySize(100000000);
        return commonsMultipartResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return springRegularCommonsMultipartResolver();
    }
}

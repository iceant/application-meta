package com.github.iceant.application.meta.console.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import java.util.Collections;

@Aspect
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* **.service.*.*(..))";
    private static final String[] REQUIRED_RULE_TRANSACTION = {"insert*", "create*", "add*", "save*","modify*", "update*", "del*", "delete*"};
    private static final String[] READ_RULE_TRANSACTION = {"select*", "get*", "query*", "search*", "count*","detail*", "find*", "list*"};


    private final TransactionManager transactionManager;

    public TransactionConfiguration(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice() {
        RuleBasedTransactionAttribute REQUIRED = new RuleBasedTransactionAttribute();
        REQUIRED.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        REQUIRED.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        RuleBasedTransactionAttribute READONLY = new RuleBasedTransactionAttribute();
        READONLY.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        READONLY.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        READONLY.setReadOnly(true);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        for (String s : REQUIRED_RULE_TRANSACTION) {
            source.addTransactionalMethod(s, REQUIRED);
        }

        for (String s : READ_RULE_TRANSACTION) {
            source.addTransactionalMethod(s, READONLY);
        }

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
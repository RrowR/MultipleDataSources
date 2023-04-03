package com.example.transactionalformultipledatasource.aspect;

import com.example.transactionalformultipledatasource.config.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: Rrow
 * @date: 2023/4/3 21:06
 * Description:
 */
@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Slf4j
public class DataSourceAop {

    @Around("@annotation(com.example.transactionalformultipledatasource.anno.FirstSource)")
    public Object setFirstDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicDataSource.DataBaseType type = DynamicDataSource.getDataBaseType();
        try {
            if (Objects.equals(type, DynamicDataSource.DataBaseType.SECOND_DATASOURCE)) {
                DynamicDataSource.DATASOURCE();
                log.info("切换到First数据源");
            } else if (Objects.equals(type, DynamicDataSource.DataBaseType.THIRD_DATASOURCE)) {
                DynamicDataSource.DATASOURCE();
                log.info("切换到First数据源");
            }
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("切换数据源异常!", e);
            throw e;
        } finally {
            DynamicDataSource.setDataBaseType(type);
        }
    }

    @Around("@annotation(com.example.transactionalformultipledatasource.anno.SecondSource)")
    public Object setSecondDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicDataSource.DataBaseType type = DynamicDataSource.getDataBaseType();
        try {
            if (Objects.equals(type, DynamicDataSource.DataBaseType.DATASOURCE)) {
                DynamicDataSource.SECOND_DATASOURCE();
                log.info("切换到SECOND数据源");
            } else if (Objects.equals(type, DynamicDataSource.DataBaseType.THIRD_DATASOURCE)) {
                DynamicDataSource.SECOND_DATASOURCE();
                log.info("切换到SECOND数据源");
            }
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("切换数据源异常!", e);
            throw e;
        } finally {
            DynamicDataSource.setDataBaseType(type);
        }
    }

    @Around("@annotation(com.example.transactionalformultipledatasource.anno.ThirdSource)")
    public Object setThirdDataSourceType(ProceedingJoinPoint joinPoint) throws Throwable {
        DynamicDataSource.DataBaseType type = DynamicDataSource.getDataBaseType();
        try {
            if (Objects.equals(type, DynamicDataSource.DataBaseType.DATASOURCE)) {
                DynamicDataSource.THIRD_DATASOURCE();
                log.info("切换到Third数据源");
            } else if (Objects.equals(type, DynamicDataSource.DataBaseType.SECOND_DATASOURCE)) {
                DynamicDataSource.THIRD_DATASOURCE();
                log.info("切换到Third数据源");
            }
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error("切换数据源异常!", e);
            throw e;
        } finally {
            DynamicDataSource.setDataBaseType(type);
        }
    }


}

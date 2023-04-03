package com.example.transactionalformultipledatasource.config;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author: Rrow
 * @date: 2023/4/3 19:49
 * Description:
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<DataBaseType> CONTEXT_HOLDER = new ThreadLocal(){
        protected DataBaseType initialValue() {
            return DataBaseType.DATASOURCE;
        }
    };

    public static enum DataBaseType {
        DATASOURCE, SECOND_DATASOURCE,THIRD_DATASOURCE
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void DATASOURCE() {
        CONTEXT_HOLDER.set(DataBaseType.DATASOURCE);
    }

    public static void SECOND_DATASOURCE() {
        CONTEXT_HOLDER.set(DataBaseType.SECOND_DATASOURCE);
    }

    public static void THIRD_DATASOURCE() {
        CONTEXT_HOLDER.set(DataBaseType.THIRD_DATASOURCE);
    }

    public static void setDataBaseType(DataBaseType dataBaseType) {
        CONTEXT_HOLDER.set(dataBaseType);
    }

    public static DataBaseType getDataBaseType() {
        return CONTEXT_HOLDER.get();
    }


}

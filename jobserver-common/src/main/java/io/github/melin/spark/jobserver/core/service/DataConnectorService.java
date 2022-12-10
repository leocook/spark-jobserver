package io.github.melin.spark.jobserver.core.service;

import com.gitee.melin.bee.core.hibernate5.HibernateBaseDao;
import com.gitee.melin.bee.core.jdbc.AbstractDialect;
import com.gitee.melin.bee.core.jdbc.Connector;
import com.gitee.melin.bee.core.jdbc.JDBCDataSourceInfo;
import com.gitee.melin.bee.core.jdbc.JdbcDialectHolder;
import com.gitee.melin.bee.core.service.BaseServiceImpl;
import io.github.melin.spark.jobserver.core.dao.DataConnectorDao;
import io.github.melin.spark.jobserver.core.entity.DataConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DataConnectorService extends BaseServiceImpl<DataConnector, Long> {

    private static final Logger LOG = LoggerFactory.getLogger(DataConnectorService.class);

    @Autowired
    private DataConnectorDao connectorDao;

    @Override
    public HibernateBaseDao<DataConnector, Long> getHibernateBaseDao() {
        return connectorDao;
    }

    public JDBCDataSourceInfo testConnection(DataConnector dataConnector) throws SQLException {
        Connector connector = dataConnector.buildDataConnector();
        AbstractDialect dialect = JdbcDialectHolder.getJdbcDialect(connector);
        return dialect.checkAccount();
    }
}

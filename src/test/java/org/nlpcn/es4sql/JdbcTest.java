package org.nlpcn.es4sql;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Jecceca on 2017/9/20.
 */
public class JdbcTest {
    @Test
    public void testJDBC() throws Exception {
        Properties properties = new Properties();
        properties.put("url", "jdbc:elasticsearch://139.224.137.75:9800,139.224.137.83:9800,/");
     //   properties.put("validationQuery", "select count(1) from cu-test-*");
        DruidDataSource dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
        Connection connection = dds.getConnection();
        String sql = "SELECT\n" +
                "\tprovinceId,\n" +
                "\tprovinceName,\n" +
                "\tcityId,\n" +
                "\tcityName,\n" +
                "\tdistrictId,\n" +
                "\tdistrictName,\n" +
                "\tcompanyId,\n" +
                "\tcompanyName,\n" +
                "\tvehicleOperateTypeId,\n" +
                "\tvehicleOperateTypeCode,\n" +
                "\tvehicleOperateTypeName,\n" +
                "\tsum(ONLINE) AS onlineQuantity,\n" +
                "\tcount(*) AS totalQuantity,\n" +
                "\tsum(todayDuration) AS totalOnlineTime\n" +
                "FROM\n" +
                "\tcu-vehicle_state_history_data-*\n" +
                "WHERE companyId=4444\n" +
                "GROUP BY\n" +
                "\tprovinceId,\n" +
                "    provinceName,\n" +
                "\tcityId,\n" +
                "\tcityName,\n" +
                "\tdistrictId,\n" +
                "\tdistrictName,\n" +
                "\tcompanyId,\n" +
                "\tcompanyName,\n" +
                "\tvehicleOperateTypeId,\n" +
                "\tvehicleOperateTypeCode,\n" +
                "\tvehicleOperateTypeName\n" +
                "\n";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        List<String> result = new ArrayList<String>();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("provinceName") + ","
                    + resultSet.getDouble("totalQuantity") + "," + resultSet.getString("districtName"));
        }
        ps.close();
        connection.close();
        dds.close();

        Thread.sleep(60 * 1000);
    }
}

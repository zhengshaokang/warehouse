package com.hys.commons.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;

import com.hys.commons.logutil.LogProxy;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 工具类JdbcUtil,读配置文件建立连接池，当不方便再spring里或者ibaits时候,用我
 * 
 */
public class JdbcUtil
{
    private static Logger log = LogProxy.getLogger(JdbcUtil.class);
    // 数据源
    private static ThreadLocal<ComboPooledDataSource> threadDataSource = new ThreadLocal<ComboPooledDataSource>()
    {
        @Override
        protected ComboPooledDataSource initialValue()
        {
            return new ComboPooledDataSource();
        }
    };

    // 取得连接
    public static Connection getConnection() throws SQLException
    {
        return threadDataSource.get().getConnection();
    }

    // 关闭连接
    public static void close(Connection conn)
    {
        try
        {
            if (conn != null)
            {
                conn.close();
            }
        }
        catch (Throwable e)
        {
            log.error("close c3p0 connection error", e);
        }
    }

    /**
     * 判断增删改是否成功
     * 
     * @param effectRowCount
     *        影响的行数
     * @return 行数大于零返回true，否则返回false
     */
    public static boolean isSuccess(int effectRowCount)
    {
        if (effectRowCount > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String[] args) throws SQLException
    {
        Connection con = JdbcUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM hys_warehouse.sys_select_list_data");

        ResultSet rs = pstmt.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getInt("code"));
        }

        JdbcUtil.close(con);

    }
}

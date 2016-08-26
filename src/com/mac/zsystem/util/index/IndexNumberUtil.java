/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.zsystem.util.index;

import com.mac.af.core.ApplicationException;
import com.mac.af.core.database.connection_pool.CConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author thilanga
 */
public class IndexNumberUtil {

    public static int getNextIndex(String tableName, String where) throws ApplicationException {

        int nextIndex = 0;
        String sql = "SELECT COUNT(*) AS row_count FROM `" + tableName + "` WHERE " + where + ";";
        System.out.println(sql);
        try {
            Connection connection = CConnectionProvider.getInstance().getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nextIndex = resultSet.getInt("row_count")+1;
                System.out.println("\t\t----------- "+nextIndex);
            }
            resultSet.close();
            preparedStatement.close();

            CConnectionProvider.getInstance().closeConnection(connection);

            return nextIndex;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ApplicationException("next index cannot be retrived\n\tSQL:" + sql);
        }
    }
}

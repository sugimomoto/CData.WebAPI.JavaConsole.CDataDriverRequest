package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Class.forName("cdata.jdbc.odata.ODataDriver");

        Connection conn = DriverManager.getConnection("jdbc:odata:URL=https://cdatanorthwindsampleapiserver.azurewebsites.net/api.rsc;Custom Headers=x-cdata-authtoken:XXXXXXXXXX;Logfile=C:\\LogFolder\\JDBC\\OData\\Burikaigi.log;Verbosity=5;");

        /*
        SELECT
        [orders].[order_id],
        [orders].[order_date],
        [orders].[customer_id],
        [orders].[employee_id],
        [order_details].[discount],
        [order_details].[product_id],
        [order_details].[quantity],
        [order_details].[unit_price]

        FROM [order_details]
        LEFT OUTER JOIN [orders]
        ON [order_details].[order_id] = [orders].[order_id]
        LIMIT 100;
         */

        String sql = "SELECT [orders].[order_id], [orders].[order_date],[orders].[customer_id],[orders].[employee_id],[order_details].[discount],[order_details].[product_id],[order_details].[quantity],[order_details].[unit_price]FROM [order_details] LEFT OUTER JOIN [orders] ON [order_details].[order_id] = [orders].[order_id] LIMIT 100;";

        Statement stat = conn.createStatement();
        boolean ret = stat.execute(sql);
        if (ret) {
            ResultSet rs=stat.getResultSet();
            while(rs.next()) {
                System.out.println("------------------------------------------------");
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                    System.out.println(rs.getMetaData().getColumnName(i) +" : "+rs.getString(i));
                }
            }
        }

    }
}

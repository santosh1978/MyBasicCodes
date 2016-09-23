package com.kent_india.santosh.sqlite;

import android.util.Log;

/**
 * Created by SANTOSH on 22/Sep/2016.
 */
public class Database {
    public static final String DB_NAME = "dbAccount";
    public static final int DB_VERSION = 1;

    public static final class my {
        public static final String TAB_NAME = "tbUserInfo";
        public static final String COL_USERNAME = "user_name";
        public static final String COL_USERPASSWORD = "user_pwd";

        public static final String sqlquery()
        {

            StringBuilder builder = new StringBuilder();
            builder.append("create table ").append(TAB_NAME)
                    .append(" (").append(COL_USERNAME).append(" Text, ")
                    .append(COL_USERPASSWORD).append(" Text)");

            Log.i("MyBasicCodes: ",  builder.toString());

            return builder.toString();
        }
    }
}

package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import database.TableSchema.Column;


public class TableData {
    DbAccess db;

    public TableData(DbAccess db) {
        this.db = db;
    }

    public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException {
        TableSchema ts = new TableSchema(db, table);
        List<Example> list = new LinkedList<>();
        Connection con = db.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT DISTINCT * FROM " + table);

        while (rs.next()) {
            Example ex = new Example();
            for (int i = 0; i < ts.getNumberOfAttributes(); i++) {
                if (ts.getColumn(i).isNumber()) ex.add(rs.getFloat(i + 1));
                else ex.add(rs.getString(i + 1));
            }
            list.add(ex);
        }
        if (list.isEmpty()) throw new EmptySetException();
        return list;
    }


    public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
        Set<Object> set = new TreeSet<>();
        Connection con = db.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT DISTINCT " + column.getColumnName() + " FROM " + table);

        while (rs.next()) {
            if (column.isNumber()) set.add(rs.getFloat(1));
            else set.add(rs.getString(1));
        }
        return set;

    }

    public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate) throws SQLException, NoValueException {
        Connection con = db.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT " + aggregate + "(" + column.getColumnName() + ") FROM " + table);
        Object obj;
        if (rs.next()) {
            if (column.isNumber()) obj = rs.getFloat(1);
            else obj = rs.getString(1);
            if (rs.wasNull()) throw new NoValueException();
        } else throw new NoValueException();
        return obj;
    }


}
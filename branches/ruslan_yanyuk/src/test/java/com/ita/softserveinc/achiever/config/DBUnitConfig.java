package com.ita.softserveinc.achiever.config;

import java.io.File;

import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DBUnitConfig extends DBTestCase {
	protected IDatabaseTester tester;
	protected IDataSet beforeData;

	public static final String DB_DRIVER_CLASS = "org.hsqldb.jdbc.JDBCDriver";
	public static final String DB_CONNECTION_URL = "jdbc:hsqldb:mem:aname";
	public static final String DB_USERNAME = "sa";
	public static final String DB_PASSWORD = "0000";

	/*
	 * public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	 * public static final String DB_CONNECTION_URL =
	 * "jdbc:mysql://localhost:3306/it_achiver"; public static final String
	 * DB_USERNAME = "root"; public static final String DB_PASSWORD =
	 * "tk8tp-9jn6p";
	 */
	@Before
	public void setUp() throws Exception {
		tester = new JdbcDatabaseTester(DB_DRIVER_CLASS, DB_CONNECTION_URL,
				DB_USERNAME, DB_PASSWORD);

	}

	public DBUnitConfig() {

	}

	public DBUnitConfig(String name) {
		super(name);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
				DB_DRIVER_CLASS);
		System.setProperty(
				PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				DB_CONNECTION_URL);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
				DB_USERNAME);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
				DB_PASSWORD);
	}
	

	@Override
	protected IDataSet getDataSet() throws Exception {
		return beforeData;
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.CLEAN_INSERT;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.DELETE_ALL;
	}

	protected IDataSet getXmlDataSetFromPath(String path) throws Exception {

		return new FlatXmlDataSetBuilder().build(Thread.currentThread()
				.getContextClassLoader().getResourceAsStream(path));
		/*return new FlatXmlDataSetBuilder().build(new File(path));*/
	}

	protected IDataSet getXlsDataSetFromPath(String path) throws Exception {

		return new XlsDataSet(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(path));
	}

}

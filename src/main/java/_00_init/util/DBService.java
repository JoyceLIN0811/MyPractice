package _00_init.util;

public class DBService {
	public static final String host = "127.0.0.1";
	public static final String DB_SQLSERVER = "SQLSERVER";

	public static final String DB_TYPE = DB_SQLSERVER;

	private static final String DBURL_SQLServer = "jdbc:sqlserver://" + host + ":1433;databaseName=midternproject";
	public static final String USERID_SQLServer = "scott";
	public static final String PSWD_SQLServer = "tiger";


	private static final String DROP_Member_SQLServer = 
			"IF OBJECT_ID('dbo.forummember', 'U') IS NOT NULL "
			+ " DROP TABLE dbo.forummember; ";
	
	private static final String DROP_Topic_SQLServer = 
			"IF OBJECT_ID('dbo.addtopic', 'U') IS NOT NULL "
			+ " DROP TABLE dbo.addtopic; ";
	
	private static final String DROP_Menu_SQLServer = 
			"IF OBJECT_ID('dbo.menu', 'U') IS NOT NULL "
			+ " DROP TABLE dbo.menu; ";
	
	private static final String DROP_Reply_SQLServer = 
			"IF OBJECT_ID('dbo.reply', 'U') IS NOT NULL "
			+ " DROP TABLE dbo.reply; ";


	private static final String CREATE_Member_SQLServer = " CREATE TABLE forummember " 
			+ "(memberid int NOT NULL IDENTITY, "
			+ " email 			    varchar(64), "
			+ " password			varchar(32), " 
			+ " username 		nvarchar(32), "
			+ " gender 				nchar(10), " 
			+ " age 						int,"
			+ " PRIMARY KEY 		(memberid) "
			+ " )";
	private static final String CREATE_Topic_SQLServer = " CREATE TABLE addtopic " 
			+ "(topicid int NOT NULL IDENTITY, "
			+ " categoryid 			    int, "
			+ " title					nvarchar(32), " 
			+ " content 		   nvarchar(1500), "
			+ " username 		nvarchar(32), "
			+" posttime			DateTime,"	
			+ " PRIMARY KEY 		(topicid) "
			+ " )";
	
	private static final String CREATE_Menu_SQLServer = " CREATE TABLE menu " 
			+ "(categoryid int NOT NULL IDENTITY, "
			+ " category			    nvarchar(20), "	
			+ " PRIMARY KEY 		(categoryid) "
			+ " )";
	
	private static final String CREATE_Reply_SQLServer = " CREATE TABLE reply " 
			+ "(replyid int NOT NULL IDENTITY, "
			+ " username 		nvarchar(32), "
			+ " topicid		    int, " 
			+ " replycontent   nvarchar(1500), "
			+" replytime			DateTime,"	
			+ " PRIMARY KEY 		(replyid) "
			+ " )";


	public static String getDropMember() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			drop = DROP_Member_SQLServer;
		}
		return drop;
	}

	public static String getCreateMember() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			create = CREATE_Member_SQLServer;
		}
		return create;
	}
	
	public static String getDropTopic() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			drop = DROP_Topic_SQLServer;
		}
		return drop;
	}

	public static String getCreateTopic() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			create = CREATE_Topic_SQLServer;
		}
		return create;
	}
	
	public static String getDropMenu() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			drop = DROP_Menu_SQLServer;
		}
		return drop;
	}

	public static String getCreateMenu() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			create = CREATE_Menu_SQLServer;
		}
		return create;
	}
	
	public static String getDropReply() {
		String drop = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			drop = DROP_Reply_SQLServer;
		}
		return drop;
	}

	public static String getCreateReply() {
		String create = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			create = CREATE_Reply_SQLServer;
		}
		return create;
	}
	
	

	public static String getDbUrl() {
		String url = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			url = DBURL_SQLServer;
		}
		return url;
	}

	public static String getUser() {
		String user = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			user = USERID_SQLServer;
		}
		return user;
	}

	public static String getPassword() {
		String pswd = null;
		if (DB_TYPE.equalsIgnoreCase(DB_SQLSERVER)) {
			pswd = PSWD_SQLServer;
		}
		return pswd;
	}

}

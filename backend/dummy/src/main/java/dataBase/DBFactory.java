package dataBase;
public abstract class DBFactory {

	public abstract Conexiune getConexiune(String where, String user,
			String pass);

	public static DBFactory getDBFactory(DBType dbt) {
		switch (dbt) {
		case CLOUDSCAPE:
			return new CloudscapeDB();
		case MySQL:
			return new MYSQLDB();
		case ORACLE:
			return new OracleDB();
		case SYBASE:
			return new SybaseDB();
		default:
			return null;

		}
	}

}

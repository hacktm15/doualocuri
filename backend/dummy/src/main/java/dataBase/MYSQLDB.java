package dataBase;
public class MYSQLDB extends DBFactory {

	@Override
	public Conexiune getConexiune(String where, String user, String pass) {
		return new ConexiuneMySQL(where, user, pass);
	}

}

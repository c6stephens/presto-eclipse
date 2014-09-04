package org.eclipse.datatools.enablement.presto.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnection;

public class PrestoJDBCConnection extends JDBCConnection {

	@SuppressWarnings("rawtypes")
	public PrestoJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}

	@Override
	public void open() {
		super.open();
		validateConnection();
	}
		
	private void validateConnection() {
		if (getRawConnection() == null) {
			if (mConnectException == null) {
				mConnectException = new SQLException("Unable to open JDBC connection - reason unknown");
			}
			return;
		}
			
		try {			
			Connection c = (Connection)getRawConnection();
			Statement s = c.createStatement();
			s.executeQuery("select current_date");
			s.close();
		}
		catch (SQLException e) {
			mConnectException = e;
		}		
	}
}

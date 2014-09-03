package org.eclipse.datatools.enablement.presto.connection;

import org.eclipse.datatools.connectivity.IConnection;
import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnectionFactory;

public class PrestoJDBCConnectionFactory extends JDBCConnectionFactory {

	public PrestoJDBCConnectionFactory() {
		super();
	}

	public IConnection createConnection(IConnectionProfile profile) {
		PrestoJDBCConnection connection = new PrestoJDBCConnection(profile, getClass());
		connection.open();
		return connection;
	}
}

package org.eclipse.datatools.enablement.presto.connection;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnection;

public class PrestoJDBCConnection extends JDBCConnection {

	public PrestoJDBCConnection(IConnectionProfile profile,
			@SuppressWarnings("rawtypes") Class factoryClass) {
		super(profile, factoryClass);
	}
}

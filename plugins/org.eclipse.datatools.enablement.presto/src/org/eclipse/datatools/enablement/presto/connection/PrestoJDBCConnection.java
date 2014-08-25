package org.eclipse.datatools.enablement.presto.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.eclipse.datatools.connectivity.IConnectionProfile;
import org.eclipse.datatools.connectivity.db.generic.JDBCConnection;

public class PrestoJDBCConnection extends JDBCConnection {

	private static final String PROP_TUNNEL_COMMAND = "org.eclipse.datatools.enablement.presto.tunnelCommand";
	
	@SuppressWarnings("rawtypes")
	public PrestoJDBCConnection(IConnectionProfile profile, Class factoryClass) {
		super(profile, factoryClass);
	}

	@Override
	public void open() {
		super.open();
		openTunnel();
		validateConnection();
	}
	
	private void openTunnel() {
		
		String tunnelCommand = getConnectionProfile()
                               .getBaseProperties()
				               .getProperty(PROP_TUNNEL_COMMAND);
		
		if (tunnelCommand != null && !tunnelCommand.trim().isEmpty()) {
		
			try {				
				Process p = Runtime.getRuntime()
						.exec(tunnelCommand);
				
				int exitCode = p.waitFor();
				
				if (exitCode != 0) {
					mConnectException = new IOException(
						"Error executing tunnel command: " +
								this.readInputStream(p.getErrorStream()) +
								this.readInputStream(p.getInputStream())
					);
				}
				
			} catch (IOException e) {
				mConnectException = e;
			} catch (InterruptedException e) {
				mConnectException = e;
			}
		}
	}
	
	private String readInputStream(InputStream i) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(i));
		StringBuilder builder = new StringBuilder();
		String line = null;
		while ((line = br.readLine()) != null) {
		   builder.append(line);
		   builder.append(System.getProperty("line.separator"));
		}
		return builder.toString();
	}
	
	private void validateConnection() {
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

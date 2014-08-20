package org.eclipse.datatools.enablement.presto.ui.core;

import org.eclipse.datatools.sqltools.core.SQLDevToolsConfiguration;
import org.eclipse.datatools.sqltools.core.services.SQLService;

public class PrestoDBConfiguration extends SQLDevToolsConfiguration {

	@Override
	public SQLService getSQLService() {
		return new PrestoSQLService();
	}
	
	@Override
	public boolean recognize(String product, String version) {

        if (product != null)
		    if (product.trim().toLowerCase().equals("presto"))
                return true;

        return false;
    }

	@Override
	public String[] getAssociatedConnectionProfileType() {
		return new String[] { "org.eclipse.datatools.enablement.presto.profile.connectionProfile" };
	}

}

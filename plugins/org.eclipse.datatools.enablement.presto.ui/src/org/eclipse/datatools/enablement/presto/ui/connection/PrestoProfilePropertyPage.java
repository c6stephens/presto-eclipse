package org.eclipse.datatools.enablement.presto.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsPropertyPage;
import org.eclipse.datatools.enablement.presto.ui.IPrestoConnectionProfileConstants;

public class PrestoProfilePropertyPage extends
		ExtensibleProfileDetailsPropertyPage {

	public PrestoProfilePropertyPage() {
		super(IPrestoConnectionProfileConstants.Presto_CATEGORY_ID);
	}
}

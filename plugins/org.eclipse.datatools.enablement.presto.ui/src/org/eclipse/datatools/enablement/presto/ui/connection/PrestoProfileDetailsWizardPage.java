package org.eclipse.datatools.enablement.presto.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.presto.ui.IPrestoConnectionProfileConstants;

public class PrestoProfileDetailsWizardPage extends
		ExtensibleProfileDetailsWizardPage {

	public PrestoProfileDetailsWizardPage(String pageName) {
		super(pageName, IPrestoConnectionProfileConstants.Presto_CATEGORY_ID);
	}
}

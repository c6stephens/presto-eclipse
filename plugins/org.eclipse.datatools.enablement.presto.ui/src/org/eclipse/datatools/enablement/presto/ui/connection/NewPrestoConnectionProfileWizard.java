package org.eclipse.datatools.enablement.presto.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleNewConnectionProfileWizard;

public class NewPrestoConnectionProfileWizard extends
		ExtensibleNewConnectionProfileWizard {
	public NewPrestoConnectionProfileWizard() {
		super(
				new PrestoProfileDetailsWizardPage(
						"org.eclipse.datatools.enablement.presto.ui.connection.PrestoProfileDetailsWizardPage")); //$NON-NLS-1$
	}
}

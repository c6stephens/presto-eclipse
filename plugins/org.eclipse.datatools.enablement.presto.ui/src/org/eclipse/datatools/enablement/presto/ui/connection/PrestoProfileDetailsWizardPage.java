/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.datatools.enablement.presto.ui.connection;

import org.eclipse.datatools.connectivity.ui.wizards.ExtensibleProfileDetailsWizardPage;
import org.eclipse.datatools.enablement.presto.ui.IPrestoConnectionProfileConstants;

public class PrestoProfileDetailsWizardPage extends
		ExtensibleProfileDetailsWizardPage {

	public PrestoProfileDetailsWizardPage(String pageName) {
		super(pageName, IPrestoConnectionProfileConstants.Presto_CATEGORY_ID);
	}
}

package org.eclipse.datatools.enablement.presto.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class PrestoActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.datatools.enablement.presto"; //$NON-NLS-1$

	// The shared instance
	private static PrestoActivator plugin;
	
	public PrestoActivator() {
		plugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static PrestoActivator getDefault() {
		return plugin;
	}

}

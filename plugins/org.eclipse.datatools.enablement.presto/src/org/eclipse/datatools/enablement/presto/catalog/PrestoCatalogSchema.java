package org.eclipse.datatools.enablement.presto.catalog;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCTableLoader;

public class PrestoCatalogSchema extends JDBCSchema {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = -1L;

	@Override
	protected JDBCTableLoader createTableLoader() {
		JDBCTableLoader tableLoader = super.createTableLoader();
		tableLoader.registerTableFactory("BASE TABLE", tableLoader.getTableFactory("TABLE"));
		return tableLoader;
	}
}

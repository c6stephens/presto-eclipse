package org.eclipse.datatools.enablement.presto.catalog;

import org.eclipse.datatools.connectivity.sqm.core.rte.ICatalogObject;
import org.eclipse.datatools.connectivity.sqm.loader.IConnectionFilterProvider;
import org.eclipse.datatools.connectivity.sqm.loader.JDBCSchemaLoader;
import org.eclipse.datatools.modelbase.sql.schema.Schema;

public class PrestoSchemaLoader extends JDBCSchemaLoader {

	public PrestoSchemaLoader() {
		super(null);
	}
	
	public PrestoSchemaLoader(ICatalogObject catalogObject) {
		super(catalogObject);
	}

	public PrestoSchemaLoader(ICatalogObject catalogObject,
			IConnectionFilterProvider connectionFilterProvider) {
		super(catalogObject, connectionFilterProvider);
	}

	protected Schema createSchema() {
		return new PrestoCatalogSchema();
	}

}

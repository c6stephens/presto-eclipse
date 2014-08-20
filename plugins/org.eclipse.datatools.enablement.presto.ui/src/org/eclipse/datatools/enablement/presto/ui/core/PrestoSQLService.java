package org.eclipse.datatools.enablement.presto.ui.core;

import org.eclipse.datatools.enablement.presto.ui.sql.PrestoSQLSyntax;
import org.eclipse.datatools.sqltools.core.services.SQLService;
import org.eclipse.datatools.sqltools.sql.ISQLSyntax;

public class PrestoSQLService extends SQLService {

	@Override
	public ISQLSyntax getSQLSyntax() {
		return new PrestoSQLSyntax();
	}

	@Override
	public String[] splitSQL(String sql, boolean splitByDefault) {
		return sql.split(";(?=([^\']*\'[^\']*\')*[^\']*$)");
	}

}

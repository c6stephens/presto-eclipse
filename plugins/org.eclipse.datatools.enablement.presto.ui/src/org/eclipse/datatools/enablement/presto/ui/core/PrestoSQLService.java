package org.eclipse.datatools.enablement.presto.ui.core;

import java.util.LinkedList;

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
		
		LinkedList<String> results = new LinkedList<String>();
		
		for (String statement : sql.split(";(?=([^\']*\'[^\']*\')*[^\']*$)")) {
			if (statement.trim().isEmpty())
				continue;
			else
				results.add(statement);
		}
		
		return results.toArray(new String[results.size()]);
	}

}

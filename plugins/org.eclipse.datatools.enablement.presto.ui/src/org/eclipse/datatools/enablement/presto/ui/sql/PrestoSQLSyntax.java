package org.eclipse.datatools.enablement.presto.ui.sql;

import org.eclipse.datatools.sqltools.sql.ISQLSyntax;

public class PrestoSQLSyntax implements ISQLSyntax {

	protected static final String[] EMPTY = new String[0];

	// Keywords
	private static final String[] _unreservedwords = {

	};

	// Keywords
	private static final String[] reservedwords = {
	    "TO",
	    "YEAR",
	    "MONTH",
	    "DAY",
	    "HOUR",
	    "MINUTE",
	    "SECOND",

	    "SELECT",
	    "ALL",
	    "DISTINCT",
	    "FROM",
	    "WHERE",
	    "GROUP",
	    "BY",
	    "HAVING",
	    "UNION",
	    "ORDER",
	    "ASC",
	    "DESC",
	    "LIMIT",
	    "JOIN",
	    "INNER",
	    "OUTER",
	    "LEFT",
	    "RIGHT",
	    "FULL",
	    "CROSS",
	    "ON",
	    "USING",
	    "AS",
	    "TABLESAMPLE",
	    "BERNOULLI",
	    "SYSTEM",

	    "OVER",
	    "PARTITION",
	    "RANGE",
	    "UNBOUNDED",
	    "PRECEDING",
	    "FOLLOWING",
	    "CURRENT",
	    "ROW",

	    "DESCRIBE",
	    "SHOW",
	    "CATALOGS",
	    "COLUMNS",
	    "PARTITIONS",
	    "SCHEMAS",
	    "TABLES",

	    "CREATE",
	    "TABLE",
	    "REPLACE",
	    "VIEW",
	    "DROP",

	    "EXPLAIN",
	    "FORMAT",
	    "TEXT",
	    "GRAPHVIZ",
	    "TYPE",
	    "LOCAL",
	    "DISTRIBUTED",

	    "USE",
	    "CATALOG",
	    "SCHEMA",
	    
	    "CASE",
	    "WHEN",
	    "ELSE",
	    "THEN",
	    "END",
	    
        "AND",
        "OR",
        "NOT",
        "IS",
        "BETWEEN",
        "LIKE",
	};

	// Understanding the Elements of SQL Statements/SQL Operations
	private static final String[] predicates = {
		"+",
		"-",
		"*",
		"/",
		"=",
		"<>",
		"!=",
		">",
		">=",
		"<",
		"<=",
		"||",
	};

	// SQL Data Types
	private static final String[] types = {
	    "BOOLEAN",
	    "BIGINT",
	    "DOUBLE",
	    "VARCHAR",
	    "VARBINARY",
	    "DATE",
	    "TIME", "WITH", "ZONE",
	    "TIMESTAMP",
	    "INTERVAL",
	};
	
	// SQL Constants
	private static final String[] constants = {
		"NULL",
		"CURRENT_DATE",
		"CURRENT_TIME",
		"CURRENT_TIMESTAMP",
		"LOCALTIME",
		"LOCALTIMESTAMP"		
	};
	
	//Understanding the Elements of SQL Statements/SQL Functions
	private static final String[] functions = {
		"GREATEST",
		"LEAST",
		"CAST",
		
		"AVG",
		"COUNT",
		"COUNT_IF",
		"MAX",
		"MAX_BY",
		"MIN",
		"SUM",
	};

	private static final String[] _comments = {
		"--"
	};

	public String[] getFunctions() {
		return functions;
	}

	public String[] getPredicates() {
		return predicates;
	}

	public String[] getReservedwords() {
		return reservedwords;
	}

	public String[] getUnreservedwords() {
		return _unreservedwords;
	}

	public String[] getTypes() {
		return types;
	}

	public Object[] getAllWords() {
		return new Object[] { getReservedwords(), getUnreservedwords(),
				getPredicates(), getTypes(), getConstants(), getFunctions(),
				getGlobalVariables() };
	}

	public String[] getConstants() {
		return constants;
	}

	public String[] getSingleLineComments() {
		return _comments;
	}

	public String[] getGlobalVariables() {
		return EMPTY;
	}
	
}

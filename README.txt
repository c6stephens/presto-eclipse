Manual Installation Instructions
--------------------------------

For the purpose of these instructions, you can treat Teradata Studio or
Teradata Studio Express as an installation of Eclipse 3.7.

1. Install Java JDK 1.7 or higher.  (Not JRE - requires JDK).

2. Copy JARs in 'eclipse/plugins' to '[Eclipse Base Directory]/plugins'.

3. Copy 'presto-jdbc/presto-jdbc-x.xx-modified.jar' to '[Eclipse Base Directory]/'.

4. Find 'libjvm.dll' or 'libjvm.dylib' in your JDK directory.  Modify the path
   to the 'bin/java' executable in 'TeradataStudioExpress.ini' or 'eclipse.ini'
   to the full path to your libjvm library.

5. Run the Teradata Studio or Eclipse executable, with the '-clean -consolelog'
   command line options.


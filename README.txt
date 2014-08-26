Installation Instructions
-------------------------

Requirements:  To use this plugin, you must have Teradata Studio Express or
               the Eclipse IDE installed.

               You must also have Java 7 or higher.  The Presto JDBC Driver
               does not work with Java 6.

1.  Check your Java version:

        java -version
               
2.  Get the 'presto' shell command working.

    a.  If you are at Netflix, you can't access Presto directly from your
        PC.  You need to set up an SSH tunnel through a cloud server.

        Check your favorite cloud server to see if you can SSH to it without
        entering a password.

        If not, do the following:

        On your computer, copy the contents of 'id_rsa.pub' to your clipboard:

            pbcopy < ~/.ssh/id_rsa.pub

        On the cloud server, paste your public key to the end of:

            vi ~/.ssh/authorized_keys

        Now try to SSH to your cloud server without entering a password.

    b.  Run './shell/presto'.  This will download the Presto CLI jar, and will
        connect to our presto instance.  Try running a query.

3.  Copy the Presto Plugin for Eclipse DTP jars into your install folder of
    Teradata Studio Express:

        sudo cp -v eclipse/plugins/*.jar /Applications/TeradataStudioExpress/plugins

4.  This Eclipse plugin is distributed with a slightly patched version of the
    Presto JDBC driver.  Copy this patched Presto JDBC jar somewhere:

        sudo cp -v presto-jdbc/*.jar /Applications/TeradataStudioExpress

5.  Change the '-vm' setting from 'java' to 'libjvm.dylib' (of an installation
    of Java 7 or higher):

        vi "/Applications/TeradataStudioExpress/Teradata Studio Express.app/Contents/MacOS/TeradataStudioExpress.ini"

        -vm
        /Library/Java/Home/jre/lib/server/libjvm.dylib
        -vmargs
        -XstartOnFirstThread
        -Dorg.eclipse.swt.internal.carbon.smallFonts
        -Dosgi.requiredJavaVersion=1.6
        -Xms512m
        -Xmx512m
        -Xverify:none
        -XX:MaxPermSize=256m
        -Djava.util.Arrays.useLegacyMergeSort=true

6.  Run Teradata Studio Express from the command line with the '-clean' option:

    cd "/Applications/TeradataStudioExpress/Teradata Studio Express.app/Contents/MacOS"
    ./TeradataStudioExpress -clean


TODO: Instructions on how to set up Presto connection inside of Teradata Studio.


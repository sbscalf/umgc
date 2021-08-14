SPOOL .\scalf-scripts-output.txt;
@scripts\Scalf-00-CreateTables.sql
@scripts\Scalf-01-InsertData.sql
@scripts\Scalf-02-Query.sql
REM @scripts\Scalf-03-Drop.sql
SPOOL OFF;
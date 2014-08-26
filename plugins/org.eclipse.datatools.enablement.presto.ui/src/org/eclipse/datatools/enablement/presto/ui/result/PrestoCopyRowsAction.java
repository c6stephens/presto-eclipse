package org.eclipse.datatools.enablement.presto.ui.result;

import java.util.Iterator;

import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.IResultSetRow;
import org.eclipse.datatools.sqltools.result.internal.ui.PreferenceConstants;
import org.eclipse.datatools.sqltools.result.internal.utils.HexHelper;
import org.eclipse.datatools.sqltools.result.ui.ResultsViewUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

public class PrestoCopyRowsAction extends Action
{
    private TableViewer _table;
    private IResultSetObject _resultObj;

    public PrestoCopyRowsAction(String text, TableViewer table, IResultSetObject resultObj) {
        super(text);
        _table = table;
        _resultObj = resultObj;
    }

    @SuppressWarnings("rawtypes")
	public void run() {
    	
        if ( _table == null || _resultObj == null ){
            return;
        }
        
        ISelection s = _table.getSelection();
        if (!(s instanceof IStructuredSelection)) {
            return;
        }
        
        String nullString = getNullValueString();
        String fieldSeparator = "\t";
        String lineSeparator = System.getProperty("line.separator");
        
        StringBuffer sb = new StringBuffer("");
        
        IStructuredSelection ss = (IStructuredSelection) s;
        Iterator iter = ss.iterator();
        while (iter.hasNext()) {
        	
            Object object = iter.next();
            if (!(object instanceof Integer)) {
                continue;
            }
            
            int r = ((Integer) object).intValue();
            
            IResultSetRow row = _resultObj.getRowData(r);
            if (row == null) {
                continue;
            }
            
            int columnCount = _resultObj.getColumnCount();
            for (int c = 1; c < columnCount+1; c++) {
                Object columnValue = row.getData(c-1);
                String outValue;

                if (columnValue == null) {
                    outValue = nullString;
                }
                else if (columnValue instanceof byte[]) {
                    outValue = HexHelper.toHexString((byte[]) columnValue);
                }
                else {
                	outValue = escape(columnValue.toString());
                }
                
                sb.append(outValue);
                
                if (c < columnCount) {
                	sb.append(fieldSeparator);
                }
            }
            
            sb.append(lineSeparator);	            
        }
        
        Clipboard clipboard = new Clipboard(Display.getCurrent());
        
        Transfer[] transfer = new Transfer[] { TextTransfer.getInstance() };
        clipboard.setContents(new Object[] { sb.toString() }, transfer);
    }
    
    private static String getNullValueString() {
        String nullValue = ResultsViewUIPlugin.getDefault().getPreferenceStore()
        		.getString(PreferenceConstants.SQL_RESULTS_VIEW_NULL_STRING);
        
        if (nullValue == null)
        	return "null";
        else
        	return nullValue;
    }
    
    private static String escape(String s) {
        StringBuffer sb = new StringBuffer("");

        if (s == null || s.trim().equals(""))
            return "";
        
        sb.append('\"');
        for (int i = 0, size = s.length(); i < size; i++) {
            
        	char c = s.charAt(i);
            
            if (c == '\"')
                sb.append("\"\"");
            else
                sb.append(c);
        }
        sb.append('\"');

        return sb.toString();
    }    
}
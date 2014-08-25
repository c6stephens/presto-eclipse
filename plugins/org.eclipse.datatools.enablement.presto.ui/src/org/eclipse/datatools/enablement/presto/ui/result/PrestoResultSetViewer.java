package org.eclipse.datatools.enablement.presto.ui.result;


import org.eclipse.datatools.sqltools.result.IResultSetObject;
import org.eclipse.datatools.sqltools.result.internal.ui.viewer.ResultSetViewer;
import org.eclipse.datatools.sqltools.result.model.IResultInstance;
import org.eclipse.datatools.sqltools.result.ui.view.ResultsViewControl;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;

public class PrestoResultSetViewer extends ResultSetViewer {
	
	private IResultSetObject _result;
	
	public PrestoResultSetViewer(Composite parent, int style,
			IResultInstance instance, IResultSetObject result,
			boolean showRowCount, ResultsViewControl resultsViewControl) {
		super(parent, style, instance, result, showRowCount, resultsViewControl);
		_result = result;
	}

	@Override
	protected void configTableViewer() {
		
		super.configTableViewer();
		
		final PrestoCopyRowsAction copyAction =
				new PrestoCopyRowsAction("Copy Row(s) with &Tabs", this, _result);
		
		this.getMenuManager().add(copyAction);
		
		this.getMenuManager().addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager manager)
            {
                IStructuredSelection ss = (IStructuredSelection) PrestoResultSetViewer.this.getSelection();
                if (ss == null || ss.toList().size() == 0) {
                    copyAction.setEnabled(false);
                }
                else {
                    copyAction.setEnabled(true);
                }
            }
        });
		
	}
}

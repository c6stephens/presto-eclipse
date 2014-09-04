package org.eclipse.datatools.enablement.presto.ui.connection.drivers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.datatools.connectivity.drivers.jdbc.IJDBCDriverDefinitionConstants;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributor;
import org.eclipse.datatools.connectivity.ui.wizards.IDriverUIContributorInformation;
import org.eclipse.datatools.connectivity.ui.wizards.OptionalPropertiesPane;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

//blic class PrestoDriverUIContributor extends OtherDriverUIContributor
public class PrestoDriverUIContributor implements IDriverUIContributor,
		Listener {
	
	private ScrolledComposite scrolledComposite = null;

	protected Text hostText;
	protected Text portText;
	protected Text catalogText;
	protected Text schemaText;
	protected Text usernameText;
	protected Text urlText;

	protected OptionalPropertiesPane optionalPropsComposite;

	private IDriverUIContributorInformation contributorInformation;

	private Properties properties;

	protected DialogPage parentPage;
	
	protected boolean isReadOnly = false;


	private Text addTextField(Composite composite, String labelString, int additionalStyles) {
		Label label = new Label(composite, SWT.NULL);
		label.setLayoutData(new GridData());
		label.setText(labelString);

		Text text = new Text(composite, SWT.BORDER | additionalStyles);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		return text;
	}	
	
	private Text addTextField(Composite composite, String labelString) {
		int additionalStyles = isReadOnly ? SWT.READ_ONLY : SWT.NONE;
		return addTextField(composite, labelString, additionalStyles);
	}
	
	public Composite getContributedDriverUI(Composite parent, boolean isReadOnly) {
		
		if ((scrolledComposite == null) || scrolledComposite.isDisposed() || (this.isReadOnly != isReadOnly)) {
			this.isReadOnly = isReadOnly;
			
			scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL|SWT.V_SCROLL);
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setExpandVertical(true);
			scrolledComposite.setLayout(new GridLayout());

			TabFolder parentComposite = new TabFolder(scrolledComposite, SWT.TOP);

			TabItem generalTab = new TabItem(parentComposite, SWT.None);
			generalTab.setText("General");

			TabItem optionalTab = new TabItem(parentComposite, SWT.None);
			optionalTab.setText("Optional");
            
			optionalPropsComposite = new OptionalPropertiesPane(parentComposite, SWT.NULL, isReadOnly);
            optionalTab.setControl(optionalPropsComposite);

			Composite generalComposite = new Composite(parentComposite, SWT.NULL);
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			generalComposite.setLayout(layout);
			generalTab.setControl(generalComposite);

			hostText       = addTextField(generalComposite, "Host");
			portText       = addTextField(generalComposite, "Port Number");
			catalogText    = addTextField(generalComposite, "Catalog");
			schemaText     = addTextField(generalComposite, "Schema");
			usernameText   = addTextField(generalComposite, "Username");
			urlText        = addTextField(generalComposite, "URL", SWT.READ_ONLY);
			
			scrolledComposite.setContent(parentComposite);
			scrolledComposite.setMinSize(parentComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			initialize();
		}
		
		return scrolledComposite;
	}

	public void setConnectionInformation() {

		properties.setProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID,
				this.urlText.getText().trim());		
		
		properties.setProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID,
				this.usernameText.getText().trim());

		optionalPropsComposite.setConnectionInformation();

        this.contributorInformation.setProperties(properties);
	}

	private void addListeners() {
		hostText.addListener(SWT.Modify, this);
		portText.addListener(SWT.Modify, this);
		catalogText.addListener(SWT.Modify, this);
		schemaText.addListener(SWT.Modify, this);
		usernameText.addListener(SWT.Modify, this);
	}	
	
	private void removeListeners() {
		hostText.removeListener(SWT.Modify, this);
		portText.removeListener(SWT.Modify, this);
		catalogText.removeListener(SWT.Modify, this);
		schemaText.removeListener(SWT.Modify, this);
		usernameText.removeListener(SWT.Modify, this);
	}

	private void initialize() {
		addListeners();
	}

	public void handleEvent(Event event) {
		if (!isReadOnly) {
			updateUrl();
			setConnectionInformation();
		}
	}

	public boolean determineContributorCompletion() {
		
		boolean isComplete = true;
		
		if (hostText.getText().trim().isEmpty()) {
			parentPage.setErrorMessage("Enter a host name");
			isComplete = false;
        }
		else if (usernameText.getText().trim().isEmpty()) {
			parentPage.setErrorMessage("Enter a username");
			isComplete = false;
        }		
		else if (!optionalPropsComposite.validateControl(parentPage)) {
            isComplete = false;
        }

		if (isComplete) {
			parentPage.setErrorMessage(null);
		}
		
		return isComplete;
	}

	public void setDialogPage(DialogPage parentPage) {
		this.parentPage = parentPage;
	}

	public void setDriverUIContributorInformation(IDriverUIContributorInformation contributorInformation) {
		this.contributorInformation = contributorInformation;
		this.properties = contributorInformation.getProperties();
        optionalPropsComposite.setDriverUIContributorInformation(contributorInformation);
	}

	public void loadProperties() {
		
		removeListeners();

		String url = this.properties.getProperty(IJDBCDriverDefinitionConstants.URL_PROP_ID);
		if (url != null) {
			urlText.setText(url.trim());
			parseUrlAndSetFields();
		}

		String username = this.properties.getProperty(IJDBCDriverDefinitionConstants.USERNAME_PROP_ID);
		if (username != null) {
			usernameText.setText(username.trim());
		}
		
        optionalPropsComposite.loadProperties();

		addListeners();
		setConnectionInformation();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSummaryData() {
		List summaryData = new ArrayList();
		summaryData.add(new String[] { "URL", this.urlText.getText().trim() });
		summaryData.add(new String[] { "Username", this.usernameText.getText().trim() });
		return summaryData;
	}
	
	protected void updateUrl() {
		String url = "jdbc:presto://";
		
		String host = hostText.getText().trim();
		url += host.isEmpty() ? "" : host;

		String port = portText.getText().trim();
		url += port.isEmpty() ? "" : ":" + port;
		
		String catalog = catalogText.getText().trim();
		
		if (!catalog.isEmpty()) {
			url += "/" + catalog;
			String schema = schemaText.getText().trim();
			url += schema.isEmpty() ? "" : "/" + schema;
		}
			
		urlText.setText(url);
	}
	
	protected void parseUrlAndSetFields() {

		String[] parts = urlText.getText().trim().split(Pattern.quote("/"));
		
		if (parts == null || parts.length <= 2)
			return;
		
		assert parts[0].equals("jdbc:presto");
		
		if (parts.length >= 3) {
			String[] hostAndPort = parts[2].split(Pattern.quote(":"));
			if (hostAndPort != null) {
				if (hostAndPort.length >= 1)
					hostText.setText(hostAndPort[0].trim());
				if (hostAndPort.length >= 2)
					portText.setText(hostAndPort[1].trim());
			}
		}

		if (parts.length >= 4)
			catalogText.setText(parts[3].trim());
		
		if (parts.length >= 5)
			schemaText.setText(parts[4].trim());
	}
}
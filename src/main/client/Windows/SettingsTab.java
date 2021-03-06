package main.client.Windows;

import main.client.HabitatConfig;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class SettingsTab extends GwtWindow {

	private HabitatConfig root;
	
	/**
	 * Default constructor
	 */
	public SettingsTab(final HabitatConfig root) {
		
		super();
		this.root = root;
	}
	
	/**
	 * Creates the contents of the settings tab
	 */
	public boolean create() {
	
		final Button toggleLoginRequired = new Button("");
		if ( root.configOptions.loginRequired() )
			toggleLoginRequired.setText("Remove Login Requirement");
		else
			toggleLoginRequired.setText("Add Login Requirement");
		
		toggleLoginRequired.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				
				root.configOptions.setLoginRequired("mars", "12345", !root.configOptions.loginRequired());
				if ( root.configOptions.loginRequired() ) {
					root.mainWindow.showLogout();
					toggleLoginRequired.setText("Remove Login Requirement");
				}
				else {
					root.mainWindow.hideLogout();
					toggleLoginRequired.setText("Add Login Requirement");
				}
			}
		});
		add(toggleLoginRequired);
		return true;
	}
}

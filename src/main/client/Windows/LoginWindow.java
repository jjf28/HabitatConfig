package main.client.Windows;

import main.client.ConfigOptions;
import main.client.HabitatConfig;
import main.client.SoundOutput;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * This is the login window for the habitat configuration software
 */
public class LoginWindow extends GwtWindow {
	
	private HabitatConfig root; // A reference to the root class
	private TextBox usernameTextBox;
	private PasswordTextBox passwordTextBox;
	
	/**
	 * Sets default variable values
	 * @param root a reference to the root class
	 */
	public LoginWindow(final HabitatConfig root) {
		
		this.root = root;
	}
	
	/**
	 * Clears the password and optionally the username from this window
	 * @param clearUsername whether the username should be cleared
	 */
	public void ClearCredentials(final boolean clearUsername) {
		
		passwordTextBox.setText("");
		if ( clearUsername )
			usernameTextBox.setText("");
	}
	
	/**
	 * This method will create the login window
	 * @return true if window creation was successful
	 */
	protected boolean create() {
		
		// Create login window...
		
		final Label usernameLbl = new Label("Username: ");
		final Label passwordLbl = new Label("Password: ");
		usernameTextBox = new TextBox();
		passwordTextBox = new PasswordTextBox();
	    final Button login = new Button("Sign on");
		login.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				
				ConfigOptions config = root.configOptions;
				SoundOutput out = new SoundOutput();
				if ( config.validateLogin(usernameTextBox.getText(), passwordTextBox.getText()) )
				{
					hide();
					root.mainWindow.show(RootPanel.get());
					out.playLoginSuccess();
				}
				else
				{
					String errorText = "Invalid Username or Password!";
					boolean nameCaseSensative = config.loginNameCaseSensative();
					boolean passCaseSensative = config.loginPassCaseSensative();
					
					if ( nameCaseSensative && passCaseSensative )
						errorText += "\n\n\nNote: The username and password are case sensative!";
					else if ( passCaseSensative )
						errorText += "\n\n\nNote: The password is case sensative!";
					else if ( nameCaseSensative )
						errorText += "\n\n\nNote: The username is case sensative!";
					
					Window.alert(errorText);
				}
			}
		});
		
		usernameTextBox.setFocus(true);
		
		add(usernameLbl);
		add(usernameTextBox);
		add(passwordLbl);
		add(passwordTextBox);
		add(login);
		
		return true; //window creation successful
	}
}

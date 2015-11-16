package application;

import java.util.List;

public class RegistrationManager {
	List<Registration> registrations;
	
	public RegistrationManager()
	{
		loadRegistrations();
	}
	
	public void addRegistration( Registration registration )
	{
		// TODO: check if registration is valid: Throw exception
			// registraties van hetzelfde huisnr mogen niet overlappen in tijd
		
		// add registration to list
		registrations.add( registration );
	}
	
	public void loadRegistrations()
	{
		// TODO: open file, read
	}
	
	public void saveRegistrations()
	{
		// TODO: open file, write
	}
}

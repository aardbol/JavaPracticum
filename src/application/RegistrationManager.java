package application;

import java.util.List;
import java.util.Vector;

public class RegistrationManager {
	List<Registration> registrations;
	
	public RegistrationManager()
	{
		registrations = new Vector<Registration>();
		loadRegistrations();
	}
	
	public void addRegistration( Registration registration )
	{
		// TODO: check if registration is valid: Throw exception
			// registraties van hetzelfde huisnr mogen niet overlappen in tijd
		
		// add registration to list
		registrations.add( registration );
	}
	
	public int getCount()
	{
		return registrations.size();
	}
	
	public List<Registration> getAllRegistrations()
	{
		return registrations;
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

package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.mgw.google.GmailServiceImpl;


/**
 * @author mgw
 *
 */
@RestController
public class EmailControllerIT {
	
	 @Test
	    public void testDisplayNbUnreadEmail() throws IOException, GeneralSecurityException {
	        //Init data
	        EmailController ec = new EmailController();
	        
	        ec.setService(new GmailServiceImpl());

	        //Appel de la méthode
	        Integer expectedEmailNumber = 12;
	        Integer result = ec.displayNbUnreadEmail("mgw");
	        
	        //Controlle de l'éxécution de la méthode
	        Assert.assertNotNull("not null unread emails number", result);
	        //Assert.assertEquals(expectedEmailNumber, result);
	    }
	 
}

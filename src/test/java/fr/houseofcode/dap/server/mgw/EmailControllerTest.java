package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.bind.annotation.RestController;

//TODO MGW by Djer |IDE| Configure ton IDE pour g�rer automatiquement les Imports (window->preferences->save Action) V�rifie aussi l'encodage des ficheir (Help->Perform setups tasks)
import fr.houseofcode.dap.server.mgw.google.GmailServiceImpl;


/**
 * @author mgw
 *
 */
@RestController
public class EmailControllerTest {
	
	 @Test
	    public void testDisplayNbUnreadEmail() throws IOException, GeneralSecurityException {
	        //Init data
	        EmailController ec = new EmailController();
	        
	        ec.setService(new GmailServiceMock());

	        //Appel de la m�thode
	        Integer expectedEmailNumber = 25;
	        Integer result = ec.displayNbUnreadEmail("mgw");
	        
	        //Controlle de l'éxécution de la méthode
	        Assert.assertNotNull("not null unread emails number", result);
	        //Assert.assertEquals(expectedEmailNumber, result);

	    }
	 
}

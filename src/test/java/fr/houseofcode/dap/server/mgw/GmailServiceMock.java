/**
 * 
 */
package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.gmail.model.Label;

import fr.houseofcode.dap.server.mgw.google.GmailService;

/**
 * @author gma
 *
 */
public class GmailServiceMock implements GmailService{

    /**
     * MOCK: Renvoie le nombre d'emails non lus dans la bo$ite principale
     * @return
     * @throws IOException
     * @throws GeneralSecurityException
     */
    public Integer getNbUnreadEmails(String userKey) throws IOException, GeneralSecurityException {

        return 12;
    }

	@Override
	public List<Label> getLabels(String userKey56) throws IOException, GeneralSecurityException {
		// TODO Auto-generated method stub
		Label label1 = new Label();
		label1.setName("bouchon mignon 1");
		
		Label label2 = new Label();
		label1.setName("bouchon mignon 12");
		
		List<Label> data = new ArrayList<Label>();
		
		data.add(1, label1);
		data.add(2, label2);

		return data;
	}
}
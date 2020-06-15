package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.mgw.google.GmailService;

//TODO MGW by Djer |JavaDoc| Il devrait y avoir une description de la classe
/** @author mgw **/
@RestController
//TODO MGW by Djer |Spring| Tu aurais pus cr�er la m�thode "displayLabel(...) dans le controller "GmailController" comme ces deux �l�ments sont �troitement li�s. "Un controller fait des actions, qui sont associ�es � des url. Il r�unit plusieurs pages qui vont avoir un point commun." Gwen Spetembre 2019 ^^.
public class LabelController {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();

    /** connexion to Gmail as a service. **/
    @Autowired
    private GmailService service;

    /**Provide all the labels of the Gmail account.
     * @return called data from GmailService
     * @param userKey allows a value for the user's parameter added to the absolute url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     **/
    @RequestMapping("/email/labels")
    public List<String> displayLabels(@RequestParam final String userKey) throws IOException, GeneralSecurityException {

        LOG.info("userKey for /email/labels : " + userKey);

        return service.getLabels(userKey);
    }

    public void setService(GmailService gmailService) {
        //TODO MGW by Djer |IDE| Supprime les TO-DO automatique une fois que tu les as trait�s.
        // TODO Auto-generated method stub
        this.service = gmailService;
    }
}

package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.mgw.google.GmailService;

/**
 * //TODO mgw by Djer |JavaDoc| Il devrait y avoir une description de la classe
 * @author mgw
 */
@RestController
public class EmailController {

    /** Logger. **/
    private static final Logger LOG = LogManager.getLogger();

    /** connection to Gmail as a service. **/
    @Autowired
    private GmailService service;

    /** Provide the number of unread emails of the Gmail account.
     * @return called data from GmailService
     * @param userKey allows a value for the user's parameter added to the absolute url //TODO mgw by Djer |JavaDoc| Tu ne dois pas indiquer le "comment". "Google User id" serait mieux.
     * @throws IOException if the sent or received message is broken
     * @throws GeneralSecurityException if there's a security failure
     **/
    @RequestMapping("/email/nbUnread")
    //TODO mgw by Djer |JavaDoc| (Optionnel) Tu peux simplifier l'annotation par "@RequestParam()", "required=true" est la valeur par defaut. Si tu ne précise pas le nom du paramtre dans la requete HTTP (value) Spring utilisera pas défaut le nom de la variable Java. Tu peux retrovuer une partie des ces informations dans la JavaDoc de l'annotation (et le reste dans la documentation de Spring :-/).
    public Integer displayNbUnreadEmail(@RequestParam(value = "userKey", required = true) final String userKey)
            throws IOException, GeneralSecurityException {

        LOG.info("userKey for /email/nbUnread : " + userKey);

        return service.getNbUnreadEmails(userKey);
    }

    public void setService(GmailService gmailService) {
        // TODO Auto-generated method stub
        this.service = gmailService;
    }
}

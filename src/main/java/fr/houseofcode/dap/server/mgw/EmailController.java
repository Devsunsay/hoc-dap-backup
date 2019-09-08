package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.mgw.google.GmailService;

/**
 * @author mgw
 *
 */
@RestController
public class EmailController {
    /** connexion to Gmail as a service. **/
    @Autowired
    private GmailService service;

    /** Provide the number of unread emails of the Gmail account.
     * @return called data from GmailService
     * @param userKey allows a value for the user's parameter added to the absolute url
     * @throws IOException if the sent or received message is broken
     * @throws GeneralSecurityException if there's a security failure
     **/
    @RequestMapping("/email/nbUnread")
    public Integer displayNbUnreadEmail(@RequestParam(value = "userKey", required = true) final String userKey)
            throws IOException, GeneralSecurityException {
        return service.getNbUnreadEmails(userKey);
    }
}

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

/** @author mgw **/
@RestController
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
        // TODO Auto-generated method stub
        this.service = gmailService;
    }
}

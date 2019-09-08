package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.gmail.model.Label;

import fr.houseofcode.dap.server.mgw.google.GmailService;

/** @author mgw **/
@RestController
public class LabelController {
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
    public List<Label> displayLabels(@RequestParam final String userKey) throws IOException, GeneralSecurityException {

        return service.getLabels(userKey);
    }
}

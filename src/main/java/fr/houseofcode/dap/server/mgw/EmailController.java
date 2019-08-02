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

    @Autowired
    private GmailService service;

    @RequestMapping
    public Integer displayNbUnreadEmail(@RequestParam(value = "userKey", required = true) final String userKey)
            throws IOException, GeneralSecurityException {
        return service.getNbUnreadEmails(userKey);
    }
}

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

/**
 * @author mgw
 *
 */
@RestController
public class LabelController {
    @Autowired
    private GmailService service;

    @RequestMapping("/labels")
    public List<Label> displayLabels(@RequestParam String userKey) throws IOException, GeneralSecurityException {

        return service.getLabels(userKey);
    }
}

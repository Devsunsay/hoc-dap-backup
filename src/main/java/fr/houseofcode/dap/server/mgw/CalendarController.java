package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.mgw.google.CalendarService;

//TODO mgw by Djer |JavaDoc| Il devrait y avoir une description de la classe
/** @author mgw **/
@RestController
public class CalendarController {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();
    
    /** connection to Gmail Calendar as a service. **/
    @Autowired
    private CalendarService service;

    //TODO mgw by Djer |JavaDoc| Dans ta Description il ne s'agit pas de "Gmail" mais de "Google". Lorsque tu crées un "compte Gmail" tu créé automatiquement un idenfiant "Google".
    /**Provide the next Event of the calendar of the Gmail account.
     * @return called data from CalendarService //TODO mgw by Djer |JavaDoc| Tu ne dois pas indiquer le "comment". "The next user event as a simple String" serait mieux.
     * @param userKey allows a value for the user's parameter added to the absolute url //TODO mgw by Djer |JavaDoc| Tu ne dois pas indiquer le "comment". "Google User id" serait mieux.
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     **/
    @RequestMapping("/calendar/nextEvent")
    public String displayNextEvent(@RequestParam final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("userKey for /calendar/nextEvent : " + userKey);

        return service.getNextEvent(userKey);
    }
}

package fr.houseofcode.dap.server.mgw;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.houseofcode.dap.server.mgw.google.CalendarService;

/** @author mgw **/
@RestController
public class CalendarController {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();
    
    /** connection to Gmail Calendar as a service. **/
    @Autowired
    private CalendarService service;

    /**Provide the next Event of the calendar of the Gmail account.
     * @return called data from CalendarService
     * @param userKey allows a value for the user's parameter added to the absolute url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     **/
    @RequestMapping("/calendar/nextEvent")
    public String displayNextEvent(@RequestParam final String userKey) throws IOException, GeneralSecurityException {
        LOG.info("userKey for /calendar/nextEvent : " + userKey);

        return service.getNextEvent(userKey);
    }
}

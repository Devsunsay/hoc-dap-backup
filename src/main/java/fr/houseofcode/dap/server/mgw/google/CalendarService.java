package fr.houseofcode.dap.server.mgw.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

//TODO mgw by Djer |JavaDoc| Il devrait y avoir une description de la classe
/** @author mgw **/
@Service
public class CalendarService {
    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();

    /** The internal application name. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /** The default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * allow the secured access to Calendar.
     * @return an GmailService'instance with secured transport.
     * @param userKey466 allows a value for the user's parameter added to the url //TODO mgw by Djer |JavaDoc| Tu ne dois pas indiquer le "comment"
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    private Calendar getService(final String userKey466) throws IOException, GeneralSecurityException {
        LOG.info("Start a secured access in the Google Calendar Service for " + userKey466);

        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY,
                Utils.getCredentials(httpTransport, userKey466)).setApplicationName(APPLICATION_NAME).build();

        return service;
    }

    /**
     * get the next event.
     * @return the next event if it exists
     * @param userKey466 allows a value for the user's parameter added to the url //TODO mgw by Djer |JavaDoc| Tu ne dois pas indiquer le "comment"
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    public String getNextEvent(final String userKey466) throws IOException, GeneralSecurityException {
        LOG.info("Get the next event of the gmail account for the user " + userKey466 + ".");

        getService(userKey466);
        //TODO mgw by Djer |POO| Ce commentaire est devenu faux (tu récupère les 2 prochains)
        //TODO mgw by Djer |API Google| Pourqoi les **deux** prochains et pas uniquement **le** prochain ?
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = getService(userKey466).events().list("primary").setMaxResults(2).setTimeMin(now)
                .setOrderBy("startTime").setSingleEvents(true).execute();
        List<Event> items = events.getItems();

        String eventText = "";

        //return items;

        if (items.isEmpty()) {
            //System.out.println("No upcoming events found.");
            eventText = "No upcoming events found.";
        } else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                DateTime end = event.getEnd().getDateTime();

                if (start == null) {
                    start = event.getStart().getDate();
                }

                if (end == null) {
                    end = event.getEnd().getDateTime();
                }

                eventText = "Event «" + eventText + event.getSummary() + "» (starting on " + start + "; ending on "
                        + end + ")\n";

                //System.out.printf("%s (%s)\n", event.getSummary(), start);
                LOG.debug("Next event text : " + eventText);
            }
        }

        return eventText;

    }
}

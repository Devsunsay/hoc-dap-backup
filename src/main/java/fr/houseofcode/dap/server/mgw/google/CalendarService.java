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
     * @param userKey466 allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    private Calendar getService(final String userKey466) throws IOException, GeneralSecurityException {
        LOG.debug("Start a secured access");
        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY,
                Utils.getCredentials(httpTransport, userKey466)).setApplicationName(APPLICATION_NAME).build();
        return service;
    }

    /**
     * get the next event.
     * @return the next event if it exists
     * @param userKey466 allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    public String getNextEvent(final String userKey466) throws IOException, GeneralSecurityException {
        getService(userKey466);
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
                if (start == null) {
                    start = event.getStart().getDate();
                }

                eventText = eventText + event.getSummary() + " (" + start + ")\n";
                //System.out.printf("%s (%s)\n", event.getSummary(), start);
            }
        }

        return eventText;

    }
}

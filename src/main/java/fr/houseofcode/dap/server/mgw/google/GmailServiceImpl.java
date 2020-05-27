package fr.houseofcode.dap.server.mgw.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/** @author mgw **/
@Service
public class GmailServiceImpl implements GmailService {

    /** Logger. */
    private static final Logger LOG = LogManager.getLogger();

    /** The internal application name. */
    private static final String APPLICATION_NAME = "Gmail API Java Quickstart";
    /** The default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /** The default user. */
    private static String user = "me";

    //    /** Singleton. */
    //    private static GmailService instance;
    //    /** Number of unread messages. */
    //    Integer resultUnread = 0;
    //
    //    /**
    //     * is an empty private constructor (cf.Singleton pattern).
    //     */
    //    private GmailService() {
    //
    //    }
    //
    //    /**
    //     * create only one new instance of GmailService (Singleton).
    //     * @return the only object GmailService (cf. Singleton).
    //     */
    //    public static GmailService getInstance() {
    //        if (instance == null) {
    //            instance = new GmailService();
    //        }
    //        return instance;
    //    }

    /**
     * allow the secured access to Gmail.
     * @return an instance GmailService with secured transport
     * @param userKey5 allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    private Gmail getService(final String userKey5) throws IOException, GeneralSecurityException {
        LOG.info("Start a secured access in the Google Calendar Service for " + userKey5);

        // Build a new authorized API client service.
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(httpTransport, JSON_FACTORY, Utils.getCredentials(httpTransport, userKey5))
                .setApplicationName(APPLICATION_NAME).build();

        return service;
    }

    /**
     * get all the labels of the Gmail account.
     * @return the list of labels.
     * @param userKey56 allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    @Override
    public List<String> getLabels(final String userKey56) throws IOException, GeneralSecurityException {
        LOG.info("Get the labels of the gmail account for the user " + userKey56 + ".");

        getService(userKey56);
        // Print the labels in the user's account.
        ListLabelsResponse listResponse = getService(userKey56).users().labels().list(user).execute();
        List<Label> labels = listResponse.getLabels();
        LOG.debug("Number of found Gmail labels : " + labels.size());

        List<String> labelNames = new ArrayList<String>();

        for (Label label : labels) {
            LOG.info("Gmail label name : " + label.getName());
            labelNames.add(label.getName());
        }

        return labelNames;
    }

    /**
     * Get the number of unread mails.
     * @return the number of unread mails.
     * @param userKey101 allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    @Override
    public Integer getNbUnreadEmails(final String userKey101) throws IOException, GeneralSecurityException {
        LOG.info("Get the number of unread emails for the user " + userKey101 + ".");

        Integer resultUnread = null;
        getService(userKey101);
        // Print the unread messages in the user's account.
        ListMessagesResponse listUnreadMessages = getService(userKey101).users().messages().list(user).setQ("is:unread")
                .execute();
        List<Message> messages = listUnreadMessages.getMessages();

        if (messages.isEmpty()) {
            //System.out.println("No unread message found.");
            resultUnread = 0;
        } else {
            resultUnread = messages.size();

            //System.out.println("Number of unread messages: " + resultUnread);
            LOG.debug("Number of unread emails : " + messages.size());
        }
        return resultUnread;
    }
}


package fr.houseofcode.dap.server.mgw.google;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

/**
 * @author adminHOC
 *
 */

/**
 * Creates an authorized Credential object.
 * @return An authorized Credential object.
 * @throws IOException If the credentials.json file cannot be found.
 */
public final class Utils {

    /** The default JsonFactory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    /**  */
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + File.separator + "git"
            + File.separator + "hoc-dap-backup" + File.separator + "tokens";

    /**
     * Global instance of the scopes required by this quick start.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = new ArrayList<String>();
    /**  */
    private static final String CREDENTIALS_FILE_PATH = System.getProperty("user.home") + File.separator + "git"
            + File.separator + "hoc-dap-backup" + File.separator + "credentials.json";

    /**
     * is an empty private constructor (cf.Singleton pattern).
     **/
    private Utils() {

    }

    /**
     * allow a secured authentication.
     * @param httpTransport is a Google instance with secured transport.
     * @param userKey allows a value for the user's parameter added to the url
     * @return the authorized user.
     * @throws IOException if the sent or received message is broken.
     **/
    public static Credential getCredentials(final NetHttpTransport httpTransport, final String userKey)
            throws IOException {

        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //return new AuthorizationCodeInstalledApp(getFlow(HTTP_TRANSPORT), receiver).authorize("user");
        SCOPES.add(CalendarScopes.CALENDAR_READONLY);
        SCOPES.add(GmailScopes.GMAIL_READONLY);

        GoogleAuthorizationCodeFlow flow = getFlow(httpTransport);
        return flow.loadCredential(userKey);

    }

    //    public static Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
    //        LOG.debug("Start of Utils.getCredentials");

    //        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
    //        return new AuthorizationCodeInstalledApp(getFlow(httpTransport), receiver).authorize("user");
    //    }

    /**
     * Allows secured log.
     * @param httpTransport is a Google instance with secured transport.
     * @return a Google secured key
     * @throws IOException if the sent or received message is broken.
     **/
    public static GoogleAuthorizationCodeFlow getFlow(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        File appClientSecretFile = new File(CREDENTIALS_FILE_PATH);
        InputStreamReader in = new InputStreamReader(new FileInputStream(appClientSecretFile),
                Charset.forName("UTF-8"));

        //        InputStream in = Utils.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        //        if (in == null) {
        //            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        //        }

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, in);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, SCOPES)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                        .setAccessType("offline").build();

        return flow;

    }
}

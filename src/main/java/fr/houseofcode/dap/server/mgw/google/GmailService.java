/**
 * 
 */
package fr.houseofcode.dap.server.mgw.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

//TODO MGW by Djer |JavaDoc| Il devrait y avoir une description de la classe
/**
 * @author gma
 *
 */
public interface GmailService {

    /**
     * Get the number of unread mails.
     * @return the number of unread mails.
     * @param userKey101 allows a value for the user's parameter added to the url //TODO MGW by Djer |JavaDoc| Le nom du paramètre n'est pas (plus?) correcte.
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    Integer getNbUnreadEmails(String userKey) throws IOException, GeneralSecurityException;

    /**
     * get all the labels of the Gmail account.
     * @return the list of labels.
     * @param userKey56 allows a value for the user's parameter added to the url
     * @throws IOException if the sent or received message is broken.
     * @throws GeneralSecurityException if there's a security failure.
     */
    List<String> getLabels(final String userKey56) throws IOException, GeneralSecurityException;
}

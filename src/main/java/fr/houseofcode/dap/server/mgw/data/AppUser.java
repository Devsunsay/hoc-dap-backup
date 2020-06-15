package fr.houseofcode.dap.server.mgw.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//TODO MGW by Djer |JavaDoc| Il devrait y avoir une description de la classe
/**
 * @author mgw
 *
 */
@Entity
public class AppUser {

    /**
     * Attribute id of database.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Attribute name of database.
     */
    private String name;

    /**
     * List of googleAccount Entity.
     */
    //TODO MGW by Djer |JPA| Le "mappedBy" fait référence à **l'attribut** de l classe liée (grace au typage JAVA) qui contient le lien. Dans ton cas ce n'estp as appUser mais "owner". Je te l'ai corrigé.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<GoogleAccount> googleAccounts;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param userId the id to set
     */
    public void setId(final Integer userId) {
        this.id = userId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param mName the name to set
     */
    public void setName(final String userName) {
        this.name = userName;
    }

    /**
     * @return the googleAccounts
     */
    public List<GoogleAccount> getGoogleAccounts() {
        return googleAccounts;
    }

    /**
     * Method to modify a googleAccount.
     * @param userGoogleAccounts the googleAccounts to set.
     */
    public void setGoogleAccounts(final List<GoogleAccount> userGoogleAccounts) {
        this.googleAccounts = userGoogleAccounts;
    }

    //TODO MGW by Djer |POO| attention tu as importé le "GoogleAccount" du package "server.mgw" au lieu de "data.mgw" (je te l'ai corrigé). En Java il n'est pas utile d'importer les classes qui sont dans le **même** package, j'ai donc simplement supprimé l'import.
    /**
     * Method to add a googleAccount.
     * @param account
     */
    public void addGoogleAccount(final GoogleAccount account) {
        account.setOwner(this);
        this.getGoogleAccounts().add(account);
    }

}

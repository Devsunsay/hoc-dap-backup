package fr.houseofcode.dap.server.mgw.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.houseofcode.dap.server.mgw.GoogleAccount;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
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

    /**
     * Method to add a googleAccount.
     * @param account
     */
    public void addGoogleAccount(final GoogleAccount account) {
        account.setOwner(this);
        this.getGoogleAccounts().add(account);
    }

}

package fr.houseofcode.dap.server.mgw.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//TODO MGW by Djer |JavaDoc| Il devrait y avoir une description de la classe
/**
 * @author mgw
 *
 */
@Entity
public class GoogleAccount {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private AppUser owner;

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
     * @return the owner
     */
    public AppUser getOwner() {
        return owner;
    }

    /**
     * @param accountOwner the owner to set
     */
    public void setOwner(final AppUser accountOwner) {
        this.owner = accountOwner;
    }

}

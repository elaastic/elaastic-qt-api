package org.elaastic.qtapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String title;
    @NotNull
    @ManyToOne
    private User owner;
    @NotNull
    @Column(name="date_created")
    private Date dateCreated;
    @NotNull
    @Column(name="last_updated")
    private Date lastUpdated;
    @Column(name="global_id")
    private String globalId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }
}

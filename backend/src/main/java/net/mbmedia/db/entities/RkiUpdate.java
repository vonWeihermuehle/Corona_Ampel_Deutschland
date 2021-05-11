package net.mbmedia.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rki_update")
public class RkiUpdate implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "zeitpunkt")
    private Date zeitpunkt;

    public RkiUpdate() {
    }

    public RkiUpdate(int id, Date date) {
        this.id = id;
        this.zeitpunkt = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getZeitpunkt() {
        return zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        this.zeitpunkt = zeitpunkt;
    }
}

package net.mbmedia.db.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "rki")
public class RKI implements Serializable{

    @EmbeddedId
    private RkiID id;

    @Column(name = "gen")
    private String gen;

    @Column(name = "bez")
    private String bez;

    @Column(name = "ags")
    private String ags;

    @Column(name = "ewz")
    private String ewz;

    @Column(name = "kfl")
    private String kfl;

    @Column(name = "death_rate")
    private String death_rate;

    @Column(name = "cases")
    private Integer cases;

    @Column(name = "deaths")
    private Integer deaths;

    @Column(name = "cases_per_100k")
    private String cases_per_100k;

    @Column(name = "cases_per_population")
    private String cases_per_population;

    @Column(name = "bl")
    private String bl;

    @Column(name = "bl_id")
    private Integer bl_id;

    @Column(name = "county")
    private String county;

    @Column(name = "cases7_per_100k")
    private String cases7_per_100k;

    @Column(name = "recoverd")
    private Integer recoverd;

    @Embeddable
    public static class RkiID implements Serializable{
        @Column(name = "objectid")
        private Integer objectId;

        @Column(name = "last_update_id")
        private Integer lastUpdateId;

        public RkiID() {        }

        public Integer getObjectId() {
            return objectId;
        }

        public void setObjectId(Integer objectId) {
            this.objectId = objectId;
        }

        public Integer getLastUpdateId() {
            return lastUpdateId;
        }

        public void setLastUpdateId(Integer lastUpdateId) {
            this.lastUpdateId = lastUpdateId;
        }
    }

    public RKI() {
    }

    public RkiID getId() {
        return id;
    }

    public void setId(RkiID id) {
        this.id = id;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getBez() {
        return bez;
    }

    public void setBez(String bez) {
        this.bez = bez;
    }

    public String getAgs() {
        return ags;
    }

    public void setAgs(String ags) {
        this.ags = ags;
    }

    public String getEwz() {
        return ewz;
    }

    public void setEwz(String ewz) {
        this.ewz = ewz;
    }

    public String getKfl() {
        return kfl;
    }

    public void setKfl(String kfl) {
        this.kfl = kfl;
    }

    public String getDeath_rate() {
        return death_rate;
    }

    public void setDeath_rate(String death_rate) {
        this.death_rate = death_rate;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public String getCases_per_100k() {
        return cases_per_100k;
    }

    public void setCases_per_100k(String cases_per_100k) {
        this.cases_per_100k = cases_per_100k;
    }

    public String getCases_per_population() {
        return cases_per_population;
    }

    public void setCases_per_population(String cases_per_population) {
        this.cases_per_population = cases_per_population;
    }

    public String getBl() {
        return bl;
    }

    public void setBl(String bl) {
        this.bl = bl;
    }

    public Integer getBl_id() {
        return bl_id;
    }

    public void setBl_id(Integer bl_id) {
        this.bl_id = bl_id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCases7_per_100k() {
        return cases7_per_100k;
    }

    public void setCases7_per_100k(String cases7_per_100k) {
        this.cases7_per_100k = cases7_per_100k;
    }

    public Integer getRecoverd() {
        return recoverd;
    }

    public void setRecoverd(Integer recoverd) {
        this.recoverd = recoverd;
    }

    @Override
    public String toString() {
        return bez + "; " + cases7_per_100k;
    }
}

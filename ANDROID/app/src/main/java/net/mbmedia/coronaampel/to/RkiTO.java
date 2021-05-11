package net.mbmedia.coronaampel.to;

public class RkiTO {

    private int objectid;
    private String gen;
    private String bez;
    private String death_rate;
    private int cases;
    private int deaths;
    private String cases_per_100k;
    private String cases_per_population;
    private String bl;
    private String cases7_per_100k;
    private int recovered;
    private int last_update_id;

    public RkiTO() {
    }

    public int getObjectid() {
        return objectid;
    }

    public void setObjectid(int objectid) {
        this.objectid = objectid;
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

    public String getDeath_rate() {
        return death_rate;
    }

    public void setDeath_rate(String death_rate) {
        this.death_rate = death_rate;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
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

    public String getCases7_per_100k() {
        return cases7_per_100k;
    }

    public void setCases7_per_100k(String cases7_per_100k) {
        this.cases7_per_100k = cases7_per_100k;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getLast_update_id() {
        return last_update_id;
    }

    public void setLast_update_id(int last_update_id) {
        this.last_update_id = last_update_id;
    }

}

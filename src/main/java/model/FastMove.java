package model;

/**
 * POJO encapsulating data about a fast move
 */
public class FastMove {

    private String moveName;
    private Type type;
    private Integer damagePvP;
    private Integer energyPvP;
    private Integer duration;


    public FastMove(String moveName, Type type, Integer damagePvP, Integer energyPvP, Integer duration) {
        this.type = type;
        this.moveName = moveName;
        this.damagePvP = damagePvP;
        this.energyPvP = energyPvP;
        this.duration = duration;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public Integer getDamagePvP() {
        return damagePvP;
    }

    public void setDamagePvP(Integer damagePvP) {
        this.damagePvP = damagePvP;
    }

    public Integer getEnergyPvP() {
        return energyPvP;
    }

    public void setEnergyPvP(Integer energyPvP) {
        this.energyPvP = energyPvP;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return this.moveName;
    }
}

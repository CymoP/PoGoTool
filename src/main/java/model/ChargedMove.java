package model;

/**
 * POJO encapsulating data about a charged move
 */
public class ChargedMove {

    private String moveName;
    private Type type;
    private Integer damagePvP;
    private Integer energyPvP;

    public ChargedMove(String moveName, Type type, Integer damagePvP, Integer energyPvP) {
        this.type = type;
        this.moveName = moveName;
        this.damagePvP = damagePvP;
        this.energyPvP = energyPvP;
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

    @Override
    public String toString(){
        return this.moveName;
    }
}

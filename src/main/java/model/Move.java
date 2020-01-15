package model;

/**
 * POJO encapsulating data about a move
 */
public class Move {

    private Type type;
    private String MoveName;
    private String moveUsage;
    private Integer dps;
    private Integer energy;
    private Integer castTime;
    private Integer cooldown;
    private Integer dpt;
    private Integer ept;

    public Move() {

    }

    public Move(String moveName, Type type, String moveUsage, Integer dps, Integer energy, Integer castTime, Integer cooldown, Integer dpt, Integer ept) {
        this.type = type;
        MoveName = moveName;
        this.moveUsage = moveUsage;
        this.dps = dps;
        this.energy = energy;
        this.castTime = castTime;
        this.cooldown = cooldown;
        this.dpt = dpt;
        this.ept = ept;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMoveUsage() {
        return moveUsage;
    }

    public void setMoveUsage(String moveUsage) {
        this.moveUsage = moveUsage;
    }

    public Integer getDps() {
        return dps;
    }

    public void setDps(Integer dps) {
        this.dps = dps;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getCastTime() {
        return castTime;
    }

    public void setCastTime(Integer castTime) {
        this.castTime = castTime;
    }

    public Integer getCooldown() {
        return cooldown;
    }

    public void setCooldown(Integer cooldown) {
        this.cooldown = cooldown;
    }

    public Integer getDpt() {
        return dpt;
    }

    public void setDpt(Integer dpt) {
        this.dpt = dpt;
    }

    public Integer getEpt() {
        return ept;
    }

    public void setEpt(Integer ept) {
        this.ept = ept;
    }

    public String getMoveName() {
        return MoveName;
    }

    public void setMoveName(String moveName) {
        MoveName = moveName;
    }
}

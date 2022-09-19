package learn.field_agent.models;

import java.util.Objects;

public class Alias {
    private int aliasId;
    private String name;

    public int getAliasId() {
        return aliasId;
    }

    public void setAliasId(int aliasId) {
        this.aliasId = aliasId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersona() {
        return name;
    }

    public void setPersona(String name) {
        this.name = name;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    private int agentId;

    public Alias() {
    }

    public Alias(int aliasId, String name) {
        this.aliasId = aliasId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        learn.field_agent.models.Alias that = (learn.field_agent.models.Alias) o;
        return aliasId == that.aliasId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aliasId, name);
    }
}

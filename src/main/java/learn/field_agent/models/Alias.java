package learn.field_agent.models;

import java.util.Objects;

public class Alias {
    private int aliasId;
    private String name;

    private int agentId;

    private String persona;

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
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
    public Alias() {
    }

    public Alias(int aliasId, String name, String persona, int agentId) {
        this.aliasId = aliasId;
        this.name = name;
        this.persona = persona;
        this.agentId=agentId;
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

package learn.field_agent.data;

import learn.field_agent.models.AgencyAgent;

import java.util.List;

public interface AgencyAgentRepository {
    boolean add(AgencyAgent agencyAgent);

    boolean update(AgencyAgent agencyAgent);

    boolean deleteByKey(int agencyId, int agentId);

    List<AgencyAgent> findAll();

    //List<AgencyAgent> findSecurityClearanceId(int securityClearanceId);

    boolean countContainsSecurityId(int securityClearanceId);
}

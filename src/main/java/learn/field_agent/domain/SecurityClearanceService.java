package learn.field_agent.domain;

import learn.field_agent.data.AgencyAgentRepository;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.AgencyAgent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityClearanceService {
    private final SecurityClearanceRepository repository;
    private final AgencyAgentRepository agencyAgentRepository;

    public SecurityClearanceService(SecurityClearanceRepository repository, AgencyAgentRepository agencyAgentRepository) {
        this.repository = repository;
        this.agencyAgentRepository= agencyAgentRepository;
    }

    public List<SecurityClearance> findAll() {
        return repository.findAll();
    }

    public SecurityClearance findById(int securityClearanceId) {
        Result<SecurityClearance> result = new Result<>();
        List<SecurityClearance> all = repository.findAll();
        for (SecurityClearance clearance: all){
            if(clearance.getSecurityClearanceId()!=securityClearanceId){
               result.addMessage("securityClearanceId could not be found", ResultType.INVALID);
            }
        }
        return repository.findById(securityClearanceId);
    }

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() != 0) {
            result.addMessage("securityClearanceId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        securityClearance = repository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() <= 0) {
            result.addMessage("securityClearanceId must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(securityClearance)) {
            String msg = String.format("securityClearanceId: %s, not found", securityClearance.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int securityClearanceId) {
        List<AgencyAgent> allAgencyAgent = agencyAgentRepository.findAll();
        Result<SecurityClearance> result = new Result<>();
        for (AgencyAgent agencyAgent: allAgencyAgent){
            if (agencyAgent.getSecurityClearance().getSecurityClearanceId()==securityClearanceId){
                result.addMessage("A 'securityClearance' cannot be deleted while in use", ResultType.INVALID);
                return false;
            }
            if(repository.deleteById(securityClearanceId)){
                result.addMessage("Success", ResultType.SUCCESS);
            }
        }
        return repository.deleteById(securityClearanceId);
    }

    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        List<SecurityClearance> allClearances = repository.findAll();
        Result<SecurityClearance> result = new Result<>();
        if (securityClearance == null) {
            result.addMessage("securityClearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("securityClearanceName is required", ResultType.INVALID);
        }
        for (SecurityClearance clearance:allClearances){
            if (clearance.getName().equalsIgnoreCase(securityClearance.getName())){
                result.addMessage("securityClearanceName cannot be duplicated", ResultType.INVALID);
            }
        }

        return result;
    }
}

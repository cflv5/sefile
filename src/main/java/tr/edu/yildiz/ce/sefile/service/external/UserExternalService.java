package tr.edu.yildiz.ce.sefile.service.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import tr.edu.yildiz.ce.se.base.domain.common.TenantUser;
import tr.edu.yildiz.ce.se.base.exception.SeBaseException;
import tr.edu.yildiz.ce.se.base.service.SeRestService;
import tr.edu.yildiz.ce.sefile.domain.external.FindTenantExternalResponse;

@Service
public class UserExternalService {
    private final SeRestService seRestService;

    @Value("${se.endpoints.user-service-internal}")
    private String userServiceUrl;

    public UserExternalService(SeRestService seRestService) {
        this.seRestService = seRestService;
    }

    public TenantUser fetchTenantUser(String email) {
        var userResponse = seRestService.call(userServiceUrl + "users/email/" + email, null,
                HttpMethod.GET, FindTenantExternalResponse.class);

        if (userResponse.getResponseHeader().isSuccess()) {
            return userResponse.getTenant();
        } else {
            throw new SeBaseException("User could not be found", HttpStatus.OK);
        }
    }
}

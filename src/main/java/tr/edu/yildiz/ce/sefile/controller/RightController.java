package tr.edu.yildiz.ce.sefile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.edu.yildiz.ce.sefile.domain.response.FetchPoliciesControllerResponse;
import tr.edu.yildiz.ce.sefile.service.RightControllerService;

@RestController
@RequestMapping("/v1/api/rights")
public class RightController {
    private final RightControllerService rightControllerService;

    @Autowired
    public RightController(RightControllerService rightControllerService) {
        this.rightControllerService = rightControllerService;
    }

    @GetMapping(value = "/file/{id}")
    public ResponseEntity<FetchPoliciesControllerResponse> fetchFileRight(@PathVariable(value = "id") String fileId) {
        return ResponseEntity.ok().body(rightControllerService.fetchRights(fileId));
    }

}

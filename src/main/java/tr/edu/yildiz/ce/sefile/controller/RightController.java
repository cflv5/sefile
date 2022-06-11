package tr.edu.yildiz.ce.sefile.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.edu.yildiz.ce.se.base.domain.OnlyHeaderControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.request.InsertFileRightControllerRequest;
import tr.edu.yildiz.ce.sefile.domain.response.FetchPoliciesControllerResponse;
import tr.edu.yildiz.ce.sefile.domain.response.InsertFileRightControllerResponse;
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

    @PostMapping(value = "/file/{id}")
    public ResponseEntity<InsertFileRightControllerResponse> addFileRight(@PathVariable(value = "id") String fileId,
            @RequestBody @Valid InsertFileRightControllerRequest request) {
        return ResponseEntity.ok().body(rightControllerService.addRight(request, fileId));
    }

    @DeleteMapping(value = "{rightId}/file/{fileId}")
    public ResponseEntity<OnlyHeaderControllerResponse> deleteFileRight(
            @PathVariable(value = "fileId") String fileId,
            @PathVariable(value = "rightId") int rightId) {
        return ResponseEntity.ok().body(rightControllerService.deleteRight(rightId, fileId));
    }

}

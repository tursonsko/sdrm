package pjatk.sdrm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoRequest;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoResponse;
import pjatk.sdrm.model.entity.SpaceDetail;
import pjatk.sdrm.service.SpaceDetailService;

import java.util.List;

@RestController
@RequestMapping("/api/manage/space")
public class SpaceDetailController {

    private final SpaceDetailService spaceDetailService;


    public SpaceDetailController(SpaceDetailService spaceDetailService) {
        this.spaceDetailService = spaceDetailService;
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<SpaceDetailDtoResponse>> getAllSpaces(){
        return ResponseEntity.ok(spaceDetailService.getAllSpaces());
    }

   //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<SpaceDetailDtoResponse> addNewSpace(@RequestBody SpaceDetailDtoRequest spaceDetail) throws UserNotFoundException {
        return ResponseEntity.ok(spaceDetailService.saveSpace(spaceDetail));
    }
}

package com.example.database2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
public class OwnerController {

    @Autowired
    private IOwnerService ownerService;

    @GetMapping("owner/{PESEL}")
    public ResponseEntity<Owner> getOwnerByPesel(@PathVariable("PESEL") float PESEL) {
        Owner owner = ownerService.getOwnerByPESEL(PESEL);
        return new ResponseEntity<Owner>(owner, HttpStatus.OK);
    }

    @GetMapping("owners")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> list = ownerService.getAllOwners();
        return new ResponseEntity<List<Owner>>(list, HttpStatus.OK);
    }

    @PostMapping("owner")
    public ResponseEntity<Void> addOwner(@RequestBody Owner owner, UriComponentsBuilder builder) {
        boolean flag = ownerService.addOwner(owner);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/owner/{PESEL}").buildAndExpand(owner.getPESEL()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("owner")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner) {
        ownerService.updateOwner(owner);
        return new ResponseEntity<Owner>(owner, HttpStatus.OK);
    }

    @DeleteMapping("owner/{PESEL}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("PESEL") float PESEL) {
        ownerService.deleteOwner(PESEL);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler
    void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}

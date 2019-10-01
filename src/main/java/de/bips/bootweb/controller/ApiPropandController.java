package de.bips.bootweb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import de.bips.bootweb.models.generated.tables.pojos.VPersonIdentifierOverview;
import de.bips.bootweb.models.generated.tables.records.VPersonIdentifierOverviewRecord;
import de.bips.bootweb.service.UserService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class ApiPropandController {

  @Autowired
  private UserService userService;


  @GetMapping("/listUsers")
  public ResponseEntity<List<VPersonIdentifierOverview>> listAll() {
    return new ResponseEntity<>(userService.getPersonIDs(), HttpStatus.OK);
  }

  @GetMapping(path = "/user")
  public ResponseEntity<VPersonIdentifierOverview> getPersonidentityWithId(
      @RequestParam String id) {

    Optional<VPersonIdentifierOverviewRecord> identifier = userService.getUserwithId(id);
    if (identifier.isPresent()) {
      return new ResponseEntity<>(identifier.get().into(VPersonIdentifierOverview.class),
          HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  // Alternativ for getPersonidentityWithId()
  @GetMapping(path = "/userWithId")
  public ResponseEntity<VPersonIdentifierOverview> getUser(@RequestParam String id) {

    Optional<VPersonIdentifierOverviewRecord> identifier = userService.getUserwithId(id);

    return identifier.map(
        record -> new ResponseEntity<>(record.into(VPersonIdentifierOverview.class), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

  }

}

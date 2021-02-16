package de.bips.bootweb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.bips.bootweb.models.generated.tables.pojos.TAddress;
import de.bips.bootweb.models.generated.tables.pojos.VPersonContactDetailOverview;
import de.bips.bootweb.models.generated.tables.pojos.VPersonIdentifierOverview;
import de.bips.bootweb.models.generated.tables.pojos.VPersonOverview;
import de.bips.bootweb.models.generated.tables.records.VPersonIdentifierOverviewRecord;
import de.bips.bootweb.models.generated.tables.records.VPersonOverviewRecord;
import de.bips.bootweb.service.UserService;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class ApiPropandController {

  @Autowired
  private UserService userService;


  @GetMapping(path = "/probands",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<VPersonOverview>> getAllProbands() {
    return new ResponseEntity<>(userService.getallProbands(), HttpStatus.OK);
  }

  @GetMapping(path = "/probands/{probandId}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<VPersonOverview> getProbandWithId(@PathVariable String probandId) {

    Optional<VPersonOverviewRecord> person = userService.getProbandWithId(probandId);

    return person
        .map(
            record -> ResponseEntity.status(HttpStatus.OK).body(record.into(VPersonOverview.class)))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new VPersonOverview()));


    // if (person.isPresent()) {
    // return new ResponseEntity<>(person.get().into(VPersonOverview.class), HttpStatus.OK);
    // }
    //
    // return ResponseEntity.status(HttpStatus.NOT_FOUND)
    // .body(new StringBuilder("No Proband with Id: ").append(id).append(" found!"));
  }

  @GetMapping(path = "/probandAddresses/{probandId}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<TAddress>> getProbandAddresses(@PathVariable String probandId) {

    List<TAddress> addresses = userService.getProbandAddresses(probandId);

    if (addresses.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

  }

  @GetMapping(path = "/probandContactDetails/{probandId}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<VPersonContactDetailOverview>> getProbandContactDetails(
      @PathVariable String probandId) {

    List<VPersonContactDetailOverview> contacts = userService.getProbandContacts(probandId);
    if (contacts.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    } else {
      return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

  }

  @GetMapping(path = "/identifierbyPidAndType/{probandId}/{identifierTypeId}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> getIdByProbandIdentifier(@PathVariable String probandId,
      @PathVariable int identifierTypeId) {

    Optional<VPersonIdentifierOverviewRecord> proband =
        userService.getIdbyProbandIdAndType(probandId, identifierTypeId);

    return proband.map(result -> result.into(VPersonIdentifierOverview.class))
        .map(row -> ResponseEntity.status(HttpStatus.OK).body(row.getValue()))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

  }

  @GetMapping(path = "/pidByIdandType/{identifier}/{identifierTypeId}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> getProbandIdentifierbyId(@PathVariable String identifier,
      @PathVariable int identifierTypeId) {

    Optional<VPersonIdentifierOverviewRecord> proband =
        userService.getProbandIdbyIdAndType(identifier, identifierTypeId);

    return proband.map(result -> result.into(VPersonIdentifierOverview.class))
        .map(row -> ResponseEntity.status(HttpStatus.OK).body(row.getPersonId()))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
  }


}

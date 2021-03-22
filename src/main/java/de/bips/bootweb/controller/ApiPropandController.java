package de.bips.bootweb.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.bips.bootweb.models.generated.tables.pojos.TAddress;
import de.bips.bootweb.models.generated.tables.pojos.TCdType;
import de.bips.bootweb.models.generated.tables.pojos.TIdentifier;
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


  @Autowired
  private PasswordEncoder encoder; // add later to another controller class z.b.
                                   // 'authenticationController'

  private static final Logger logger = LoggerFactory.getLogger(ApiPropandController.class);

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

  @PostMapping(path = "/newIdentifierForProband/{probandId}/{identifierTypeId}/{newId}")
  public ResponseEntity<Integer> setNewIdentifierforProband(@PathVariable String probandId,
      @PathVariable int identifierTypeId, @PathVariable String newId) {

    int affectedRows = userService.setNewIdentifier(probandId, identifierTypeId, newId);

    if (affectedRows == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(affectedRows);
    } else if (affectedRows == 1) {
      return ResponseEntity.status(HttpStatus.CREATED).body(affectedRows);
    } else {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(affectedRows);
    }

  }

  // add later to another controller class z.b. 'authenticationController'
  @PostMapping(path = "/newpasswordtoken/{password}/", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getBycript(@PathVariable String password) {

    String bycriptPassword = encoder.encode(password);
    logger.info("Bycript:{}", bycriptPassword);
    return ResponseEntity.status(HttpStatus.CREATED).body(bycriptPassword);
  }

  @GetMapping(path = "/contactDetails",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<TCdType>> getContactDetails() {
    List<TCdType> contactDetails = userService.getContactDetails();
    return ResponseEntity.status(HttpStatus.OK).body(contactDetails);
  }

  @GetMapping(path = "/identifiers",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<TIdentifier>> getIdentifiers() {
    List<TIdentifier> identifiers = userService.getIdentifiers();
    return ResponseEntity.status(HttpStatus.OK).body(identifiers);
  }


}

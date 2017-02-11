package ru.smartcoder.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.smartcoder.members.model.Member;
import ru.smartcoder.members.service.MembersService;

import java.net.URI;
import java.util.List;

import static ru.smartcoder.members.web.MembersController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class MembersController {

    public static final String REST_URL = "/members";

    @Autowired
    private MembersService membersService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public List<Member> getAll() {
        return membersService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public ResponseEntity<Member> get(@PathVariable("id") Long id) {
        Member member = membersService.get(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(member == null) {
            return new ResponseEntity<>(null, httpHeaders, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(membersService.get(id), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        membersService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Member member) {
        membersService.update(member);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json", "application/xml"})
    public ResponseEntity<Member> createWithLocation(@RequestBody Member member) {
        Member created = membersService.create(member);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uriOfNewResource);

        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
    }

}

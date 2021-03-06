package com.gft.gdesk.controller;

import com.gft.gdesk.exception.UserNotFoundException;
import com.gft.gdesk.exception.UserStatusAlreadyChangedException;
import com.gft.gdesk.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/api/v1/admin-panel")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/block-user/{id}")
    public void blockUserById(@PathVariable Long id) {
        try {
            adminService.blockNewUser(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (UserStatusAlreadyChangedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        throw new ResponseStatusException(HttpStatus.OK);
    }

    @PutMapping("/approve-user/{id}")
    public void acceptUserById(@PathVariable Long id) {
        try {
            adminService.acceptNewUser(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (UserStatusAlreadyChangedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        throw new ResponseStatusException(HttpStatus.OK);
    }
}

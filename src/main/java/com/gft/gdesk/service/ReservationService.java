package com.gft.gdesk.service;

import com.gft.gdesk.dto.Reservation;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private UserService userService;
    private DeskService desksService;

    public ReservationService(UserService userService,
                              DeskService desksService) {
        this.userService = userService;
        this.desksService = desksService;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    @PostConstruct
    public void setInitialReservations() {
        this.reservations.addAll(Arrays.asList(
                Reservation.builder().
                        id(0L).
                        user(userService.getUsersById(0)).
                        desk(desksService.getDesksById(0)).
                        reservations_date_start(LocalDate.now()).
                        reservations_date_end(LocalDate.now().plusDays(3)).
                        build(),
                Reservation.builder().
                        id(1L).
                        user(userService.getUsersById(1)).
                        desk(desksService.getDesksById(1)).
                        reservations_date_start(LocalDate.now()).
                        reservations_date_end(LocalDate.now().plusDays(4)).
                        build(),
                Reservation.builder().
                        id(2L).
                        user(userService.getUsersById(2)).
                        desk(desksService.getDesksById(2)).
                        reservations_date_start(LocalDate.now().plusDays(1)).
                        reservations_date_end(LocalDate.now().plusDays(2)).
                        build()
        ));
    }
}
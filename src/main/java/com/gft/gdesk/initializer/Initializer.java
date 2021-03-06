package com.gft.gdesk.initializer;

import com.gft.gdesk.entity.Desk;
import com.gft.gdesk.entity.Reservation;
import com.gft.gdesk.entity.UserModel;
import com.gft.gdesk.repository.DeskRepository;
import com.gft.gdesk.repository.ReservationRepository;
import com.gft.gdesk.repository.UserModelRepository;
import com.gft.gdesk.util.UserModelRole;
import com.gft.gdesk.util.UserModelStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Component
public class Initializer implements CommandLineRunner {

    private final DeskRepository deskRepository;
    private final ReservationRepository reservationRepository;
    private final UserModelRepository userModelRepository;
    private final PasswordEncoder passwordEncoder;

    private final List<Desk> desks = new ArrayList<>();
    private final List<Reservation> reservations = new ArrayList<>();
    private final List<UserModel> users = new ArrayList<>();


    @Override
    public void run(String... args) {
        addUserModels();
        addDesks();
        addReservations();

        userModelRepository.saveAll(users);
        deskRepository.saveAll(desks);
        reservationRepository.saveAll(reservations);
    }

    private void addReservations() {
        this.reservations.addAll(Arrays.asList(
                Reservation.builder().
                        id(0L).
                        user(users.get(0)).
                        desk(desks.get(0)).
                        reservationsDateStart(LocalDate.now()).
                        reservationsDateEnd(LocalDate.now().plusDays(3)).
                        build(),
                Reservation.builder().
                        id(1L).
                        user(users.get(1)).
                        desk(desks.get(1)).
                        reservationsDateStart(LocalDate.now()).
                        reservationsDateEnd(LocalDate.now().plusDays(4)).
                        build(),
                Reservation.builder().
                        id(2L).
                        user(users.get(2)).
                        desk(desks.get(2)).
                        reservationsDateStart(LocalDate.now().plusDays(1)).
                        reservationsDateEnd(LocalDate.now().plusDays(2)).
                        build()
        ));
    }

    private void addUserModels() {
        this.users.addAll(Arrays.asList(
                UserModel.builder().
                        id(0L).
                        name("Jan").
                        surname("Kowalski").
                        company("GFT").
                        email("jan.kowalski@gmail.com").
                        password(passwordEncoder.encode("haslo123")).
                        status(UserModelStatus.APPROVED).
                        role(UserModelRole.USER).
                        build(),
                UserModel.builder().
                        id(1L).
                        name("Piotr").
                        surname("Jaworski").
                        company("Konkurencja").
                        email("jan.kowalski1@gmail.com").
                        password(passwordEncoder.encode("xd2137")).
                        status(UserModelStatus.WAITING_FOR_APPROVAL).
                        role(UserModelRole.USER).
                        build(),
                UserModel.builder().
                        id(2L).
                        name("Canadian").
                        surname("Enjoyer").
                        company("GFT").
                        email("canadian.enjoyer@gmail.com").
                        password(passwordEncoder.encode("1337canada")).
                        status(UserModelStatus.BLOCKED).
                        role(UserModelRole.USER).
                        build()
        ));
    }

    private void addDesks() {
        this.desks.addAll(Arrays.asList(
                Desk.builder().
                        id(0L).
                        sector("A").
                        deskNumber(11).
                        floor(9).
                        build(),
                Desk.builder().
                        id(1L).
                        sector("A").
                        deskNumber(21).
                        floor(10).
                        build(),
                Desk.builder().
                        id(2L).
                        sector("C").
                        deskNumber(37).
                        floor(2).
                        build(),
                Desk.builder().
                        id(4L).
                        sector("B").
                        deskNumber(46).
                        floor(11).
                        build(),
                Desk.builder().
                        id(5L).
                        sector("B").
                        deskNumber(47).
                        floor(11).
                        build(),
                Desk.builder().
                        id(6L).
                        sector("B").
                        deskNumber(49).
                        floor(11).
                        build(),
                Desk.builder().
                        id(7L).
                        sector("C").
                        deskNumber(51).
                        floor(9).
                        build(),
                Desk.builder().
                        id(8L).
                        sector("C").
                        deskNumber(52).
                        floor(10).
                        build()
        ));
    }
}

package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.repositories.TurnRepository;
import com.boot.bookingrestaurantapi.services.impl.ReservationServiceImpl;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {

    private static final CreateReservationRest RESERVATION_REST = new CreateReservationRest();

    private static final Restaurant RESTAURANT = new Restaurant();
    private static final Turn TURN = new Turn();

    private static final Long RESTAURANT_ID = 1L;
    private  static final String DESCRIPTION = "We sell burgers";
    private  static final String ADDRESS = "200 St. Boulevard";
    private  static final String IMAGE = "www.image.com";
    private  static final String NAME = "BURGER RESTAURANT";
    private  static final Date DATE = new Date();
    private  static final Long PERSON = 20L;
    private  static final Long TURN_ID = 6L;

    private  static final String SUCCESS_STATUS = "Succes";
    private  static final String SUCCESS_CODE = "200 OK";
    private  static final String OK = "OK";
    private  static final String LOCATOR = "BURGER2";

    public static final List<Turn> TURN_LIST = new ArrayList<>();
    public static final List<Board> BOARDS_LIST = new ArrayList<>();
    public static final List<Reservation> RESERVATIONS_LIST = new ArrayList<>();

    private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
    private static final Optional<Restaurant> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();
    private static final Optional<Turn> OPTIONAL_TURN = Optional.of(TURN);
    private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private TurnRepository turnRepository;
    @Mock
    private ReservationRespository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
        RESERVATION_REST.setTurnId(TURN_ID);
        RESERVATION_REST.setPerson(PERSON);
        RESERVATION_REST.setDate(DATE);

        TURN.setId(TURN_ID);
        TURN.setName(NAME);
        TURN.setRestaurant(RESTAURANT);

        RESTAURANT.setName(NAME);
        RESTAURANT.setId(RESTAURANT_ID);
        RESTAURANT.setDescription(DESCRIPTION);
        RESTAURANT.setAddress(ADDRESS);
        RESTAURANT.setImage(IMAGE);
        RESTAURANT.setTurns(TURN_LIST);
        RESTAURANT.setBoards(BOARDS_LIST);
        RESTAURANT.setReservations(RESERVATIONS_LIST);
    }

    @Test
    public void createReservationTest() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
        Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
        Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId()))
                .thenReturn(OPTIONAL_RESERVATION_EMPTY);
        Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());

        assertEquals("BURGER RESTAURANT6", reservationService.createReservation(RESERVATION_REST));
    }

    @Test (expected = BookingException.class)
    public void createReservationFindByIdTestError() throws BookingException {
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);

        reservationService.createReservation(RESERVATION_REST);
        fail();
    }
}

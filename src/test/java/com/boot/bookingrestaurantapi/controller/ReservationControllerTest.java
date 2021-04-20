package com.boot.bookingrestaurantapi.controller;

import com.boot.bookingrestaurantapi.controllers.ReservationController;
import com.boot.bookingrestaurantapi.controllers.RestaurantController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.CreateReservationRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.ReservationService;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationControllerTest {

    private static final Long RESTAURANT_ID = 1L;
    private  static final String NAME = "BURGER RESTAURANT";
    private  static final Date DATE = new Date();
    private  static final Long PERSON = 1L;
    private  static final Long TURN_ID = 1L;

    private  static final String SUCCESS_STATUS = "Succes";
    private  static final String SUCCESS_CODE = "200 OK";
    private  static final String OK = "OK";
    private  static final String LOCATOR = "BURGER2";


    private static final CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();


    @Mock
    ReservationService reservationService;

    @InjectMocks
    ReservationController reservationController;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        CREATE_RESERVATION_REST.setDate(DATE);
        CREATE_RESERVATION_REST.setTurnId(TURN_ID);
        CREATE_RESERVATION_REST.setPerson(PERSON);
        CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);

        Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
    }

    @Test
    public void createReservationTest() throws BookingException{
        BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
        assertEquals( SUCCESS_STATUS, response.getStatus());
        assertEquals(SUCCESS_CODE, response.getCode());
        assertEquals(OK, response.getMessage());
        assertEquals(LOCATOR, response.getData());
    }

}

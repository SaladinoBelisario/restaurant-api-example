package com.boot.bookingrestaurantapi.controller;

import com.boot.bookingrestaurantapi.controllers.CancelReservationController;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.CancelReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CancelReservationControllerTest {

    private  static final String SUCCESS_STATUS = "Succes";
    private  static final String SUCCESS_CODE = "200 OK";
    private  static final String OK = "OK";
    private  static final String LOCATOR = "BURGER7";
    private  static final String RESERVATION_DELETED = "LOCATOR_DELETED";

    @Mock
    CancelReservationService cancelReservationService;

    @InjectMocks
    CancelReservationController cancelReservationController;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void deleteReservationTest() throws BookingException{
        Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);
        final BookingResponse<String> response = cancelReservationController.deleteReservation(LOCATOR);

        assertEquals( SUCCESS_STATUS, response.getStatus());
        assertEquals(SUCCESS_CODE, response.getCode());
        assertEquals(OK, response.getMessage());
        assertEquals(RESERVATION_DELETED, response.getData());
    }
}

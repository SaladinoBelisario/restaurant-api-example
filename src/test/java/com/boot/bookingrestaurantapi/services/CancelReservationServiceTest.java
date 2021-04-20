package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.repositories.ReservationRespository;
import com.boot.bookingrestaurantapi.services.impl.CancelReservationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CancelReservationServiceTest {

    private  static final String SUCCESS_STATUS = "Succes";
    private  static final String SUCCESS_CODE = "200 OK";
    private  static final String OK = "OK";
    private  static final String LOCATOR = "BURGER7";
    private  static final Reservation RESERVATION = new Reservation();
    private  static final String RESERVATION_DELETED = "LOCATOR_DELETED";

    @Mock
    private ReservationRespository reservationRespository;

    @InjectMocks
    private CancelReservationServiceImpl cancelReservationService;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void deleteReservationOk() throws BookingException{
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));
        Mockito.when(reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));

        assertEquals(RESERVATION_DELETED, cancelReservationService.deleteReservation(LOCATOR));
    }

    @Test (expected = BookingException.class)
    public void deleteReservationNotFoundError() throws BookingException{
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.empty());
        Mockito.when(reservationRespository.deleteByLocator(LOCATOR)).thenReturn(Optional.of(RESERVATION));

        cancelReservationService.deleteReservation(LOCATOR);
        fail();
    }

    @Test (expected = BookingException.class)
    public void deleteReservationInternalServerError() throws BookingException{
        Mockito.when(reservationRespository.findByLocator(LOCATOR)).thenReturn(Optional.empty());
        Mockito.when(reservationRespository.deleteByLocator(LOCATOR)).thenThrow(BookingException.class);

        cancelReservationService.deleteReservation(LOCATOR);
        fail();
    }
}

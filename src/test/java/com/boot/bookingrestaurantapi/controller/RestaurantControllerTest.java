package com.boot.bookingrestaurantapi.controller;

import com.boot.bookingrestaurantapi.controllers.RestaurantController;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.jsons.TurnRest;
import com.boot.bookingrestaurantapi.responses.BookingResponse;
import com.boot.bookingrestaurantapi.services.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestaurantControllerTest {

    private static final Long RESTAURANT_ID = 1L;
    private  static final String NAME = "BURGER RESTAURANT";
    private  static final String DESCRIPTION = "We sell burgers";
    private  static final String ADDRESS = "200 St. Boulevard";
    private  static final String IMAGE = "www.image.com";
    private  static final String SUCCESS_STATUS = "Succes";
    private  static final String SUCCESS_CODE = "200 OK";
    private  static final String OK = "OK";

    public static final RestaurantRest RESTAURANT_REST = new RestaurantRest();
    public static final List<TurnRest> TURN_LIST = new ArrayList<>();

    @Mock
    RestaurantService restaurantService;

    @InjectMocks
    RestaurantController restaurantController;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

        RESTAURANT_REST.setName(NAME);
        RESTAURANT_REST.setId(RESTAURANT_ID);
        RESTAURANT_REST.setDescription(DESCRIPTION);
        RESTAURANT_REST.setAddress(ADDRESS);
        RESTAURANT_REST.setImage(IMAGE);
        RESTAURANT_REST.setTurns(TURN_LIST);

        Mockito.when(restaurantService.getRestaurantById(RESTAURANT_ID)).thenReturn(RESTAURANT_REST);
    }

    @Test
    public void getRestaurantByIdTest() throws BookingException{
        final BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
        assertEquals( SUCCESS_STATUS, response.getStatus());
        assertEquals(SUCCESS_CODE, response.getCode());
        assertEquals(OK, response.getMessage());
        assertEquals(RESTAURANT_REST, response.getData());
    }
}

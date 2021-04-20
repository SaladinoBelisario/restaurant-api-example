package com.boot.bookingrestaurantapi.services;

import com.boot.bookingrestaurantapi.entities.Board;
import com.boot.bookingrestaurantapi.entities.Reservation;
import com.boot.bookingrestaurantapi.entities.Restaurant;
import com.boot.bookingrestaurantapi.entities.Turn;
import com.boot.bookingrestaurantapi.exceptions.BookingException;
import com.boot.bookingrestaurantapi.jsons.RestaurantRest;
import com.boot.bookingrestaurantapi.repositories.RestaurantRepository;
import com.boot.bookingrestaurantapi.services.impl.RestaurantServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantServiceTest {

    private static final Long RESTAURANT_ID = 1L;
    private  static final String NAME = "BURGER RESTAURANT";
    private  static final String DESCRIPTION = "We sell burgers";
    private  static final String ADDRESS = "200 St. Boulevard";
    private  static final String IMAGE = "www.image.com";

    public static final Restaurant RESTAURANT = new Restaurant();

    public static final List<Turn> TURN_LIST = new ArrayList<>();
    public static final List<Board> BOARDS_LIST = new ArrayList<>();
    public static final List<Reservation> RESERVATIONS_LIST = new ArrayList<>();

    @Mock
    RestaurantRepository restaurantRepository;

    @InjectMocks
    RestaurantServiceImpl restaurantService;

    @Before
    public void init() throws BookingException {
        MockitoAnnotations.initMocks(this);

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
    public void getRestaurantByIdTest() throws BookingException{
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
        assertNotNull(restaurantService.getRestaurantById(RESTAURANT_ID));
    }

    @Test (expected = BookingException.class)
    public void getRestaurantByIdTestError() throws BookingException{
        Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.empty());
        restaurantService.getRestaurantById(RESTAURANT_ID);
        fail();
    }

    @Test
    public void getRestaurantsTest() throws BookingException{
        final Restaurant restaurant = new Restaurant();
        Mockito.when(restaurantRepository.findAll()).thenReturn(Arrays.asList(restaurant));
        final List<RestaurantRest> response = restaurantService.getRestaurants();
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(1, response.size());
    }

}

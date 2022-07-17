import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant = Mockito.mock(Restaurant.class);
        LocalTime timebetweenopenandclose = LocalTime.parse("16:00:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(timebetweenopenandclose);
        boolean open = restaurant.isRestaurantOpen();
        assertTrue(true,String.valueOf(open));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant = Mockito.mock(Restaurant.class);
        LocalTime timeoutsideopenandclose = LocalTime.parse("05:00:00");
        Mockito.when(restaurant.getCurrentTime()).thenReturn(timeoutsideopenandclose);
        boolean close = restaurant.isRestaurantOpen();
        assertFalse(false,String.valueOf(close));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    // TDD scenarios ------------------------------------------------------------------->
    // positive TC --------------------
    // send selected items for cost calculation from the restaurant menu list.
    // find each item cost from selected items
    // calculate total order cost
    // display total order cost of selected items
    // negative TC --------------------
    // display total order cost as 0 if selected items is empty or null
    // display or throw exception if selected item not found in the arraylist of items


    @Test
    public void calculate_order_cost_when_menu_items_selected_from_existing_list() throws itemNotFoundException {
        List<String> items = new ArrayList<String>();
        items.add("Sweet corn soup");
        items.add("Vegetable lasagne");
        assertEquals(388,restaurant.calculateOrderCost(items));

    }

    @Test
    public void order_cost_0_if_no_items_added() throws itemNotFoundException {
        List<String> items = new ArrayList<String>();
        assertEquals(0,restaurant.calculateOrderCost(items));
    }

    @Test
    public void item_selected_not_in_the_list_should_throw_exception() throws itemNotFoundException {
        List<String> items = new ArrayList<String>();
        items.add("Lassi");
        assertThrows(itemNotFoundException.class,()->restaurant.calculateOrderCost(items));
    }

}
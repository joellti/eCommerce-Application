package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;


public class CartControllerTest {

    private CartController cartController;
    private UserRepository userRepository = mock(UserRepository.class);
    private ItemRepository itemRepository = mock(ItemRepository.class);
    private CartRepository cartRepository = mock(CartRepository.class);

    @Before
    public void setUp() {
        cartController = new CartController();
        TestUtils.injectObjects(cartController, "userRepository", userRepository);
        TestUtils.injectObjects(cartController, "itemRepository", itemRepository);
        TestUtils.injectObjects(cartController, "cartRepository", cartRepository);
    }

    @Test
    public void addToCart() {
        ModifyCartRequest r = new ModifyCartRequest();
        r.setUsername("test");
        r.setItemId(1);
        r.setQuantity(2);

        User userMock = new User();
        Cart cartMock = new Cart();
        cartMock.setItems(new ArrayList<>());
        userMock.setCart(cartMock);

        when(userRepository.findByUsername("test")).thenReturn(userMock);

        Item itemMock = new Item();
        itemMock.setName("testItem");
        itemMock.setPrice(BigDecimal.valueOf(10));
        Optional<Item> itemMockOpt = Optional.of(itemMock);

        when(itemRepository.findById(any())).thenReturn(itemMockOpt);

        final ResponseEntity<Cart> response = cartController.addTocart(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    public void removeFromCart() {

        ModifyCartRequest r = new ModifyCartRequest();
        r.setUsername("test");
        r.setItemId(1);
        r.setQuantity(2);

        User userMock = new User();
        Cart cartMock = new Cart();
        cartMock.setItems(new ArrayList<>());
        userMock.setCart(cartMock);

        when(userRepository.findByUsername("test")).thenReturn(userMock);

        Item itemMock = new Item();
        itemMock.setName("testItem");
        itemMock.setPrice(BigDecimal.valueOf(10));
        Optional<Item> itemMockOpt = Optional.of(itemMock);

        when(itemRepository.findById(any())).thenReturn(itemMockOpt);

        final ResponseEntity<Cart> response = cartController.removeFromcart(r);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

    }

}

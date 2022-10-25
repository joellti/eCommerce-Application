package com.example.demo.controllers;

import com.example.demo.TestUtils;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemControllerTest {
    private ItemController itemController;
    private ItemRepository itemRepository = mock(ItemRepository.class);

    @Before
    public void setUp() {
        itemController = new ItemController();
        TestUtils.injectObjects(itemController, "itemRepository", itemRepository);
    }

    @Test
    public void get_items() throws Exception {

        List<Item> items = new ArrayList<>();
        when(itemRepository.findAll()).thenReturn(items);

        final ResponseEntity<List<Item>> response = itemController.getItems();

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    public void get_item_by_id() throws Exception {

        Item itemMock = new Item();
        Optional<Item> itemMockOpt = Optional.of(itemMock);

        when(itemRepository.findById(any())).thenReturn(itemMockOpt);
        final ResponseEntity<Item> response = itemController.getItemById(Long.valueOf(1));

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    public void get_item_by_name() throws Exception {

        List<Item> itemsMock = new ArrayList<>();
        Item e = new Item();
        itemsMock.add(e);

        when(itemRepository.findByName(any())).thenReturn(itemsMock);
        final ResponseEntity<List<Item>> response = itemController.getItemsByName("");

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());

    }

}

package com.vmware.retail.controller;

import com.vmware.retail.domain.CustomerFavorites;
import com.vmware.retail.domain.Product;
import com.vmware.retail.domain.ProductQuantity;
import com.vmware.retail.repository.CustomerFavoriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerFavoritesControllerTest
{
    @Mock
    private CustomerFavoriteRepository repository;

    @Mock
    private Mono<CustomerFavorites> mono;

    private long expectedQuantity = 54L;
    private TreeSet<ProductQuantity> expectedProductQuantities = new TreeSet<ProductQuantity>();
    private CustomerFavorites expectedCustomerFavorites = new CustomerFavorites("user",expectedProductQuantities);


    private CustomerFavoritesController subject;

    @BeforeEach
    void setUp()
    {
        expectedProductQuantities.add(new ProductQuantity(
                new Product("p1","pname"),
                expectedQuantity));

        subject = new CustomerFavoritesController(repository);
    }

    @Test
    void given_userId_when_findById_then_return_fromRepository()
    {

        when(repository.findById(anyString())).thenReturn(mono);
        when(mono.block()).thenReturn(expectedCustomerFavorites);

        var actual = subject.findById("user");

        assertEquals(expectedCustomerFavorites,actual.block());
    }

    @Test
    void given_favorites_whenSave_saveOnRepository()
    {
        subject.saveCustomerFavorites(expectedCustomerFavorites);
        verify(repository).saveCustomerFavorites(any());
    }
}
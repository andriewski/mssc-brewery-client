package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient client;

    @Test
    void getBeerById() {
        BeerDto dto = client.getBeerById(UUID.randomUUID());

        assertNotNull(dto);
    }

    @Test
    void saveNewBeer() {
        //given
        BeerDto dto = BeerDto.builder().beerName("New Beer").build();
        URI uri = client.saveNewBeer(dto);

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void updateBeer() {
        //given
        BeerDto dto = BeerDto.builder().beerName("New Beer").build();
        client.updateBeer(UUID.randomUUID(), dto);
    }

    @Test
    void deleteBeer() {
        //given
        client.deleteBeer(UUID.randomUUID());
    }
}
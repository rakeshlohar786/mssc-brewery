package guru.springframework.msscbrewery.services.v2;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;

public interface BeerServiceV2 {

	BeerDtoV2 getBeerById(UUID beerId);
	
	BeerDtoV2 saveNewBeer(BeerDtoV2 beerDtoV2);
	
	void updateBear(UUID beerId, BeerDtoV2 beerDtoV2);
	
	void deleteById(UUID beerId);
}
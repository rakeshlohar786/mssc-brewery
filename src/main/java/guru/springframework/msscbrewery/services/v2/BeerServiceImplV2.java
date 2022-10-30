package guru.springframework.msscbrewery.services.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImplV2 implements BeerServiceV2 {

	@Override
	public BeerDtoV2 getBeerById(UUID beerId) {
		
		return null;
	}

	@Override
	public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDtoV2) {
		
		return null;
	}

	@Override
	public void updateBear(UUID beerId, BeerDtoV2 beerDtoV2) {
		//todo impl - would add a real impl to update beer
		
	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("Deleting a bear");
	}

	
	
	
}

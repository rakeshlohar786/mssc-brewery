package guru.springframework.msscbrewery.web.controller.v2;


import java.util.UUID;

import javax.validation.Constraint;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import java.util.*;

import guru.springframework.msscbrewery.services.v2.BeerServiceV2;
import guru.springframework.msscbrewery.web.model.v2.BeerDtoV2;


@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

	private final BeerServiceV2 beerServiceV2;
	
	public BeerControllerV2(BeerServiceV2 beerServiceV2) {
		this.beerServiceV2 = beerServiceV2;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){
		
		return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity handlePost(@Valid @RequestBody BeerDtoV2 beerDtoV2) {
		
		BeerDtoV2 saveDto = beerServiceV2.saveNewBeer(beerDtoV2);
		HttpHeaders headers = new HttpHeaders();
		//to do hostname to url'
		headers.add("Location", "/api/v1/beer"+saveDto.getId().toString());
		
		return new ResponseEntity(headers,HttpStatus.CREATED);
	}
	
	@PutMapping({"/{beerId}"})
	public ResponseEntity handleUpdate(@PathVariable("beerId") UUID beerId, 
		@Valid	@RequestBody BeerDtoV2 beerDtoV2) {
		beerServiceV2.updateBear(beerId, beerDtoV2);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
			
		beerServiceV2.deleteById(beerId);
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e){
		
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
		
		e.getConstraintViolations().forEach(
				constraintViolation ->{
				  errors.add(constraintViolation.getPropertyPath()+ ": "+ constraintViolation.getMessage());
				});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	
}

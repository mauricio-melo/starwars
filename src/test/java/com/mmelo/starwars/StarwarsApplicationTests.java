package com.mmelo.starwars;

import com.mmelo.starwars.service.SwapiClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StarwarsApplicationTests {

	@Autowired
	private SwapiClientService swapiClientService;

	@Test
	void contextLoads() {
		swapiClientService.getPlanets();
	}

}

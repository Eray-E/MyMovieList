package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "omdb.api.key=testkey",
        "OMDB_API_URL=https://www.omdbapi.com/"})
class DemoApplicationTests {


	@Test
	void contextLoads() {
	}

}

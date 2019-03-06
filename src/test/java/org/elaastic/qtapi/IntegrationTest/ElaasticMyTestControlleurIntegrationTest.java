package org.elaastic.qtapi.IntegrationTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElaasticMyTestControlleurIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testFindAllProjectsWithEnterprises() {

        // when requesting all projects
        String body = this.restTemplate.getForObject("/questions", String.class);

        System.out.println(body);

    }

}

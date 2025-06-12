package com.portfolio.community;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "storage.path=/resources"
})
class CommunityApplicationTests {

    @Test
    void contextLoads() {
    }

}

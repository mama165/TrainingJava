package fr.coding.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import redis.clients.jedis.Jedis;

@Testcontainers
public class OperationRepositoryImplTest {
    @Container
    public GenericContainer redis = new GenericContainer("redis:latest")
            .withExposedPorts(6379);


    @BeforeEach
    public void setUp() throws Exception {
        Jedis jedis = new Jedis(redis.getContainerIpAddress(), redis.getMappedPort(6379));
        jedis.set("maison", "brest");
    }
}

package client.user;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarTest {

    Car car;

    @BeforeEach
    void setUp() {
        car = new Car(0, 0);
    }

    @Test
    void getCarName() {
        car.setCarType(0);
        Assert.assertEquals("Compact Car", car.getCarName());
        car.setCarName("Compact Car");
        Assert.assertEquals("Compact Car", car.getCarName());
    }

    @Test
    void getEmissionName() {
        car.setEmissionName("Electric");
        Assert.assertEquals("Electric", car.getEmissionName());
        car.setEmissionType(0);
        Assert.assertEquals("Gasoline", car.getEmissionName());
    }

    @Test
    void getEmissionType() {
        car.setEmissionName("Electric");
        Assert.assertEquals(3, car.getEmissionType());
        car.setEmissionType(0);
        Assert.assertEquals(0, car.getEmissionType());
    }


    @Test
    void setCarType() {
        car.setCarType(4);
        Assert.assertEquals(4, car.getCarType());
        car.setCarName("SUV");
        Assert.assertEquals(4, car.getCarType());
    }

    @Test
    void setEmisionType() {
        car.setEmissionType(0);
        Assert.assertEquals("Gasoline", car.getEmissionName());
        car.setEmissionName("Diesel");
        Assert.assertEquals(1, car.getEmissionType());
    }

}

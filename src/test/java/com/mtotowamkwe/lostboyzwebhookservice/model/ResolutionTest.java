package com.mtotowamkwe.lostboyzwebhookservice.model;

import com.mtotowamkwe.lostboyzwebhookservice.util.WebhookServiceConstants;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mock;
import org.springframework.util.SerializationUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResolutionTest {

    @Mock
    private WebhookServiceConstants CONSTANTS;

    private Resolution resolution;

    @Before
    public void setUp() {
        resolution = new Resolution();
        resolution.setHeight(1137);
        resolution.setUrl(CONSTANTS.RESOLUTION_URL_4);
        resolution.setWidth(640);
    }

    @Test
    public void testIfResolutionIsSerializable() {
        final byte[] serializedResolution = SerializationUtils.serialize(resolution);
        final Resolution deserializedResolution = (Resolution) SerializationUtils.deserialize(serializedResolution);
        assertEquals(resolution, deserializedResolution);
    }

    @Test
    public void testGettersAndSettersUsingMeanBean() {
        new BeanTester().testBean(Resolution.class);
    }

    @Test
    public void testEqualsAndHashCodeWithEqualsVerifier() {
        EqualsVerifier.simple().forClass(Resolution.class).verify();
    }
}
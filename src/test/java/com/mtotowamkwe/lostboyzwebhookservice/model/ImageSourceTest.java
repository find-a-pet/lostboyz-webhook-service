package com.mtotowamkwe.lostboyzwebhookservice.model;

import com.mtotowamkwe.lostboyzwebhookservice.util.WebhookServiceConstants;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mock;
import org.springframework.util.SerializationUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageSourceTest {

    @Mock
    private WebhookServiceConstants CONSTANTS;

    private ImageSource imageSource;

    @Before
    public void setUp() {
        imageSource = new ImageSource();
        imageSource.setHeight(1600);
        imageSource.setUrl(CONSTANTS.IMG_SRC_URL);
        imageSource.setWidth(900);
    }

    @Test
    public void testIfImageSourceIsSerializable() {
        final byte[] serializedImageSource = SerializationUtils.serialize(imageSource);
        final ImageSource deserializedImageSource = (ImageSource) SerializationUtils.deserialize(serializedImageSource);
        assertEquals(imageSource, deserializedImageSource);
    }

    @Test
    public void testGettersAndSettersUsingMeanBean() {
        new BeanTester().testBean(ImageSource.class);
    }

    @Test
    public void testEqualsAndHashCodeWithEqualsVerifier() {
        EqualsVerifier.simple().forClass(ImageSource.class).verify();
    }
}
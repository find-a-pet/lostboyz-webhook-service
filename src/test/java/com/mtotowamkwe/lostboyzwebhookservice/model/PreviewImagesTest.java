package com.mtotowamkwe.lostboyzwebhookservice.model;

import com.mtotowamkwe.lostboyzwebhookservice.util.WebhookServiceConstants;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.mockito.Mock;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreviewImagesTest {

    @Mock
    private WebhookServiceConstants CONSTANTS;

    @Mock
    private ImageSource imageSource;

    @Mock
    private PreviewImages previewImage;

    @Before
    public void setUp() {

        imageSource = new ImageSource();
        imageSource.setHeight(1600);
        imageSource.setUrl(CONSTANTS.IMG_SRC_URL);
        imageSource.setWidth(900);

        ArrayList<Resolution> resolutions = new ArrayList<Resolution>(4);

        Resolution first = new Resolution();
        first.setHeight(192);
        first.setUrl(CONSTANTS.RESOLUTION_URL_1);
        first.setWidth(108);
        resolutions.add(first);

        Resolution second = new Resolution();
        first.setHeight(384);
        first.setUrl(CONSTANTS.RESOLUTION_URL_2);
        first.setWidth(216);
        resolutions.add(second);

        Resolution third = new Resolution();
        first.setHeight(568);
        first.setUrl(CONSTANTS.RESOLUTION_URL_3);
        first.setWidth(320);
        resolutions.add(third);

        Resolution fourth = new Resolution();
        first.setHeight(1137);
        first.setUrl(CONSTANTS.RESOLUTION_URL_4);
        first.setWidth(640);
        resolutions.add(fourth);

        previewImage = new PreviewImages();
        previewImage.setId(CONSTANTS.PREVIEW_IMG_ID);
        previewImage.setResolutions(resolutions);
        previewImage.setSource(imageSource);
        previewImage.setVariants(new Object());

        ArrayList<PreviewImages> images = new ArrayList<PreviewImages>(1);
        images.add(previewImage);
    }

    @Test
    public void testIfPreviewImagesIsSerializable() {
        final byte[] serializedPreviewImages = SerializationUtils.serialize(previewImage);
        final PreviewImages deserializedPreviewImages = (PreviewImages) SerializationUtils.deserialize(serializedPreviewImages);
        assertEquals(previewImage, deserializedPreviewImages);
    }

    @Test
    public void testGettersAndSettersUsingMeanBean() {
        new BeanTester().testBean(PreviewImages.class);
    }

    @Test
    public void testEqualsAndHashCodeWithEqualsVerifier() {
        EqualsVerifier.simple().forClass(PreviewImages.class).verify();
    }
}
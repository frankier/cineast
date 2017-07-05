package org.vitrivr.cineast.core.util.web;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author rgasser
 * @version 1.0
 * @created 10.03.17
 */
public class DataURLParser {

    protected static final Logger LOGGER = LogManager.getLogger();

    protected DataURLParser() {}

    /**
     * Converts a Base64 data URL to a byte array and returns it.
     *
     * @param dataUrl String containing the data url.
     * @return Byte array of the data.
     */
    public static byte[] dataURLtoByteArray(String dataUrl) {
        dataUrl = dataUrl.replace(' ', '+');

		/* Check if string is actually a valid data URL. */
        if (!dataUrl.startsWith("data:")) {
            LOGGER.warn("This is not a valid data URL.");
            return null;
        }

		/* Convert and return byte array. */
        int headerLength = dataUrl.indexOf(',');
        String base46data = dataUrl.substring(headerLength + 1);
        return Base64.decodeBase64(base46data);
    }

    /**
     * Converts a Base64 data URL to a byte array and returns it. Only URLs that contain the
     * verify substring at position five of the String will be converted!
     *
     * @param dataUrl String containing the data url.
     * @param verify Substring that must be contained at position 5 in order for the data to be converted.
     * @return Byte array of the data.
     */
    public static byte[] dataURLtoByteArray(String dataUrl, String verify) {
        if (dataUrl == null) return null;
        dataUrl = dataUrl.replace(' ', '+');

        /* Check data URL. */
        if (!isValidDataUrl(dataUrl, verify)) return null;

		/* Convert and return byte array. */
        int headerLength = dataUrl.indexOf(',');
        String base46data = dataUrl.substring(headerLength + 1);
        return Base64.decodeBase64(base46data);
    }


    /**
     * Checks if the provided data URL is actually a valid data URL. Returns true, if so and false
     * otherwise.
     *
     * @param dataUrl Data URL that should be checked.
     * @param verify Substring that must be contained at position 5 in order for the data to be valid.
     * @return True if valid and false otherwise.
     */
    public static boolean isValidDataUrl(String dataUrl, String verify) {
        /* Check if string is actually a valid data URL. */
        if (!dataUrl.startsWith("data:")) {
            LOGGER.warn("This is not a valid data URL.");
            return false;
        }

        /* Check if data URL is of supported type. */
        if (!dataUrl.substring(5, 5 + verify.length()).equals(verify)) {
            LOGGER.warn("Data URL does not have a supported type.");
            return false;
        }

        return true;
    }
}
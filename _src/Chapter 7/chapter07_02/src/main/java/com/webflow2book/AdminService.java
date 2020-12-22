package com.webflow2book;

import java.util.List;

/**
 * Service interface that defines two methods that
 * return a list of String-objects.
 * @author Sven
 */
public interface AdminService {

    /**
     * Returns a list of secret Strings
     * @return A list of Strings
     */
    List<String> getSecretStrings();

    /**
     * Returns a list of public Strings
     * @return A list of Strings
     */
    List<String> getPublicStrings();
}

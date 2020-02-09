package com.bnkbl;

public interface UserRepository {
    /**
     * Create a user with the given name and age
     * @param name the user's name
     * @param age the user's age in whole years
     * @return a uniquely-generated ID for the new user
     */
    String createUser(String name, int age);

    /**
     * Get the name of a user given their ID. Returns the
     * name if found, or throws an exception if not found.
     * @param id the unique ID for the user
     * @return the name of the user with that ID
     */
    String getNameById(String id);
}

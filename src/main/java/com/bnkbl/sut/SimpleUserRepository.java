package com.bnkbl.sut;

import com.bnkbl.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.util.Optional.ofNullable;

public class SimpleUserRepository implements UserRepository {
    private Map<UUID, User> users = new HashMap<>();

    @Override
    public String createUser(String name, int age) {
        UUID newId = UUID.randomUUID();
        users.put(newId, new User(name, age));
        return newId.toString();
    }

    @Override
    public String getNameById(String id) {
        return ofNullable(users.get(UUID.fromString(id)))
                .map(user -> user.name)
                .orElseThrow(UserNotFound::new);
    }

    private class User {
        final String name;
        final int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static class UserNotFound extends RuntimeException {}
}

package com.tanukode.crudapp;

import org.springframework.data.annotation.Id;

public record User (@Id String uid, String name, String email, String password){

    @Override
    public String toString() {
        return String.format(
                "User[uid='%s', name='%s', email='%s', password='%s']",
                uid, name, email, password);
    }
}

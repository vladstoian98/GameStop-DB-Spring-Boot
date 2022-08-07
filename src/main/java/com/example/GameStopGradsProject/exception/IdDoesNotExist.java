package com.example.GameStopGradsProject.exception;

public class IdDoesNotExist extends RuntimeException {

    public IdDoesNotExist(String className, long id) {

        super("In the " + className + " table this id does not exist: " + id + "." );
    }

}

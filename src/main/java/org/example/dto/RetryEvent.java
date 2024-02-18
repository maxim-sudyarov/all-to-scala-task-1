package org.example.dto;

public record RetryEvent(Address recipient, Payload payload) {}

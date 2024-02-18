package org.example.service;

import java.time.Duration;

public interface Handler {
    Duration timeout();

    void performOperation();
}

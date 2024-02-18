package org.example.service;

import org.example.dto.Address;
import org.example.dto.Event;
import org.example.dto.Payload;
import org.example.dto.Result;

public interface Client {
    //блокирующий метод для чтения данных
    Event readData();

    //блокирующий метод отправки данных
    Result sendData(Address dest, Payload payload);
}

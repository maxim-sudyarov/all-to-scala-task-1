package org.example.dto;

import java.util.List;

public record Event(List<Address> recipients, Payload payload) {}

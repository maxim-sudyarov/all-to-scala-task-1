package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientImpl implements Client {
    Random random = new Random();
    @Override
    public Event readData() {
        Event event = null;
        if (random.nextBoolean()) {
            int recipientsCount = random.nextInt(5);
            List<Address> recipients = new ArrayList<>();
            for (int i = 0; i < recipientsCount + 1; ++i) {
                recipients.add(new Address("", ""));
            }
            event = new Event(recipients, new Payload("", new byte[0]));
        }
        return event;
    }

    @Override
    public Result sendData(Address dest, Payload payload) {
        return random.nextBoolean()
                ? Result.ACCEPTED
                : Result.REJECTED;
    }
}

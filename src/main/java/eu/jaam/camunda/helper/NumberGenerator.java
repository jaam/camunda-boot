package eu.jaam.camunda.helper;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGenerator {

    public int generateNumber() {
        int generatedNumber = new Random().nextInt(5);
        System.out.println("Generated number " + generatedNumber);
        return generatedNumber;
    }

    public int flakyGenerateNumber() {
        int generatedNumber = new Random().nextInt(5);
        if (true) {
            throw new RuntimeException("Oh shooot");
        }

        System.out.println("Generated number " + generatedNumber);
        return generatedNumber;
    }

}

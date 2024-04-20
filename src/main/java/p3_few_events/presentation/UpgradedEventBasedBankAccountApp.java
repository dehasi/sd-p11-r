package p3_few_events.presentation;

import p3_few_events.application.ApplicationConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class UpgradedEventBasedBankAccountApp {
    public static void main(String[] args) throws IOException {
        var userInputService = ApplicationConfiguration.userInputService();

        var reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("> ");
            String input = reader.readLine();

            if (input.startsWith("exit")) {
                break;
            }

            try {
                String output = userInputService.exec(input);
                System.out.println(output);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

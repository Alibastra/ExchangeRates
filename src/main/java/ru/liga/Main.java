package ru.liga;

import ru.liga.service.ProgramInterface;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        ProgramInterface programInterface = new ProgramInterface();
        programInterface.startInterface();
    }
}


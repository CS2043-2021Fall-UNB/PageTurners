package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import pageturners.database.DatabaseManager;

public class DatabaseManagerTest {
    private DatabaseManager databaseManager;

    @BeforeEach
    void setUp() {
        databaseManager = new DatabaseManager();
    }
}

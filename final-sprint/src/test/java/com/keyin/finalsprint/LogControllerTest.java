package com.keyin.finalsprint;

import com.keyin.finalsprint.Controller.LogController;
import com.keyin.finalsprint.Entity.LogEntity;
import com.keyin.finalsprint.Repository.LogRepository;
import com.keyin.finalsprint.Service.LogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LogControllerTest {

    @Mock
    private LogService logService;

    @Mock
    private LogRepository logRepository;

    @InjectMocks
    private LogController logController;

    @Test
    void testGetAllLogs() {
        // Mock data
        List<LogEntity> expectedLogs = new ArrayList<>();
        expectedLogs.add(new LogEntity(1L, "user_id_1", "query_1", LocalDateTime.now()));
        expectedLogs.add(new LogEntity(2L, "user_id_2", "query_2", LocalDateTime.now()));

        // Mock logService.getAllLogs()
        when(logService.getAllLogs()).thenReturn(expectedLogs);

        // Call the controller method
        List<LogEntity> actualLogs = logController.getAllLogs();

        // Assertions
        assertEquals(expectedLogs.size(), actualLogs.size());
        for (int i = 0; i < expectedLogs.size(); i++) {
            assertEquals(expectedLogs.get(i), actualLogs.get(i));
        }
        verify(logService).getAllLogs();
    }
}

package edu.matc.persistence;

import edu.matc.entity.Holiday;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the HolidayDao.
 *
 * @author JaQuan Weaver
 */
class HolidayDaoTest {

    /**
     * Verifies holidays are returned from the REST service.
     *
     * @throws Exception if the service call fails
     */
    @Test
    void getHolidaysSuccess() throws Exception {

        HolidayDao dao = new HolidayDao();

        List<Holiday> holidays = dao.getHolidays(2026);

        assertNotNull(holidays);
        assertFalse(holidays.isEmpty());
        assertNotNull(holidays.get(0).getName());
        assertNotNull(holidays.get(0).getDate());
    }
}
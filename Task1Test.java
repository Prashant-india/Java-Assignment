import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    private LoanAccountService service;

    @BeforeEach
    void setUp() {
        service = new LoanAccountService();
    }

    @Test
    void testGetOverdueLoans_FixesAllDefects() {
        Calendar past = Calendar.getInstance();
        past.add(Calendar.DAY_OF_YEAR, -10);

        // 1. Test Fix for NullPointerException (result list initialization)
        // Original code would throw NPE here because result was null.
        List<LoanAccount> emptyList = new ArrayList<>();
        assertDoesNotThrow(() -> service.getOverdueLoans(emptyList),
                "Should not throw NPE when result list is initialized.");

        // 2. Test Fix for NullPointerException (restructured accounts/null dates)
        // Original code would throw NPE calling .before() on a null date.
        LoanAccount restructuredAcc = new LoanAccount("ACC_001", 100.0, null);
        List<LoanAccount> listWithNullDate = Collections.singletonList(restructuredAcc);

        List<LoanAccount> resultNullDate = service.getOverdueLoans(listWithNullDate);
        assertTrue(resultNullDate.isEmpty(), "Accounts with null due dates should be skipped, not crash.");

        // 3. Test Fix for Zero Balance logic
        // Ensuring accounts with 0 balance are NOT included even if overdue.
        LoanAccount zeroBalAcc = new LoanAccount("ACC_002", 0.0, past.getTime());
        List<LoanAccount> listWithZeroBal = Collections.singletonList(zeroBalAcc);

        List<LoanAccount> resultZeroBal = service.getOverdueLoans(listWithZeroBal);
        assertEquals(0, resultZeroBal.size(), "Accounts with zero balance should not be returned.");

        // 4. Test Valid Overdue Case
        LoanAccount overdueAcc = new LoanAccount("ACC_003", 500.0, past.getTime());
        List<LoanAccount> validList = Collections.singletonList(overdueAcc);

        List<LoanAccount> finalResult = service.getOverdueLoans(validList);
        assertEquals(1, finalResult.size(), "Should correctly identify a valid overdue account.");
    }
}

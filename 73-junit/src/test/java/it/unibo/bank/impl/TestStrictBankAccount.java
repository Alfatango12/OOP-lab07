package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import javax.sql.RowSetListener;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private static final int AMOUNT = 100;
    private static final int NEG_AMOUNT = -100;
    private static final int transactionCount = 1;

    private AccountHolder mRossi;
    private BankAccount bankAccount;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 0);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), AMOUNT);
        assertEquals(AMOUNT, bankAccount.getBalance());
        assertEquals(transactionCount, bankAccount.getTransactionsCount());

        bankAccount.chargeManagementFees(0);
        assertEquals(AMOUNT - SimpleBankAccount.MANAGEMENT_FEE - (transactionCount * StrictBankAccount.TRANSACTION_FEE), bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try {
            bankAccount.withdraw(mRossi.getUserID(), NEG_AMOUNT);
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly managerd a negative withdrawal amount -> exception thrown");
        }
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        try { 
            bankAccount.withdraw(mRossi.getUserID(), bankAccount.getBalance() + 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly managed a wrong withdrawal amount -> exception thrown");
        }

    }
}

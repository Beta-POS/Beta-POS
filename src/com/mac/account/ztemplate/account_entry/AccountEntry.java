/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.account.ztemplate.account_entry;

import com.mac.account.ztemplate.account_entry.object.Entry;
import com.mac.account.ztemplate.account_entry.object.AccountEntryTransaction;
import com.mac.af.component.model.table.CTableColumn;
import com.mac.af.component.model.table.CTableModel;
import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.templates.registration.AbstractRegistrationForm;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SMTK
 */
public abstract class AccountEntry extends AbstractRegistrationForm {

    protected abstract String referenceGeneratorPrefix();

    public abstract String getTransactionName();

    public abstract String getTransactionTypeCode();

    protected abstract String getAccountTransactionTypeCode();

    protected abstract boolean isAccountCreditDebitEqualNeeded();

    public String getAccountEntryType() {
        return ACCOUNT_ENTRY_CREDIT_OR_DEBIT;
    }

    @Override
    public AbstractObjectCreator getObjectCreator() {

        return new PCAccountEntry() {
            @Override
            protected String ReferenceGeneratorPrefix() {
                return referenceGeneratorPrefix();
            }

            @Override
            protected void addJournelEntry(Entry journalEntry) {
                AccountEntry.this.addJournelEntry(journalEntry);
            }

            @Override
            protected String getTransactionTypeCode() {
                return AccountEntry.this.getTransactionTypeCode();
            }

            @Override
            public List getAccounts() {
                return AccountEntry.this.getAccounts();
            }

            @Override
            public Double getTotalCredit() {
                return AccountEntry.this.getCredit();
            }

            @Override
            public Double getTotalDebit() {
                return AccountEntry.this.getDebit();
            }

            @Override
            protected String getEntryType() {
                return AccountEntry.this.getAccountEntryType();
            }
        };
    }

    @Override
    public Class getObjectClass() {
        return AccountEntryTransaction.class;
    }

    @Override
    public CTableModel getTableModel() {
        //CREATE DEFAULT CTABLE MODEL TO HERE
        return new CTableModel(new CTableColumn[]{
            new CTableColumn("Account", new String[]{"account"}),
            new CTableColumn("Description", new String[]{"description"}),
            new CTableColumn("Credit Amount", new String[]{"creditAmount"}),
            new CTableColumn("Debit Amount", new String[]{"debitAmount"})
        });

    }

    public int save(AccountEntryTransaction entryTransaction, Collection<Entry> entries) {
        try {
            serAccountEntry.save(entryTransaction, journalEntrys);
            return SAVE_SUCCESS;
        } catch (DatabaseException ex) {
            Logger.getLogger(AccountEntry.class.getName()).log(Level.SEVERE, null, ex);
            return SAVE_FAILED;
        }
    }

    @Override
    protected int save(Object object) throws DatabaseException {
        initService();

        if (isAccountCreditDebitEqualNeeded()
                ? getCredit().doubleValue() == getDebit().doubleValue()
                : true) {

            AccountEntryTransaction transaction = (AccountEntryTransaction) object;
            return save(transaction, journalEntrys);
        } else {
            mOptionPane.showMessageDialog(this, "Credit total should equal to debit total. ", "Account Transaction", mOptionPane.ERROR_MESSAGE);

            return SAVE_FAILED;
        }
    }

    @Override
    protected List getTableData() throws DatabaseException {
        if (journalEntrys != null) {
            return journalEntrys;
        } else {
            return new ArrayList();
        }
    }

    private void addJournelEntry(Entry journalEntry) {
        if (journalEntrys == null) {
            journalEntrys = new ArrayList<>();
        }

        journalEntrys.add(journalEntry);
        refreshTable();
    }

    public SERAccountEntry getService() {
        initService();

        return serAccountEntry;
    }

    private void initService() {
        if (serAccountEntry == null) {
            serAccountEntry = new SERAccountEntry(this) {
                @Override
                protected String getTransactionTypeCode() {
                    return AccountEntry.this.getTransactionTypeCode();
                }

                @Override
                protected String getAccountTransactionTypeCode() {
                    return AccountEntry.this.getAccountTransactionTypeCode();
                }
            };
        }
    }

    private List getAccounts() {
        initService();
        return serAccountEntry.getAccouns();
    }
    private SERAccountEntry serAccountEntry;
    public ArrayList<Entry> journalEntrys;

    private Double getCredit() {
        Double totalCredit = 0.0;
        for (Entry journalEntry : journalEntrys) {
            totalCredit = totalCredit + journalEntry.getCreditAmount();
        }
        return totalCredit;
    }

    private Double getDebit() {
        Double totalDebit = 0.0;
        for (Entry journalEntry : journalEntrys) {
            totalDebit = totalDebit + journalEntry.getDebitAmount();
        }
        return totalDebit;
    }
    //
    public static final String ACCOUNT_ENTRY_CREDIT_ONLY = "CREDIT_ONLY";
    public static final String ACCOUNT_ENTRY_DEBIT_ONLY = "DEBIT_ONLY";
    public static final String ACCOUNT_ENTRY_CREDIT_OR_DEBIT = "CREDIT_OR_DEBIT";
}

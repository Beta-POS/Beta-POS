/*
 *  SphereMainFrame.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Dec 19, 2014, 4:24:33 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package sphere;

import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.environment.cpanel.CPanel;
import com.mac.zresources.SphereResources;
import com.mac.af.core.environment.mainframe.DefaultMainframe;
import com.mac.transaction.item_transaction_new.object.RTransactionType;
import com.mac.transaction.transaction_registration.FormTypes;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author mohan
 */
public class SphereMainFrame extends DefaultMainframe {

    @Override
    protected void createGUI() {
        createRegistration();
        createAccounts();
        createItemTransactions();
        createAccountTransactions();
        createReports();
        createSystem();
    }

    private void createRegistration() {
        addTask("Registration");

        addBand("People", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Client", SphereResources.getImageIconURL(SphereResources.CLIENT), DefaultMainframe.ElementPriority.TOP, com.mac.registration.transactor.REGClient.class, null);
        addButton("Supplier", SphereResources.getImageIconURL(SphereResources.SUPPLIER), DefaultMainframe.ElementPriority.TOP, com.mac.registration.transactor.REGSupplier.class, null);
        addButton("Route", SphereResources.getImageIconURL(SphereResources.ROUTE), DefaultMainframe.ElementPriority.LOW, com.mac.registration.route.REGRoute.class, null);
        addButton("Transactor Category", SphereResources.getImageIconURL(SphereResources.ROUTE), DefaultMainframe.ElementPriority.LOW, com.mac.registration.transactor_category.REGTransactorCategory.class, null);
        addButton("Employee", SphereResources.getImageIconURL(SphereResources.EMPLOYEE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.employee.REGEmployee.class, null);

        addBand("Company", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Company", SphereResources.getImageIconURL(SphereResources.COMPANY), DefaultMainframe.ElementPriority.TOP, com.mac.registration.company.REGCompany.class, null);
        addButton("Branch", SphereResources.getImageIconURL(SphereResources.BRANCH), DefaultMainframe.ElementPriority.TOP, com.mac.registration.branch.REGBranch.class, null);
        addButton("Store", SphereResources.getImageIconURL(SphereResources.STORE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.store.REGStore.class, null);

        addBand("Item", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Item", SphereResources.getImageIconURL(SphereResources.ITEM), DefaultMainframe.ElementPriority.TOP, com.mac.registration.item.REGItem.class, null);
        addButton("Item Price", SphereResources.getImageIconURL(SphereResources.ITEM_PRICE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.item_price_change.REGItemPriceChange.class, null);
        addButton("Department", SphereResources.getImageIconURL(SphereResources.DEPARTMENT), DefaultMainframe.ElementPriority.TOP, com.mac.registration.department.REGDepartment.class, null);
        addButton("Main Category", SphereResources.getImageIconURL(SphereResources.MAIN_CATEGORY), DefaultMainframe.ElementPriority.TOP, com.mac.registration.main_category.REGMainCategory.class, null);
        addButton("Sub Category", SphereResources.getImageIconURL(SphereResources.SUB_CATEGORY), DefaultMainframe.ElementPriority.TOP, com.mac.registration.sub_category.REGSubCategory.class, null);

        addBand("Item Properties", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Brand", SphereResources.getImageIconURL(SphereResources.BRAND), DefaultMainframe.ElementPriority.LOW, com.mac.registration.item_property.REGItemPropertyBrand.class, null);
        addButton("Make", SphereResources.getImageIconURL(SphereResources.MAKE), DefaultMainframe.ElementPriority.LOW, com.mac.registration.item_property.REGItemPropertyMake.class, null);
        addButton("Model", SphereResources.getImageIconURL(SphereResources.MODEL), DefaultMainframe.ElementPriority.LOW, com.mac.registration.item_property.REGItemPropertyModel.class, null);
        addButton("Size", SphereResources.getImageIconURL(SphereResources.SIZE), DefaultMainframe.ElementPriority.LOW, com.mac.registration.item_property.REGItemPropertySize.class, null);
        addButton("Unit", SphereResources.getImageIconURL(SphereResources.UNIT), DefaultMainframe.ElementPriority.LOW, com.mac.registration.item_property.REGItemPropertyUnit.class, null);
        addButton("Generic", SphereResources.getImageIconURL(SphereResources.GENERIC), DefaultMainframe.ElementPriority.LOW, com.mac.registration.item_property.REGItemPropertyGeneric.class, null);
    }

    private void createAccounts() {
        addTask("Account");
        addBand("Account", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Account Registration", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.account.REGAccount.class, null);
        addButton("Account Category", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.account_category.REGAccountCategory.class, null);

        addBand("Bank", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Bank Registration", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.bank.REGBank.class, null);
        addButton("Bank Branch Registration", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), DefaultMainframe.ElementPriority.TOP, com.mac.registration.bank_branch.REGBankBranch.class, null);
        
        
        addBand(" Account Transaction", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        startGroup();
        addButton("Opening Balance", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), ElementPriority.TOP, com.mac.account.opening_balance.OpeningBalance.class, null);
        startGroup();
        addButton("Bank Deposit", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), ElementPriority.TOP, com.mac.account.bank_deposit.BankDeposit.class, null);
        startGroup();
        addButton("General Voucher", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), ElementPriority.TOP, com.mac.account.general_voucher.GeneralVoucher.class, null);

        startGroup();
        addButton("Journal", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE), ElementPriority.TOP, com.mac.account.journal.Journel.class, null);
    }

    private void createItemTransactions() {
        HibernateDatabaseService databaseService = CPanel.GLOBAL.getDatabaseService();
//        try {
            List<RTransactionType> transactionTypes = databaseService.initCriteria(RTransactionType.class).add(Restrictions.eq("formType", FormTypes.ITEM_TRANSACTION)).list();

            if (!transactionTypes.isEmpty()) {
                addTask("Item Transactions");
                addBand("Item Transactions", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));

                for (RTransactionType transactionType : transactionTypes) {
                    if (transactionType.getFormType().equals(FormTypes.ITEM_TRANSACTION)) {
                        addButton(
                                transactionType.getName(),
                                SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE),
                                DefaultMainframe.ElementPriority.TOP,
                                com.mac.transaction.item_transaction_new.ItemTransactionPanel.class,
                                transactionType,
                                true);
                    }
                    if (transactionType.getFormType().equals(FormTypes.ACCOUNT_TRANSACTION)) {
                        addButton(
                                transactionType.getName(),
                                SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE),
                                DefaultMainframe.ElementPriority.TOP,
                                com.mac.transaction.account_transaction.AccountTransactionPanel.class,
                                transactionType.getCode(),
                                true);
                    }
                }
            }
//        } catch (DatabaseException ex) {
//            Logger.getLogger(SphereMainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void createAccountTransactions() {
        HibernateDatabaseService databaseService = CPanel.GLOBAL.getDatabaseService();
//        try {
            List<RTransactionType> transactionTypes = databaseService.initCriteria(RTransactionType.class).add(Restrictions.eq("formType", FormTypes.ACCOUNT_TRANSACTION)).list();

            if (!transactionTypes.isEmpty()) {
                addTask("Account Transactions");
                addBand("Account Transactions", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));

                for (RTransactionType transactionType : transactionTypes) {
                    if (transactionType.getFormType().equals(FormTypes.ITEM_TRANSACTION)) {
                        addButton(
                                transactionType.getName(),
                                SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE),
                                DefaultMainframe.ElementPriority.TOP,
                                com.mac.transaction.item_transaction_new.ItemTransactionPanel.class,
                                transactionType,
                                true);
                    }
                    if (transactionType.getFormType().equals(FormTypes.ACCOUNT_TRANSACTION)) {
                        addButton(
                                transactionType.getName(),
                                SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE),
                                DefaultMainframe.ElementPriority.TOP,
                                com.mac.transaction.account_transaction.AccountTransactionPanel.class,
                                transactionType.getCode(),
                                true);
                    }
                }
            }
//        } catch (DatabaseException ex) {
//            Logger.getLogger(SphereMainFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void createReports() {
        addTask("Reports");
        addBand("Reports", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("Master Reports", SphereResources.getImageIconURL(SphereResources.TRANSACTION_REPORTS), DefaultMainframe.ElementPriority.TOP, com.mac.reports.report_panel.MasterReport.class, null);
        addButton("Transaction Reports", SphereResources.getImageIconURL(SphereResources.TRANSACTION_REPORTS), DefaultMainframe.ElementPriority.TOP, com.mac.reports.report_panel.TransactionReport.class, null);
        addButton("Stock Reports", SphereResources.getImageIconURL(SphereResources.TRANSACTION_REPORTS), DefaultMainframe.ElementPriority.TOP, com.mac.reports.report_panel.StockReport.class, null);
        addButton("Supplier Reports", SphereResources.getImageIconURL(SphereResources.TRANSACTION_REPORTS), DefaultMainframe.ElementPriority.TOP, com.mac.reports.report_panel.SupplierReport.class, null);
        addButton("Customer Reports", SphereResources.getImageIconURL(SphereResources.TRANSACTION_REPORTS), DefaultMainframe.ElementPriority.TOP, com.mac.reports.report_panel.CustomerReport.class, null);
    }

    private void createSystem() {
        addTask("System");
        addBand("Settings", SphereResources.getImageIconURL(SphereResources.REGISTRATION_PEOPLE));
        addButton("System Settings", SphereResources.getImageIconURL(SphereResources.SYSTEM_SETTING), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.settings.system_settings.editor.SystemSettings.class, null);
        addButton("Branch Settings", SphereResources.getImageIconURL(SphereResources.BRANCH_SETTING), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.settings.branch_settings.BranchSettings.class, null);
        addButton("Transaction Type", SphereResources.getImageIconURL(SphereResources.TRANSACTION_TYPE), DefaultMainframe.ElementPriority.TOP, com.mac.transaction.transaction_registration.REGTransactionType.class, null);
        addButton("Report Registration", SphereResources.getImageIconURL(SphereResources.REPORT_REGISTRATION), DefaultMainframe.ElementPriority.TOP, com.mac.reports.registration.REGReportRegistration.class, null);
        addButton("User Role", SphereResources.getImageIconURL(SphereResources.USER_ROLE_REGISTRATION), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.permission.user_role.REGUserRole.class, null);
        addButton("Permission", SphereResources.getImageIconURL(SphereResources.USER_PERMISSION), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.permission.permission.REGPermission.class, null);
        addButton("User Role Permission", SphereResources.getImageIconURL(SphereResources.USER_ROLE_REGISTRATION), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.permission.user_role_permission.REGUserRolePermission.class, null);
        addButton("User Role Report", SphereResources.getImageIconURL(SphereResources.USER_ROLE_REGISTRATION), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.permission.user_role_report.REGUserRoleReport.class, null);
        addButton("Theme", SphereResources.getImageIconURL(SphereResources.THEME), DefaultMainframe.ElementPriority.TOP, com.mac.zsystem.settings.theme_chooser.Theme.class, null);
    }
}

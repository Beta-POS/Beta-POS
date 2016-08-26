/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.reports.report_parameter;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.environment.service.AbstractService;
import com.mac.registration.item.ItemPropertyTypes;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class SERReportParameter extends AbstractService {

    public SERReportParameter(Component component) {
        super(component);
    }

    public List getDepartments() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MDepartment where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getMainCategories() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MMainCategory where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getSubCategories() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MSubCategory where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getStores() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MStore where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getEmployees() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MEmployee where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getItems() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItem where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getBranches() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MBranch");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            ex.printStackTrace();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    public List getRouts() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MRouts");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getTransactorCategories() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MTransactorCategory where Branch ='"+CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
      public List getTransactionType() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.transaction_type_object.RTransactionType");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getClients() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MTransactor where client=true and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getSuppliers() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MTransactor where supplier=true and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List getBrands() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItemProperty where type='"+ ItemPropertyTypes.BRAND+"' and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getMakes() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItemProperty where type='"+ ItemPropertyTypes.MAKE+"' and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getModels() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItemProperty where type='"+ ItemPropertyTypes.MODEL+"' and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getSizes() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItemProperty where type='"+ ItemPropertyTypes.SIZE+"' and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getUnits() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItemProperty where type='"+ ItemPropertyTypes.UNIT+"' and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List getGenarics() {
        List list;
        try {
            list = getDatabaseService().getCollection("from com.mac.reports.report_parameter.object.MItemProperty where type='"+ ItemPropertyTypes.GENERIC+"' and branch='"+ CApplication.getSessionVariable(CApplication.STORE_ID)+"'");
        } catch (DatabaseException ex) {
            list = new ArrayList();
            Logger.getLogger(SERReportParameter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

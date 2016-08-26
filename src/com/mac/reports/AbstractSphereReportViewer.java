/*
 *  AbstractSphereReportViewer.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 16, 2015, 12:27:34 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.reports;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.database.hibernate.HibernateSQLQuery;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.environment.cpanel.CPanel;
import com.mac.af.panel.dialog.object_creator_dialog.ObjectCreatorDialog;
import com.mac.af.templates.report.AbstractReportViewer;
import com.mac.af.templates.report.ReportBuilder;
import com.mac.registration.branch.object.MBranch;
import com.mac.reports.object.MReport;
import com.mac.reports.registration.ReportsResources;
import com.mac.reports.report_parameter.PCParameterList;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import sphere.Sphere;

/**
 *
 * @author mohan
 */
public abstract class AbstractSphereReportViewer extends AbstractReportViewer<MReport> {

    private static ObjectCreatorDialog<HashMap<String, Object>> OBJECT_CREATOR_DIALOG;
    private static PCParameterList PC_PARAMETER;
    private static Map<String, Object> DEFAULT_PARAMETERS;
    private static String BRANCH;

    protected abstract String getReportCategory();

    @Override
    protected List<MReport> getReports() {
        try {
            String sql = "SELECT \n"
                    + "	*\n"
                    + "FROM\n"
                    + "	m_report\n"
                    + "	LEFT JOIN r_user_role_report ON r_user_role_report.report = m_report.code\n"
                    + "WHERE\n"
                    + "	r_user_role_report.user_role = '" + CApplication.getSessionVariable(Sphere.USER_ROLE_KEY) + "'"
                    + "     AND m_report.category = '" + getReportCategory() + "'";

            HibernateSQLQuery hibernateSQLQuery = new HibernateSQLQuery(sql, MReport.class);

            return getDatabaseService().getCollection(hibernateSQLQuery, null);
        } catch (DatabaseException ex) {
            Logger.getLogger(AbstractSphereReportViewer.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }

    }

    @Override
    protected String getReportName(MReport report) {
        return report.getName();
    }

    @Override
    protected Icon getReportIcon(MReport report) {
        return ReportsResources.getImageIcon(ReportsResources.REPORT_ICON_URL);
    }

    @Override
    protected InputStream getReportStream(MReport report) {
        return new ByteArrayInputStream(report.getReport());
    }

    @Override
    protected Map<String, Object> getDefaultParameters() {
        initDefaultParameters();
        return DEFAULT_PARAMETERS;
    }

    @Override
    protected Map<String, Object> getRequiredParameters(String title, List<net.sf.jasperreports.engine.JRParameter> parameters) {

        if (OBJECT_CREATOR_DIALOG == null) {
            OBJECT_CREATOR_DIALOG = new ObjectCreatorDialog();

            PC_PARAMETER = new PCParameterList();
            OBJECT_CREATOR_DIALOG.setObjectCreator(PC_PARAMETER);
        }

        OBJECT_CREATOR_DIALOG.setTitle(title);
        PC_PARAMETER.setReportParameters(parameters);

        OBJECT_CREATOR_DIALOG.pack();

        return OBJECT_CREATOR_DIALOG.getNewObject();
    }

    public static void initDefaultParameters() {
        //init default parameters
        if (DEFAULT_PARAMETERS == null) {
            try {
                DEFAULT_PARAMETERS = new HashMap<>();

                HibernateDatabaseService databaseService = new HibernateDatabaseService(CPanel.GLOBAL);
                MBranch branch = (MBranch) databaseService.getObject(MBranch.class, (Serializable) CApplication.getSessionVariable(CApplication.STORE_ID));

                BRANCH = branch.getCode();

                DEFAULT_PARAMETERS.put("H_COMPANY_NAME", branch.getMCompany().getName());
                DEFAULT_PARAMETERS.put("H_COMPANY_ADDRESS", branch.getAddressLine1() + ", " + branch.getAddressLine2() + ", " + branch.getAddressLine3());
                DEFAULT_PARAMETERS.put("H_COMPANY_TELEPHONE", branch.getTelephone1() + ", " + branch.getTelephone2());
                DEFAULT_PARAMETERS.put("H_COMPANY_FAX", branch.getFax());
                DEFAULT_PARAMETERS.put("H_COMPANY_EMAIL", branch.getEmail());
                DEFAULT_PARAMETERS.put("H_BRANCH", branch.getCode());
            } catch (DatabaseException ex) {
                Logger.getLogger(ReportBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static Map<String, Object> getDefaultParameterMap() {
        initDefaultParameters();
        return DEFAULT_PARAMETERS;
    }

    public static String getBranch() {
        initDefaultParameters();
        return BRANCH;
    }
}

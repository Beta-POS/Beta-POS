/*
 *  TransactionReport.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 19, 2015, 3:55:25 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.reports.report_panel;

import com.mac.reports.AbstractSphereReportViewer;
import com.mac.reports.registration.ReportCategories;

/**
 *
 * @author mohan
 */
public class TransactionReport extends AbstractSphereReportViewer {

    @Override
    protected String getReportCategory() {
        return ReportCategories.TRANSACTION;
    }
}

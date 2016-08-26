/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.reports.report_parameter;

import com.mac.af.panel.object.AbstractObjectCreator;
import com.mac.af.panel.object.ObjectCreatorException;
import com.mac.af.panel.springgrid.AbstractSpringGrid;
import com.mac.af.templates.report.ReportBuilder;
/////////import com.mac.zreport.ReportUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import net.sf.jasperreports.engine.JRParameter;

/**
 *
 * @author mohan
 */
public class PCParameter extends AbstractObjectCreator<Map<String, Object>> {

    private AbstractSpringGrid springGrid;
    private List<JRParameter> parameters;

    public PCParameter() {
        initOthers();
    }

    public void setReportParameters(List<JRParameter> parameters) {
        this.parameters = parameters;

        initGrid();
    }

    private void initGrid() {
        springGrid = new AbstractSpringGrid(
                0,0,10,10
                ) {
            @Override
            protected Map<String, Class> getValueClasses() {
                return PCParameter.this.getValueClasses();
            }

            @Override
            protected Map<String, String> getValueLabels() {
                return PCParameter.this.getValueLabels();
            }
        };

        removeAll();
        add(springGrid);
    }

    private void initOthers() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
    }

    private Map<String, Class> getValueClasses() {
        Map<String, Class> valueClasses = new HashMap<>();
        for (JRParameter parameter : parameters) {
            if (!parameter.isSystemDefined()) {
                valueClasses.put(parameter.getName(), parameter.getValueClass());
            }
        }
        return valueClasses;
    }

    private Map<String, String> getValueLabels() {
        Map<String, String> valueLabels = new HashMap<>();
        for (JRParameter parameter : parameters) {
            if (!parameter.isSystemDefined()) {
                valueLabels.put(parameter.getName(), ReportBuilder.getFormattedString(parameter.getName()));
            }
        }

        return valueLabels;
    }

    @Override
    public void setNewMood() {
    }

    @Override
    public void setEditMood() {
    }

    @Override
    public void setIdleMood() {
    }

    @Override
    public void resetFields() {
    }

    @Override
    protected void setValueAbstract(Map<String, Object> value) throws ObjectCreatorException {
    }

    @Override
    protected Map<String, Object> getValueAbstract() throws ObjectCreatorException {
        return springGrid.getValue();
    }

    @Override
    protected void initInterface() throws ObjectCreatorException {
    }

    @Override
    protected void initObject() throws ObjectCreatorException {
    }
}

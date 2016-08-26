/*
 *  Sphere.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Dec 19, 2014, 2:35:42 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package sphere;

import com.mac.af.core.database.DatabaseException;
import com.mac.af.core.database.hibernate.HibernateDatabaseService;
import com.mac.af.core.environment.CApplication;
import com.mac.af.core.environment.CSplashScreen;
import com.mac.af.core.environment.cpanel.CPanel;
import com.mac.af.core.environment.mainframe.DefaultMainframe;
import com.mac.af.core.message.mOptionPane;
import com.mac.af.core.setting.SettingException;
import com.mac.af.core.setting.framework.Framework;
import com.mac.af.core.setting.framework.store.Store;
import com.mac.af.core.setting.gui.Settings;
import com.mac.registration.branch.object.MBranch;
import com.mac.registration.employee.object.MEmployee;
import com.mac.registration.employee.object.MPhoto;
import com.mac.registration.employee.object.RPermission;
import com.mac.registration.employee.object.RUserRole;
import com.mac.zsystem.settings.system_settings.SystemSettingInterface;
import java.awt.Frame;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohan
 */
public class Sphere extends CApplication {

    private static String[] args;
    private HibernateDatabaseService databaseService;
    private DefaultMainframe mainFrame;
    public static final String USER_PREFIX_KEY = "USER_PREFIX";
    public static final String USER_ROLE_KEY = "USER_ROLE";
    private List<String> allowedPermissionTabs;
    private List<String> allPermissionTabs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sphere.args = args;

        CApplication.launch(new Sphere());
    }

    @Override
    public void Restart() {
        CApplication.getInstance().getMainFrame().dispose();
        main(args);
    }

    @Override
    protected Frame getApplicationMainFrame() {
        return new SphereMainFrame();
    }

    @Override
    protected CSplashScreen getSplashScreen() {
//        return new SphereSplashScreen();
        return null;
    }

    @Override
    public String getApplicationName() {
        return "Sphere";
    }

    @Override
    public String getApplicationVersion() {
        return "1.0.6";
    }

    private void initDatabaseService() {
        if (databaseService == null) {
            databaseService = new HibernateDatabaseService(CPanel.GLOBAL);
        }
    }

    @Override
    protected void startup() {
        try {
            initDatabaseService();
            MBranch branch = getBranch();
            if (!getApplicationVersion().equalsIgnoreCase(branch.getMCompany().getVersionId())) {
                mOptionPane.showMessageDialog(null, "You currently using differet version of the system. \nPlease update your system.\n System version:" + getApplicationVersion() + "\nDatabase Version:" + branch.getMCompany().getVersionId(), "System Update", mOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            SystemSettingInterface.checkSystemSettings(databaseService);
        } catch (DatabaseException ex) {
            Logger.getLogger(Sphere.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean authenticate(String user, String password, Object store) throws Exception {
        initDatabaseService();
        Store store1 = ((Framework) CApplication.getInstance().getSettingRoot().getSetting(Framework.class)).getStore();

        HashMap<String, Object> params = new HashMap<>();
        params.put("USER_NAME", user);
        params.put("PASSWORD", password);
        params.put("STORE", ((Store) store1).getStore().getValue());

        List<MEmployee> listData = databaseService.getCollection("from com.mac.registration.employee.object.MEmployee where active=true and user_name=:USER_NAME and password=:PASSWORD and branch = :STORE", params);

        if (listData.size() > 0) {
            MEmployee mEmployee = listData.get(0);

            try {
                createSidePanel(mEmployee);
                MBranch mBranch = getBranch();

                //CREATE DATE
                Date workingDate = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                workingDate = dateFormat.parse(dateFormat.format(workingDate));

                //SESSION VARIABLES
                CApplication.addSessionVariable(CApplication.STORE_ID, mBranch.getCode());
                CApplication.addSessionVariable(CApplication.USER_ID, mEmployee.getCode());
                CApplication.addSessionVariable(CApplication.WORKING_DATE, workingDate);

                if (mEmployee.getRUserRole() == null) {
                    CApplication.addSessionVariable(Sphere.USER_ROLE_KEY, null);
                } else {
                    CApplication.addSessionVariable(Sphere.USER_ROLE_KEY, mEmployee.getRUserRole().getCode());
                }

                //permission
                allowedPermissionTabs = new ArrayList<>();
                RUserRole userRole = mEmployee.getRUserRole();

                if (userRole != null) {
                    for (RPermission permission1 : userRole.getRPermissions()) {
                        allowedPermissionTabs.add(permission1.getName());
                    }
                }

                allPermissionTabs = new ArrayList<>();
                List<RPermission> permissions = databaseService.getCollection(RPermission.class);
                for (RPermission permission1 : permissions) {
                    allPermissionTabs.add(permission1.getName());
                }

            } catch (ParseException | DatabaseException e) {
                Logger.getLogger(Sphere.class.getName()).log(Level.SEVERE, null, e);
            }
            return true;
        }
        return false;
    }

    @Override
    public String getAuthenticationInformation() {
        MBranch branch = getBranch();

        StringBuilder builder = new StringBuilder();

        builder.append("<HTML>");

        //application information
        builder.append("<DIV STYLE='text-align: center; font-size: 18px;'>");
        builder.append("<B>");
        builder.append(CApplication.getInstance().getApplicationName())
                .append(" - v")
                .append(CApplication.getInstance().getApplicationVersion());
        builder.append("</B>");
        builder.append("</DIV>");

        //company information
        builder.append(branch.getMCompany() != null ? "<B>Company:</B><BR/>" + branch.getMCompany().getName() + "</B>" : "");
        builder.append("<BR/>");
        builder.append("<BR/>");
        builder.append("<B>Branch:</B>");
        builder.append("<BR/>");
        builder.append(branch.getName());
        builder.append("<BR/>");
        builder.append("<BR/>");
        builder.append("<B><U>");
        //ending
        builder.append("</HTML>");

        return builder.toString();
    }

    private MBranch getBranch() {
        initDatabaseService();

        MBranch branch = null;
        try {
            //getting info from database
            Store store = ((Framework) CApplication.getInstance().getSettingRoot().getSetting(Framework.class)).getStore();
            //read from db 
            branch = (MBranch) databaseService.getObject(MBranch.class, store.getStore().getValue());
            if (branch == null) {
                int q = mOptionPane.showOptionDialog(null, "Store cannot find by the code '" + store.getStore().getValue() + "', \n What do you wish to do ?", "No Branch Found", mOptionPane.YES_NO_OPTION, mOptionPane.ERROR_MESSAGE, null, new String[]{"Edit Settings", "Shut Down"}, "Edit Settings");

                switch (q) {
                    case mOptionPane.YES_OPTION:
                        Settings.viewSettingsPanel(store);
                        return getBranch();
                    case mOptionPane.NO_OPTION:
                        CApplication.getInstance().Shutdown();
                }
            } else {
                Date date = branch.getWorkingDate();
                if (date == null) {
                    int q = mOptionPane.showOptionDialog(null, "Not recognized working date, \n What do you wish to do ?", "Working date", mOptionPane.YES_NO_OPTION, mOptionPane.ERROR_MESSAGE, null, new String[]{"Set Working Date", "Shut Down"}, "Set Working Date");

                    switch (q) {
                        case mOptionPane.YES_OPTION:
                            String wDate = mOptionPane.showInputDialog(null, "Please enter the correct working date in pattern YYYY-MM-DD. \n WORNING: If you are not sure the working date, please contact your system administrator.", "Working Date", mOptionPane.WARNING_MESSAGE);
                            if (wDate == null) {
                                CApplication.getInstance().Shutdown();
                            } else {
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date workingDate = format.parse(wDate);
                                    branch.setWorkingDate(workingDate);
                                    databaseService.save(branch);
                                } catch (ParseException ex) {
                                    mOptionPane.showMessageDialog(null, "You entered working date is not recognized. System will shutdown.", "Working Date", mOptionPane.WARNING_MESSAGE);
                                    CApplication.getInstance().Shutdown();
                                }
                            }
                            return getBranch();
                        case mOptionPane.NO_OPTION:
                            CApplication.getInstance().Shutdown();
                    }
                }
            }

        } catch (SettingException | DatabaseException ex) {
            Logger.getLogger(Sphere.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (branch == null) {
            return getBranch();
        } else {
            //this.branch = branch;
            return branch;
        }

    }

    private void createSidePanel(MEmployee employee) {
        mainFrame = new SphereMainFrame();
        MPhoto photo = employee.getMPhoto();
        byte[] imageData = null;

        if (photo != null) {
            imageData = photo.getPhoto();
        } else {
            imageData = null;
        }
        SidePanel sidePanel = new SidePanel(employee.getName(), imageData);
        mainFrame.setSideBar(sidePanel);
    }

    @Override
    public boolean isAllowedTab(Class c) {
        String cl = c.getCanonicalName();

        boolean permissionIncluded = allPermissionTabs.contains(cl);
        boolean hasPermission = allowedPermissionTabs.contains(cl);

        return permissionIncluded ? hasPermission : true;
    }

    @Override
    public boolean isAllowedTab(String title) {
        boolean permissionIncluded = allPermissionTabs.contains(title);
        boolean hasPermission = allowedPermissionTabs.contains(title);

        return permissionIncluded ? hasPermission : true;
    }
}

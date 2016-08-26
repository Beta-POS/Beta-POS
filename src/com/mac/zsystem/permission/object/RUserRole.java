package com.mac.zsystem.permission.object;

/**
  *	@author Channa Mohan
  *	
  *	Created On Jul 2, 2015 3:46:30 PM 
  *	Mohan Hibernate Mapping Generator
  */


import java.util.HashSet;
import java.util.Set;

/**
 * RUserRole generated by hbm2java
 */
public class RUserRole  implements java.io.Serializable {


     private String code;
     private String name;
     private Set<RPermission> RPermissions = new HashSet<RPermission>(0);
     private Set<MReport> MReports = new HashSet<MReport>(0);
     private Set<MEmployee> MEmployees = new HashSet<MEmployee>(0);

    public RUserRole() {
    }

	
    public RUserRole(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public RUserRole(String code, String name, Set<RPermission> RPermissions, Set<MReport> MReports, Set<MEmployee> MEmployees) {
       this.code = code;
       this.name = name;
       this.RPermissions = RPermissions;
       this.MReports = MReports;
       this.MEmployees = MEmployees;
    }
   
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<RPermission> getRPermissions() {
        return this.RPermissions;
    }
    
    public void setRPermissions(Set<RPermission> RPermissions) {
        this.RPermissions = RPermissions;
    }
    public Set<MReport> getMReports() {
        return this.MReports;
    }
    
    public void setMReports(Set<MReport> MReports) {
        this.MReports = MReports;
    }
    public Set<MEmployee> getMEmployees() {
        return this.MEmployees;
    }
    
    public void setMEmployees(Set<MEmployee> MEmployees) {
        this.MEmployees = MEmployees;
    }


	@Override
     public String toString() {
		return code + "-" + name;
     }

@Override
public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof RUserRole) ) return false;
		 RUserRole castOther = ( RUserRole ) other; 

	if(!java.util.Objects.equals(this.code, castOther.code)) {
            return false;
     }
	if(!java.util.Objects.equals(this.name, castOther.name)) {
            return false;
     }
         
		 return true;
   }

   @Override
   public int hashCode() {
         int result = 17;
         
	result = result * 17 + java.util.Objects.hashCode(this.code);
	result = result * 17 + java.util.Objects.hashCode(this.name);

         return result;
   }   

}



/*
 *  XmlViewFactory.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 17, 2015, 10:33:10 AM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.reports.registration.highlighter;

import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class XmlViewFactory extends Object implements ViewFactory {

    /**
     * @see javax.swing.text.ViewFactory#create(javax.swing.text.Element)
     */
    public View create(Element element) {

        return new XmlView(element);
    }
}

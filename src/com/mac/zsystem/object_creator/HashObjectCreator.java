/*
 *  HashObjectCreator.java
 *  
 *  @author Channa Mohan
 *     hjchanna@gmail.com
 *  
 *  Created on Feb 26, 2015, 8:46:37 PM
 *  Copyrights channa mohan, All rights reserved.
 *  
 */
package com.mac.zsystem.object_creator;

import com.mac.af.panel.object.DefaultObjectCreator;
import com.mac.zsystem.util.hash.HashUtil;

/**
 *
 * @author mohan
 */
public abstract class HashObjectCreator<T> extends DefaultObjectCreator<T> {

    @Override
    public Object getIdentifier() {
        return HashUtil.getHash((String) getIdentityComponent().getCValue());
    }
}

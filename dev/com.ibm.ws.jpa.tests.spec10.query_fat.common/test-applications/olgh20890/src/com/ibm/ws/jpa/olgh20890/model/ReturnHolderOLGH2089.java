/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.jpa.olgh20890.model;

import java.math.BigDecimal;

public class ReturnHolderOLGH2089 {
    private BigDecimal value;

    public ReturnHolderOLGH2089(Object value) {
        super();
        this.value = (BigDecimal) value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DecHolder [value=" + value + "]";
    }
}

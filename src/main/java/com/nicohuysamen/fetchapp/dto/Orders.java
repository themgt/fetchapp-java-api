/*
* Orders.java
*
* Copyright (c) 2013, Nicolaas Frederick Huysamen. All rights reserved.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 3 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
* MA 02110-1301 USA
*/

package com.nicohuysamen.fetchapp.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
public class Orders {

    @XmlAttribute
    private static final String type = "array";

    @XmlElement(name = "order")
    private List<Order> orders = new ArrayList<Order>();

    public List<Order> getOrders() {
        return orders;
    }
}

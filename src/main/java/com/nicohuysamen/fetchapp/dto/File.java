/*
* File.java
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "file")
@XmlAccessorType(XmlAccessType.FIELD)
public class File {

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "filename")
    private String filename;

    @XmlElement(name = "size_bytes")
    private int sizeInBytes;

    @XmlElement(name = "content_type")
    private String contentType;

    @XmlElement(name = "permalink")
    private String permalink;

    @XmlElement(name = "url", nillable = true)
    private String url;

    @XmlElement(name = "type")
    private String type;

    public String getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public String getContentType() {
        return contentType;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", filename='" + filename + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                ", contentType='" + contentType + '\'' +
                ", permalink='" + permalink + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

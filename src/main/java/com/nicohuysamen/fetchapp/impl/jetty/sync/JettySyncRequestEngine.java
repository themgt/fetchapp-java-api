/*
* JettySyncRequestEngine.java
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

package com.nicohuysamen.fetchapp.impl.jetty.sync;

import com.nicohuysamen.fetchapp.RequestConstants;
import com.nicohuysamen.fetchapp.dto.Message;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.io.ByteArrayBuffer;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 *
 */
public class JettySyncRequestEngine {

    private final HttpClient httpClient = new HttpClient();

    private String authKey;

    public JettySyncRequestEngine(
            final String authKey,
            final int maxConnectionsPerAddress,
            final int connectionQueueSize,
            final int connectionTimeout) throws Exception {

        httpClient.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
        httpClient.setMaxConnectionsPerAddress(maxConnectionsPerAddress);
        httpClient.setThreadPool(new QueuedThreadPool(connectionQueueSize));
        httpClient.setTimeout(connectionTimeout);
        httpClient.start();

        this.authKey = authKey;
    }

    public void setAuthKey(final String authKey) {
        this.authKey = authKey;
    }

    public synchronized <R> R sendGetRequest(
            final Class<R> receiveClass,
            final String method) {

        return sendRequest(Void.class, receiveClass, RequestConstants.REQUEST_METHOD_GET, method, null);
    }

    public synchronized <R> R sendDeleteRequest(
            final Class<R> receiveClass,
            final String method) {

        return sendRequest(Void.class, receiveClass, RequestConstants.REQUEST_METHOD_DELETE, method, null);
    }

    public synchronized <S, R> R sendPostRequest(
            final Class<S> sendClass,
            final Class<R> receiveClass,
            final String method,
            final S content) {

        return sendRequest(sendClass, receiveClass, RequestConstants.REQUEST_METHOD_POST, method, content);
    }

    public synchronized <S, R> R sendPutRequest(
            final Class<S> sendClass,
            final Class<R> receiveClass,
            final String method,
            final S content) {

        return sendRequest(sendClass, receiveClass, RequestConstants.REQUEST_METHOD_PUT, method, content);
    }

    @SuppressWarnings("unchecked")
    private <S, R> R sendRequest(
            final Class<S> sendClass,
            final Class<R> receiveClass,
            final String requestMethod,
            final String method,
            final S content) {

        try {
            final ContentExchange exchange = new ContentExchange();

            exchange.setMethod(requestMethod);
            exchange.setRequestHeader(RequestConstants.REQUEST_HEADER_AUTH, authKey);
            exchange.setRequestContentType(RequestConstants.REQUEST_CONTENT_XML);
            exchange.setURL(RequestConstants.generateRequestUrl(method));

            if (content != null) {
                final JAXBContext sendContext = JAXBContext.newInstance(sendClass);
                final Marshaller marshaller = sendContext.createMarshaller();
                final StringWriter xml = new StringWriter();

                marshaller.marshal(content, xml);
                exchange.setRequestContent(new ByteArrayBuffer(xml.toString()));
            }

            httpClient.send(exchange);
            exchange.waitForDone();

            JAXBContext receiveContext = JAXBContext.newInstance("com.nicohuysamen.fetchapp.dto");
            Unmarshaller unmarshaller = receiveContext.createUnmarshaller();
            Object response = unmarshaller.unmarshal(new StringReader(exchange.getResponseContent()));

            if ((response instanceof Message && !receiveClass.equals(Message.class))) {
                // TODO: Log error
                System.err.println(((Message) response).getMessage());
                return null;
            } else if (!response.getClass().equals(receiveClass)) {
                // Try to recover -- Workaround for JAXB problem when loading package files.
                receiveContext = JAXBContext.newInstance(receiveClass);
                unmarshaller = receiveContext.createUnmarshaller();
                response = unmarshaller.unmarshal(new StringReader(exchange.getResponseContent()));
            }

            return (R) response;

        } catch (final JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}

/**
 * Copyright 2016 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.com.claro.post.documentosSaldoReclamo.one;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pe.com.claro.common.resource.exception.ApiException;
import pe.com.claro.common.resource.exception.ErrorMessage;
import pe.com.claro.common.resource.exception.NotFoundException;
import pe.com.claro.common.resource.exception.NotLogException;
import pe.com.claro.common.resource.util.ApiResponse;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProviderExceptionMapper implements ExceptionMapper<Exception> {


    LogUtil logUtil = new LogUtil(ProviderExceptionMapper.class);
    @Context
    private Configuration conf;
    private ObjectMapper mapper = new ObjectMapper();
    private String result = "";

    public Response toResponse(Exception exception) {
        if (exception instanceof WebApplicationException) {
            WebApplicationException e = (WebApplicationException) exception;
            logUtil.error("1ERROR: [" + WebApplicationException.class + "] - [" + e.getMessage() + "] ", e);
            return Response.status(e.getResponse().getStatus())
                    .entity(new ApiResponse(e.getResponse().getStatus(), exception.getMessage())).build();
        } else if (exception instanceof JsonParseException) {
            logUtil.error("2ERROR: [" + JsonParseException.class + "] - [" + exception.getMessage() + "] ", exception);
            return Response.status(Status.BAD_REQUEST).entity(new ApiResponse(ApiResponse.ERROR, "Entrada incorrecta"))
                    .build();
        } else if (exception instanceof NotFoundException) {
            logUtil.error("ERROR: [" + NotFoundException.class + "] - [] ", exception);
            try {
                result = mapper.writeValueAsString(new ErrorMessage((NotFoundException) exception));
                logUtil.response(result);
            } catch (JsonProcessingException ignored) {
            }
            return Response.status(((NotFoundException) exception).getStatus())
                    .entity(result).type(MediaType.APPLICATION_JSON).build();
        } else if (exception instanceof NotLogException) {
            try {
                result = mapper.writeValueAsString(new ErrorMessage((NotLogException) exception));
                logUtil.response(result);
            } catch (JsonProcessingException ignored) {
            }
            return Response.status(((NotLogException) exception).getStatus())
                    .entity(result).type(MediaType.APPLICATION_JSON).build();
        } else if (exception instanceof ApiException) {
            logUtil.error("5ERROR: [" + ApiException.class + "] - [" + exception.getMessage() + "] ", exception);
            return Response.status(((ApiException) exception).getStatus())
                    .entity(new ErrorMessage((ApiException) exception)).type(MediaType.APPLICATION_JSON).build();
        } else {
            logUtil.error("ERROR: - [] --->", exception);
            try {
                result = mapper.writeValueAsString(new ErrorMessage(Integer.parseInt(conf.getProperty("codigoRespuesta.idt3").toString()), conf.getProperty("mensajeRespuesta.idt3").toString(), exception));
            } catch (JsonProcessingException ignored) {
            }
            logUtil.response(result);
            return Response.status(Status.BAD_REQUEST)
                    .entity(result).type(MediaType.APPLICATION_JSON).build();


        }
    }

}
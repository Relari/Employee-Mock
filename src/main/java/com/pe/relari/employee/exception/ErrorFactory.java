package com.pe.relari.employee.exception;

import com.pe.relari.config.ErrorProperties;
import com.pe.relari.employee.model.error.ErrorResponse;
import com.pe.relari.employee.util.ConverterUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ErrorFactory {

  private static final List<HttpStatus> HTTP_STATUSES = Arrays.asList(HttpStatus.values());

  @Value("${application.info.name}")
  private String applicationName;

  private final ErrorProperties errorProperties;

  public ErrorFactory(ErrorProperties errorProperties) {
    this.errorProperties = errorProperties;
  }

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ErrorResponse> handleException(ApiException apiException) {

    ErrorProperties.ErrorModel properties = errorProperties.getErrors()
            .get(apiException.getCatalog().name());

    HttpStatus httpStatus = getHttpStatus(properties.getCategory());

    ErrorResponse.ErrorDetail errorDetail = ErrorResponse.ErrorDetail.builder()
            .appName(applicationName)
            .errorCode(apiException.getClass().getSimpleName())
            .status(httpStatus.value())
            .message(properties.getDescription())
            .cause(apiException.getDetail())
            .build();

    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(properties.getDescription());

    if (StringUtils.isBlank(apiException.getMessage())) {

      errorResponse.setErrorDetails(Collections.singletonList(errorDetail));

    } else {

      String json = apiException.getMessage()
              .substring(apiException.getMessage().indexOf("["));

      ErrorResponse[] responses = ConverterUtil.readData(json, ErrorResponse[].class);

      assert responses != null;
      List<ErrorResponse.ErrorDetail> errorDetails = responses[0].getErrorDetails();

      errorDetails.add(errorDetail);
      errorResponse.setErrorDetails(errorDetails);

    }

    return ResponseEntity
            .status(httpStatus)
            .body(errorResponse);
  }

  private HttpStatus getHttpStatus(String category) {
    return HTTP_STATUSES.stream()
            .filter(status ->
                    status.name().equals(category)
            )
            .findFirst()
            .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}

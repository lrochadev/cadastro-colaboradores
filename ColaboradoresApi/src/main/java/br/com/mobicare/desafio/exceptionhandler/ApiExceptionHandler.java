package br.com.mobicare.desafio.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Leonardo de Souza Rocha
 *
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String msgUsuario = getMessageFromProperties("mensagem.invalida");

		ErrorDetails rnfDetails = ErrorDetails.Builder.newBuilder().status(HttpStatus.NOT_FOUND.value())
				.title(msgUsuario).detail(ex.getMessage()).developerMessage(ex.getClass().getName()).build();

		logger.error("Handle -> handleHttpMessageNotReadable() " + rnfDetails.toString());

		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {

		ErrorDetails rnfDetails = ErrorDetails.Builder.newBuilder().status(HttpStatus.NOT_FOUND.value())
				.title(getMessageFromProperties("recurso.nao.encontrado")).detail(rfnException.getMessage())
				.developerMessage(rfnException.getClass().getName()).build();

		logger.error("Handle -> handleResourceNotFoundException() " + rnfDetails.toString());

		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ MetaDataAccessException.class, DataAccessResourceFailureException.class,
			CannotGetJdbcConnectionException.class, DataIntegrityViolationException.class, DataAccessException.class,
			BadSqlGrammarException.class, UncategorizedSQLException.class, })
	public ResponseEntity<Object> handleDatabaseException(Exception ex) {

		ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.title("Sistema temporariamente indisponível. Tente novamente mais tarde.").detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();

		logger.error("handleDatabaseException() " + errorDetails.toString());

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

		ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder.newBuilder()
				.status(HttpStatus.BAD_REQUEST.value()).title("Foram enviados parametros com o formato inválido.")
				.detail("Field validation error").developerMessage(manvException.getClass().getName()).field(fields)
				.fieldMessage(fieldMessages).build();

		logger.error("handleMethodArgumentNotValid() " + rnfDetails.toString());

		return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		String msgUsuario = getMessageFromProperties("internal.server.error");

		ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().status(status.value()).title(msgUsuario)
				.detail(exception.getMessage()).developerMessage(exception.getClass().getName()).build();

		logger.error("handleExceptionInternal() " + errorDetails.toString());

		return new ResponseEntity<>(errorDetails, headers, status);
	}

	/**
	 *
	 * Método responsável por buscar uma mensagem do arquivo: messages.properties
	 *
	 * @param mensagem
	 * @return
	 *
	 */
	private String getMessageFromProperties(String mensagem) {
		return messageSource.getMessage(mensagem, null, LocaleContextHolder.getLocale());
	}
}
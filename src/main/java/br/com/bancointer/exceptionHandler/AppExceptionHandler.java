package br.com.bancointer.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messagesource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String retorno = messagesource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		String stack = ex.getCause().toString();
		return handleExceptionInternal(ex, new Erro(retorno, stack), headers, status, request);
	}
	
	public static class Erro {
		private String retorno;
		private String stack;
		
		public String getRetorno() {
			return retorno;
		}

		public String getStack() {
			return stack;
		}

		public Erro(String retorno, String stack) {
			super();
			this.retorno = retorno;
			this.stack = stack;
		}
	}

}

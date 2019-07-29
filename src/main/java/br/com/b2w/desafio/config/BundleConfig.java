package br.com.b2w.desafio.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class BundleConfig {

    private static MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:mensagem");
        messageSource.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        return messageSource;
    }

    public static String getMensagem(String mensagem, String... values) {
        Locale.setDefault(new Locale("pt","BR"));
        MessageSource message = messageSource();
        return message.getMessage(mensagem, values, Locale.getDefault());
    }
}

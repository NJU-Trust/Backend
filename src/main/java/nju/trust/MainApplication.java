package nju.trust;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.Formatter;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
@EnableScheduling
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public LocalDateTime parse(String s, Locale locale) throws ParseException {
                return LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }

            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime);
            }
        };
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String s, Locale locale) throws ParseException {
                return LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
            }

            @Override
            public String print(LocalDate localDate, Locale locale) {
                return DateTimeFormatter.ISO_LOCAL_DATE.format(localDate);
            }
        };
    }
}

import org.example.provider.*;
import org.example.service.CurrencyExchangeService;
import org.example.service.Greeting;


module org.example.provider {

    exports org.example.provider;
    requires org.example.service;
    requires org.json;

    provides CurrencyExchangeService with UsdExchange, EuroExchange;

    provides Greeting with SwedishGreeting, EnglishGreeting, SpanishGreeting;
}

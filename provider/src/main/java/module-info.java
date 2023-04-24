import org.example.provider.currencyexchange.*;
import org.example.service.CurrencyExchangeService;


module org.example.provider {

    exports org.example.provider;
    exports org.example.provider.currencyexchange;
    requires org.example.service;
    requires org.json;

    provides CurrencyExchangeService with UsdExchange, EuroExchange, DkkExchange, JpyExchange, NokExchange, GbpExchange;
}

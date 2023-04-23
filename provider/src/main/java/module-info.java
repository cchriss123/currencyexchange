import org.example.provider.*;
import org.example.service.CurrencyExchangeService;


module org.example.provider {

    exports org.example.provider;
    requires org.example.service;
    requires org.json;

    provides CurrencyExchangeService with UsdExchange, EuroExchange;
}

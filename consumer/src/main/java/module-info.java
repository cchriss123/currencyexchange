module org.example.consumer {
    requires org.example.service;
    uses org.example.service.Greeting;
    uses org.example.service.CurrencyExchangeService;
}

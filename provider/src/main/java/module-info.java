import org.example.provider.EnglishGreeting;
import org.example.provider.SpanishGreeting;
import org.example.provider.SwedishGreeting;
import org.example.service.Greeting;


module org.example.provider {

    requires org.example.service;

    provides Greeting with SwedishGreeting, EnglishGreeting, SpanishGreeting;
}

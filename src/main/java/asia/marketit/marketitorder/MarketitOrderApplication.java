package asia.marketit.marketitorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MarketitOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketitOrderApplication.class, args);
	}

}

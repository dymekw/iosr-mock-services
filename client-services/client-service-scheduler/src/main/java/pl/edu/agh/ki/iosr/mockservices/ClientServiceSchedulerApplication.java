package pl.edu.agh.ki.iosr.mockservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.HttpZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
import org.springframework.cloud.sleuth.zipkin.ZipkinSpanReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import zipkin.Span;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@Configuration
@AutoConfigureBefore(ZipkinAutoConfiguration.class)
public class ClientServiceSchedulerApplication {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private SpanMetricReporter spanMetricReporter;

	@Autowired
	private ZipkinProperties zipkinProperties;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ClientServiceSchedulerApplication.class);
	}

	@Bean
	public ZipkinSpanReporter makeZipkinSpanReporter() {
		return new ZipkinSpanReporter() {
			private HttpZipkinSpanReporter delegate;
			private String baseUrl;

			@Override
			public void report(Span span) {
				InstanceInfo instance = eurekaClient.getNextServerFromEureka("zipkin", false);
				baseUrl = instance.getHomePageUrl();
				delegate = new HttpZipkinSpanReporter(new RestTemplate(), baseUrl, zipkinProperties.getFlushInterval(),
						spanMetricReporter);

				delegate.report(span);
			}
		};
	}
}

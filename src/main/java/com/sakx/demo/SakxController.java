package com.sakx.demo;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.ReadinessState;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SakxController {

	private final ApplicationEventPublisher publisher;

	public SakxController(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@RequestMapping("/")
	public String info() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
		return "Hello World - " + fmt.format(LocalDateTime.now());
	}

	@RequestMapping("/down")
	public String down() {
		AvailabilityChangeEvent.publish(publisher,this, ReadinessState.REFUSING_TRAFFIC);
		return "down";
	}

	@RequestMapping("/up")
	public String up() {
		AvailabilityChangeEvent.publish(publisher,this, ReadinessState.ACCEPTING_TRAFFIC);
		return "up";
	}
}
